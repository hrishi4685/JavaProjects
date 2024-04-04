package com.example.swipeservice1.kafka;

import java.util.List;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.basedomains.dto.Attendance;
import com.example.swipeservice1.jpa.SwipeDetails;

@Service
public class SwipeProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwipeProducer.class);

	private NewTopic topic;

	private KafkaTemplate<String, SwipeDetails> kafkaTemplate;

	public SwipeProducer(NewTopic topic, KafkaTemplate<String, SwipeDetails> kafkaTemplate) {
		this.topic = topic;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(List<Attendance> event) {
		LOGGER.info(String.format("Swipe event => %s", event.toString()));

		// create Message
		
		event.stream().forEach(e->{
			
			Message<Attendance> message = MessageBuilder.withPayload(e).setHeader(KafkaHeaders.TOPIC, topic.name())
					.build();
			kafkaTemplate.send(message);
			
		});
		
	}
}