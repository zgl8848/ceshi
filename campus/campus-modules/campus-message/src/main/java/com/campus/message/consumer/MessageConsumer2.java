package com.campus.message.consumer;

import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.minio.service.MinioTemplate;
import com.campus.grid.api.entity.School;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.equipmententity.Equipment;
import com.campus.grid.api.entity.equipmententity.EquipmentMsgSynchronize;
import com.campus.grid.api.entity.hktonglinkmsg.EventNotify;
import com.campus.message.feign.RemoteAlarmService;
import com.campus.message.feign.RemoteGridService;
import com.campus.message.feign.RemoteSchoolService;
import com.campus.message.utils.MessageConstants;
import com.campus.message.utils.MessageMethods;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author eatheryu
 * message 消费者2  处理设备以及抓拍机告警
 * 接收设备信息
 */

@Slf4j
@Component
@AllArgsConstructor
public class MessageConsumer2 {
	private static JsonParser parser = new JsonParser();
	private static MessageMethods messageMethods = new MessageMethods();
	private final RemoteGridService remoteGridService;
	private final RemoteAlarmService remoteAlarmService;
	private final RemoteSchoolService remoteSchoolService;
	private final MinioTemplate minioTemplate;
	private Gson gson = new Gson();

	@KafkaListener(topics = "${pro.topic2}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory", errorHandler = "consumerAwareErrorHandler")
	public void listen(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) throws Exception {
		log.info("kafkaConsumer2222222222 消费消息:{}", consumerRecord);
//		获取原始消息信息
		String equipmentMsg = consumerRecord.value();
//		获取原始消息中的  dataType 属性的值  1 = 设备信息 2 = 告警信息
		String dataType = null;
		try {
			JsonObject asJsonObject = parser.parse(equipmentMsg).getAsJsonObject();
			dataType = asJsonObject.get("dataType").getAsString();
		} catch (Exception e) {
			e.printStackTrace();
			acknowledgment.acknowledge();
		}
		//解析消息类型  =1  说明说设备信息 需调用设备信息接口
		if (MessageConstants.MSG_EQUIPMENT.equals(dataType)) {
			EquipmentMsgSynchronize equipmentMsgSynchronize = gson.fromJson(equipmentMsg, EquipmentMsgSynchronize.class);
			//解析操作类型 add-增加 update-修改 del-删除
			if (MessageConstants.MSG_ADD.equals(equipmentMsgSynchronize.getOperateType())) {
				try {
					remoteGridService.saveEquipmentMsg(equipmentMsgSynchronize, SecurityConstants.FROM_IN);
					// 提交偏移量
					acknowledgment.acknowledge();
				} catch (Exception e) {
					log.error("添加设备信息失败", e);
					throw e;
				}
			} else if (MessageConstants.MSG_UPDATE.equals(equipmentMsgSynchronize.getOperateType())) {
				try {
					//设备信息存入db
					remoteGridService.updateEquipmentMsg(equipmentMsgSynchronize, SecurityConstants.FROM_IN);
					// 提交偏移量
					acknowledgment.acknowledge();
				} catch (Exception e) {
					log.error("更新设备信息失败", e);
					throw e;
				}
			} else {
				try {
					//设备信息存入db
					remoteGridService.deleteEquipmentMsg(equipmentMsgSynchronize, SecurityConstants.FROM_IN);
					// 提交偏移量
					acknowledgment.acknowledge();
				} catch (Exception e) {
					log.error("删除操作数据库失败", e);
					throw e;
				}
			}
		}
		//解析消息类型  =2  说明说抓拍机告警 需调用告警接口
		if (MessageConstants.MSG_CAMERA_ALARM.equals(dataType)) {
			//将消息转换为xml对象
			EventNotify eventNotify = gson.fromJson(equipmentMsg, EventNotify.class);
			try {
				//根据平台ip获取学校编码
				School schoolCodeByPlatformIp = remoteSchoolService.getSchoolCodeByPlatformIp(eventNotify.getPlatformIp(), SecurityConstants.FROM_IN).getData();
				//将xml对象转为告警对象
				Alarm alarm = messageMethods.beanToAlarm(eventNotify);
				alarm.setSchoolId(schoolCodeByPlatformIp.getSchoolId());
				if ("".equals(alarm.getPicUrl())) {
					log.info("将告警信息存入数据库:{}", eventNotify);
					remoteAlarmService.saveAlarmInfo(alarm, SecurityConstants.FROM_IN);
				} else {
//					传入平台 ip以及port 以及图片name  获取 拼接好的图片地址
					String picUrl = messageMethods.getPicUrl(schoolCodeByPlatformIp.getAlarmPlatformIp(), schoolCodeByPlatformIp.getAlarmPicturePort(), alarm.getPicUrl());
//					将图片存入图片服务器并返回存入后的图片name
					String picName = messageMethods.saveToFile(picUrl, minioTemplate, MessageConstants.URL_PIC);
					//存入本地图片服务器的图片地址
					alarm.setPicUrl(MessageConstants.PIC_PREFIX + picName);
					log.info("将告警信息存入数据库:{}", eventNotify);
					remoteAlarmService.saveAlarmInfo(alarm, SecurityConstants.FROM_IN);
				}
				// 提交偏移量
				acknowledgment.acknowledge();
			} catch (Exception e) {
				log.error("处理抓拍机告警消息失败");
				throw e;
			}
		}
	}
}
