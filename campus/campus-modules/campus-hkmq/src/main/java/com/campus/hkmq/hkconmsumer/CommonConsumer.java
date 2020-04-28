//package com.campus.hkmq.hkconmsumer;
//
//
//import com.campus.grid.api.entity.hktonglinkmsg.EventNotify;
//import com.campus.hkmq.util.XstreamAnalysisUtil;
//import com.google.gson.Gson;
//import com.hikvision.ivms6.cms.core.mq.component.IMqComponent;
//import com.hikvision.ivms6.cms.core.mq.component.MqComponentFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jms.support.converter.SimpleMessageConverter;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//
//import javax.jms.JMSException;
//
//
///**
// * @author eatheryu
// * 普通消费者
// */
//@Slf4j
//public class CommonConsumer {
//
//	//初始化gson
//	private Gson gson = new Gson();
//
//	private KafkaTemplate kafkaTemplate;
//	private String topic;
//	private SimpleMessageConverter eventReportConvert;
//
//	private MqComponentFactory mqComponentFactory;
//
//	private IMqComponent mqComponent;
//
//	private String destionName;
//
//	private boolean topicFlag;
//
//
//	/**
//	 * 启动监听器
//	 */
//	public void start(KafkaTemplate kafkaTemplate, String topic) {
//
//		try {
//			this.kafkaTemplate = kafkaTemplate;
//			this.topic = topic;
//			getMqComponent().start();
//			log.info("开始消费:{}", mqComponentFactory.getBrokeUrl());
//		} catch (JMSException e) {
//			log.error("接收消息时出现异常", e);
//		}
//
//	}
//
//	/**
//	 * 停止监听器
//	 */
//	public void stop() {
//		try {
//			if (mqComponent != null) {
//				mqComponent.stop();
//			}
//		} catch (Exception e) {
//			log.error("停止监听器时出现异常", e);
//		}
//
//	}
//
//	/**
//	 * 接受处理日志消息recvLogMsgrecvLogMsg<bean id="eventReportProducer" class="com.hikvision.vmcc.hpp.producer.EventReportProducer" >
//	 * <property name="destionName" value="MyQueue" />
//	 * <property name="topicFlag" value="false" />
//	 * <property name="eventReportConvert" ref="eventReportConvert" />
//	 * <property name="mqComponentFactory" ref="mqComponentFactory" />
//	 * </bean>
//	 *
//	 * @param notify
//	 */
//	public void recvLogMsg(String notify) {
//		try {
//			log.info("消费信息内容{}", notify);
//			EventNotify eventNotify = XstreamAnalysisUtil.xmlToBean(notify);
//			if (null != eventNotify && !eventNotify.getEvent_type().equals("131331")) {
//				send(eventNotify);
//			}
//		} catch (Exception e) {
//			log.error("处理上报的日志信息时出现异常", e);
//		}
//	}
//
//	/**
//	 * 向kafka服务器发送消息
//	 */
//	public void send(EventNotify eventNotify) {
////		获取平台该消息所属的 ip以及 port
//		String brokeUrl = mqComponentFactory.getBrokeUrl();
//		String[] url = brokeUrl.split("//");
//		String[] ipAndPort = url[1].split(":");
////		设置消息类型
//		eventNotify.setDataType("2");
////		设置消息所属平台IP以及Port
//		eventNotify.setPlatformIp(ipAndPort[0]);
//		eventNotify.setPlatformPort(ipAndPort[1]);
//		String eventNotifyMsg = gson.toJson(eventNotify);
//		ListenableFuture listenableFuture = kafkaTemplate.send(topic, eventNotifyMsg);
//		listenableFuture.addCallback(new ListenableFutureCallback() {
//			@Override
//			public void onFailure(Throwable throwable) {
//				log.error("发送消息失败：{}", throwable);
//			}
//
//			@Override
//			public void onSuccess(Object o) {
//				log.info("发送消息成功：{}", o);
//			}
//		});
//	}
//
//	public SimpleMessageConverter getEventReportConvert() {
//		return eventReportConvert;
//	}
//
//	public void setEventReportConvert(SimpleMessageConverter eventReportConvert) {
//		this.eventReportConvert = eventReportConvert;
//	}
//
//	public MqComponentFactory getMqComponentFactory() {
//		return mqComponentFactory;
//	}
//
//	public void setMqComponentFactory(MqComponentFactory mqComponentFactory) {
//		this.mqComponentFactory = mqComponentFactory;
//	}
//
//	public IMqComponent getMqComponent() throws JMSException {
//		if (mqComponent == null) {
//			mqComponent = mqComponentFactory.createMqComponent(destionName, topicFlag);
//			mqComponent.setBizListener(this);
//			mqComponent.setMsgConverter(eventReportConvert);
//			mqComponent.setMethodName("recvLogMsg");
//		}
//		return mqComponent;
//
//	}
//
//	public void setMqComponent(IMqComponent mqComponent) {
//		this.mqComponent = mqComponent;
//	}
//
//	public String getDestionName() {
//		return destionName;
//	}
//
//	public void setDestionName(String destionName) {
//		this.destionName = destionName;
//	}
//
//	public boolean isTopicFlag() {
//		return topicFlag;
//	}
//
//	public void setTopicFlag(boolean topicFlag) {
//		this.topicFlag = topicFlag;
//	}
//}
//



//
//
//package com.campus.hkmq.hkconmsumer;
//
//
//import com.campus.grid.api.entity.hktonglinkmsg.EventNotify;
//import com.campus.hkmq.util.XstreamAnalysisUtil;
//import com.google.gson.Gson;
//import com.hikvision.ivms6.cms.core.mq.component.IMqComponent;
//import com.hikvision.ivms6.cms.core.mq.component.MqComponentFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jms.support.converter.SimpleMessageConverter;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.ListenableFutureCallback;
//
//import javax.jms.JMSException;
//
///*
//
//*
// * @author eatheryu
// * 普通消费者
//
//*/
//
//@Slf4j
//public class CommonConsumer {
//
//	//初始化gson
//	private Gson gson = new Gson();
//
//	private KafkaTemplate kafkaTemplate;
//	private String topic;
//	private SimpleMessageConverter eventReportConvert;
//
//	private MqComponentFactory mqComponentFactory;
//
//	private IMqComponent mqComponent;
//
//	private String destionName;
//
//	private boolean topicFlag;
//
//
//	// 启动监听器
//
//
//	public void start(KafkaTemplate kafkaTemplate, String topic) {
//
//		try {
//			this.kafkaTemplate = kafkaTemplate;
//			this.topic = topic;
//			getMqComponent().start();
//			log.info("开始消费:{}", mqComponentFactory.getBrokeUrl());
//		} catch (JMSException e) {
//			log.error("接收消息时出现异常", e);
//		}
//
//	}
//
//	//停止监听器
//
//
//	public void stop() {
//		try {
//			if (mqComponent != null) {
//				mqComponent.stop();
//			}
//		} catch (Exception e) {
//			log.error("停止监听器时出现异常", e);
//		}
//
//	}
//
//	/*
//	 * 接受处理日志消息recvLogMsgrecvLogMsg<bean id="eventReportProducer" class="com.hikvision.vmcc.hpp.producer.EventReportProducer" >
//	 * <property name="destionName" value="MyQueue" />
//	 * <property name="topicFlag" value="false" />
//	 * <property name="eventReportConvert" ref="eventReportConvert" />
//	 * <property name="mqComponentFactory" ref="mqComponentFactory" />
//	 * </bean>
//	 *
//	 * @param notify
//	 */
//
//	public void recvLogMsg(String notify) {
//		try {
//			log.info("消费信息内容{}", notify);
//			EventNotify eventNotify = XstreamAnalysisUtil.xmlToBean(notify);
//			if (null != eventNotify && !eventNotify.getEvent_type().equals("131331")) {
//				send(eventNotify);
//			}
//		} catch (Exception e) {
//			log.error("处理上报的日志信息时出现异常", e);
//		}
//	}
//
//// 向kafka服务器发送消息
//
//
//	public void send(EventNotify eventNotify) {
////		获取平台该消息所属的 ip以及 port
//		String brokeUrl = mqComponentFactory.getBrokeUrl();
//		String[] url = brokeUrl.split("//");
//		String[] ipAndPort = url[1].split(":");
////		设置消息类型
//		eventNotify.setDataType("2");
////		设置消息所属平台IP以及Port
//		eventNotify.setPlatformIp(ipAndPort[0]);
//		eventNotify.setPlatformPort(ipAndPort[1]);
//		String eventNotifyMsg = gson.toJson(eventNotify);
//		ListenableFuture listenableFuture = kafkaTemplate.send(topic, eventNotifyMsg);
//		listenableFuture.addCallback(new ListenableFutureCallback() {
//			@Override
//			public void onFailure(Throwable throwable) {
//				log.error("发送消息失败：{}", throwable);
//			}
//
//			@Override
//			public void onSuccess(Object o) {
//				log.info("发送消息成功：{}", o);
//			}
//		});
//	}
//
//	public SimpleMessageConverter getEventReportConvert() {
//		return eventReportConvert;
//	}
//
//	public void setEventReportConvert(SimpleMessageConverter eventReportConvert) {
//		this.eventReportConvert = eventReportConvert;
//	}
//
//	public MqComponentFactory getMqComponentFactory() {
//		return mqComponentFactory;
//	}
//
//	public void setMqComponentFactory(MqComponentFactory mqComponentFactory) {
//		this.mqComponentFactory = mqComponentFactory;
//	}
//
//	public IMqComponent getMqComponent() throws JMSException {
//		if (mqComponent == null) {
//			mqComponent = mqComponentFactory.createMqComponent(destionName, topicFlag);
//			mqComponent.setBizListener(this);
//			mqComponent.setMsgConverter(eventReportConvert);
//			mqComponent.setMethodName("recvLogMsg");
//		}
//		return mqComponent;
//
//	}
//
//	public void setMqComponent(IMqComponent mqComponent) {
//		this.mqComponent = mqComponent;
//	}
//
//	public String getDestionName() {
//		return destionName;
//	}
//
//	public void setDestionName(String destionName) {
//		this.destionName = destionName;
//	}
//
//	public boolean isTopicFlag() {
//		return topicFlag;
//	}
//
//	public void setTopicFlag(boolean topicFlag) {
//		this.topicFlag = topicFlag;
//	}
//}
