package com.campus.message.consumer;

import com.campus.common.core.constant.SecurityConstants;
import com.campus.common.minio.service.MinioTemplate;
import com.campus.grid.api.entity.alarmManagement.Alarm;
import com.campus.grid.api.entity.blackalarm.BlackAlarm;
import com.campus.grid.api.entity.blackalarm.FenceAlarm;
import com.campus.grid.api.entity.emotion.mongo.MongoEmotionAlarm;
import com.campus.grid.api.entity.emotion.mongo.MongoEmotionPerson;
import com.campus.message.feign.RemoteAlarmService;
import com.campus.message.feign.RemoteGridService;
import com.campus.message.utils.MessageConstants;
import com.campus.message.utils.MessageMethods;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.UUID;


/**
 * @author eatheryu
 * message 消费者1 处理黑名单告警以及围栏告警
 * 接收设备信息
 */

@Slf4j
@Component
@AllArgsConstructor
public class MessageConsumer {
	private static JsonParser parser = new JsonParser();
	private static MessageMethods messageMethods = new MessageMethods();
	private final RemoteGridService remoteGridService;
	private final RemoteAlarmService remoteAlarmService;
	private final MinioTemplate minioTemplate;
	private Gson gson = new Gson();
	@Autowired
	private KafkaTemplate kafkaTemplate;

	//进行信息的消费
	@KafkaListener(topics = "${pro.topic1}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory", errorHandler = "consumerAwareErrorHandler")
	public void listen(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) throws Exception {
		log.info("kafkaConsumer111111111111 消费消息:{}", consumerRecord);
//		获取原始消息信息
		String equipmentMsg = consumerRecord.value();
		System.out.println(equipmentMsg);
//		获取原始消息中的  dataType 属性的值  1 = 设备信息 2 = 告警信息
		String dataType = null;
		try {
			JsonObject asJsonObject = parser.parse(equipmentMsg).getAsJsonObject();
			dataType = asJsonObject.get("dataType").getAsString();
		} catch (Exception e) {
			e.printStackTrace();
			acknowledgment.acknowledge();
		}
		//解析消息类型  =3  说明说黑名单告警 需调用告警接口
		if (MessageConstants.MSG_BLACK_ALARM.equals(dataType)) {
//			封装原始黑名单告警信息
			BlackAlarm blackAlarm = gson.fromJson(equipmentMsg, BlackAlarm.class);
//			重新封装黑名单中的person信息
			BlackAlarm newblackAlarm = messageMethods.beanToPerson(blackAlarm);
			log.info("黑名单信息:{}", blackAlarm);
			try {
				//根据学校编号（固定编号）查询到schoolId并添加
				String schoolId = remoteGridService.getSchoolIdByCode(blackAlarm.getSchoolId(), SecurityConstants.FROM_IN);
//			将原始告警信息封装为需要的告警信息
				Alarm alarm = messageMethods.beanToAlarm(blackAlarm);
				alarm.setSchoolId(schoolId);
//			将告警图片存入服务器
				String picName = messageMethods.saveToFile(blackAlarm.getPic_data(), minioTemplate, MessageConstants.URL_PIC);
				alarm.setPicUrl(MessageConstants.PIC_PREFIX + picName);
//				将告警信息存入数据库
				remoteAlarmService.saveAlarmInfo(alarm, SecurityConstants.FROM_IN);
//				将person信息存入数据库
				remoteAlarmService.saveBlackPerson(newblackAlarm.getPerson(), SecurityConstants.FROM_IN);
				log.info("将告警信息存入数据库:{}", alarm);
				// 提交偏移量
				acknowledgment.acknowledge();
			} catch (Exception e) {
				log.error("处理黑名单告警失败", e);
				throw e;
			}
		}

		if (MessageConstants.ELECTRONIC_FENCE.equals(dataType)) {
			try {
				//			封装原始围栏告警信息
				FenceAlarm fenceAlarm = gson.fromJson(equipmentMsg, FenceAlarm.class);
				log.info("围栏告警信息:{}", fenceAlarm);
				//根据学校编号（固定编号）查询到schoolId并添加
				String schoolId = remoteGridService.getSchoolIdByCode(fenceAlarm.getSchoolId(), SecurityConstants.FROM_IN);
				//			将原始告警信息封装为需要的告警信息
				Alarm alarm = messageMethods.beanToAlarm(fenceAlarm);
				alarm.setSchoolId(schoolId);
				//				将告警信息存入数据库
				remoteAlarmService.saveAlarmInfo(alarm, SecurityConstants.FROM_IN);
				log.info("将告警信息存入数据库:{}", alarm);
			} catch (JsonSyntaxException e) {
				log.error("处理围栏告警失败", e);
				throw e;
			}
		}

		//表情模块
        if(MessageConstants.EMOTION_ALARM.equals(dataType)){
            try {
                //封装数据
				MongoEmotionAlarm emotionAlarm = gson.fromJson(equipmentMsg, MongoEmotionAlarm.class);
                emotionAlarm.setEventID(emotionAlarm.getEventID().replace("-", ""));
                //没有告警级别为5的情况,所以将告警级别转换为1
				//C:\Windows\System32\drivers\etc
                if("5".equals(emotionAlarm.getEventLevel())){
                	emotionAlarm.setEventLevel("1");
				}
                log.info("表情告警信息:{}", emotionAlarm);

                //根据学校编号（固定编号）查询到schoolId并添加
                String schoolId = remoteGridService.getSchoolIdByCode(emotionAlarm.getSchoolId(), SecurityConstants.FROM_IN);
                //将原始告警信息封装为需要的告警信息
                Alarm alarm = messageMethods.beanToAlarm(emotionAlarm);
                if (schoolId != null) {
					alarm.setSchoolId(schoolId);
				}else {
                	alarm.setSchoolId("");
				}
                //封装人脸信息
				MongoEmotionPerson person = emotionAlarm.getPerson();
                person.setAlarmId(emotionAlarm.getEventID());
                person.setId(UUID.randomUUID().toString().replace("-", ""));

				System.out.println("kafka运行:"+emotionAlarm.getEventID()+"---"+ person);
                //将告警信息存入数据库中
                remoteAlarmService.saveAlarmInfo(alarm, SecurityConstants.FROM_IN);
                //将人脸数据存入数据库中
                remoteAlarmService.saveFaceInfo(person, SecurityConstants.FROM_IN);

                log.info("将表情识别信息存入数据库:{}", alarm);
                log.info("将人脸信息存入数据库:{}", person);

                //提交偏移量
                acknowledgment.acknowledge();
            }catch (Exception e){
                log.error("处理表情识别告警失败", e);
                throw e;
            }
        }
	}
}
