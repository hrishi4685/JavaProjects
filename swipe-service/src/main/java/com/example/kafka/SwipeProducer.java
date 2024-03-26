package com.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.basedomains.dto.SwipeEvent;

@Service
public class SwipeProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwipeProducer.class);

	private NewTopic topic;

	private KafkaTemplate<String, SwipeEvent> kafkaTemplate;

	public SwipeProducer(NewTopic topic, KafkaTemplate<String, SwipeEvent> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(SwipeEvent event) {
		LOGGER.info(String.format("Swipe event => %s", event.toString()));

		// create Message
		Message<SwipeEvent> message = MessageBuilder.withPayload(event).setHeader(KafkaHeaders.TOPIC, topic.name())
				.build();
		kafkaTemplate.send(message);
	}
}