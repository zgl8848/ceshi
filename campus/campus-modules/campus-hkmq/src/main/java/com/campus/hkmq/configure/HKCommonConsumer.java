//package com.campus.hkmq.configure;
//
//import com.campus.hkmq.hkconmsumer.CommonConsumer;
//import com.campus.hkmq.simplemessageconverter.HKSimpleMessageConverter;
//import com.hikvision.ivms6.cms.core.mq.component.MqComponentFactory;
//import org.springframework.stereotype.Component;
//
//
///**
// * @author eatheryu
// */
//@Component
//public class HKCommonConsumer {
//
//
//	public MqComponentFactory mqComponentFactory() {
//		MqComponentFactory mqComponentFactory = new MqComponentFactory();
//		mqComponentFactory.setBrokeUrl("tlkq://192.168.0.164:11024");
//		mqComponentFactory.setMqComponentType("tonglinkq");
//		mqComponentFactory.setJndiConnectFactoryName("RemoteConnectionFactory");
//		return mqComponentFactory;
//	}
//
//	public CommonConsumer commonConsumer() {
//		CommonConsumer commonConsumer = new CommonConsumer();
//		commonConsumer.setDestionName("usp.eds.publish.topic");
//		commonConsumer.setTopicFlag(true);
//		commonConsumer.setEventReportConvert(new HKSimpleMessageConverter());
//		commonConsumer.setMqComponentFactory(this.mqComponentFactory());
//		return commonConsumer;
//	}
//}
