package com.campus.message.consumerexcption;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 * @author eatheryu
 * consumer 异常处理器
 */
@Slf4j
@Component
public class ConsumerAwareErrorHandlers {
	@Setter
	private Throwable cause;

	@Bean
	public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
		return (message, e, consumer) -> {
			MessageHeaders headers = message.getHeaders();
			String topic = headers.get(KafkaHeaders.RECEIVED_TOPIC, String.class);
			Integer partition_id = headers.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class);
			Long offset = headers.get(KafkaHeaders.OFFSET, Long.class);
			log.error("kafkaConsumer 处理信息数据失败异常处理器捕获进行重新消费:{}", message, e);
			// 重新将offset重置到发生错误的位置.重新消费
			consumer.seek(new TopicPartition(topic, partition_id), offset);
			return null;
		};
	}
}
