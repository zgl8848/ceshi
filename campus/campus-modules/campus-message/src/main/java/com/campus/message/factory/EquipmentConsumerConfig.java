package com.campus.message.factory;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author eatheryu
 * 消息监听器容器配置类
 */
@Configuration
@EnableKafka
public class EquipmentConsumerConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String servers;
	@Value("${spring.kafka.consumer.group-id}")
	private String groupId;
	@Value("${spring.kafka.consumer.enable-auto-commit}")
	private String enableAutoCommit;
	@Value("${spring.kafka.consumer.key-deserializer}")
	private String key_Deserializer;
	@Value("${spring.kafka.consumer.value-deserializer}")
	private String value_Deserializer;
	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String auto_offset_reset;
	@Value("${spring.kafka.consumer.max-poll-records}")
	private String max_poll_records;

	/**
	 * 多线程监听器工厂容器
	 *
	 * @return KafkaListenerContainerFactory
	 */
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		factory.setConcurrency(3);
		factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);
		return factory;
	}

	/**
	 * 消费者工厂
	 *
	 * @return ConsumerFactory
	 */
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerconfigs());
	}


	/**
	 * 消费者工厂配置
	 *
	 * @return Map
	 */
	public Map<String, Object> consumerconfigs() {
		HashMap<String, Object> prop = new HashMap<>();
		prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		prop.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, key_Deserializer);
		prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, value_Deserializer);
		prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
		prop.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 300000);
		prop.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 600000);
		prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, auto_offset_reset);
		prop.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, max_poll_records);
		prop.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, -1);
		return prop;
	}

}
