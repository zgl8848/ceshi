//package com.campus.hkmq.hkmqlistener;
//
//import com.campus.hkmq.configure.HKCommonConsumer;
//import com.campus.hkmq.configure.IpAndPort;
//import com.campus.hkmq.configure.PlatformMessage;
//import com.campus.hkmq.hkconmsumer.CommonConsumer;
//import com.hikvision.ivms6.cms.core.mq.component.MqComponentFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @author eatheryu
// * HKmq消息监听器。
// */
//@Slf4j
//@Component
//public class ListenerHKmqConsumer implements CommandLineRunner {
//	@Autowired
//	private HKCommonConsumer hkCommonConsumer;
//	@Autowired
//	private PlatformMessage platformMessage;
//	@Autowired
//	private KafkaTemplate kafkaTemplate;
//
//	@Value("${pro.topic2}")
//	private String topic;
//
//	@Override
//	public void run(String... args) throws Exception {
//		List<IpAndPort> info = platformMessage.getInfo();
//		try {
//			for (IpAndPort ipAndPort : info) {
//				MqComponentFactory mqComponentFactory = hkCommonConsumer.mqComponentFactory();
//				mqComponentFactory.setBrokeUrl("tlkq://" + ipAndPort.getPlatformIp() + ":" + ipAndPort.getPlatformPort() + "");
//				CommonConsumer commonConsumer = hkCommonConsumer.commonConsumer();
//				commonConsumer.setMqComponentFactory(mqComponentFactory);
//				log.info("开启hkmq的消费者:{}", commonConsumer.getMqComponentFactory().getBrokeUrl());
//				commonConsumer.start(kafkaTemplate, topic);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error("开启监听器失败", e);
//		}
//	}
//}
