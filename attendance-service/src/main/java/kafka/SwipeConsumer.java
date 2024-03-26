package kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.basedomains.dto.SwipeEvent;



@Service
public class SwipeConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwipeConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}"
            ,groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(SwipeEvent event){
        LOGGER.info(String.format("Swipe event received in Attendance service => %s", event.toString()));

        // send an email to the customer
    }
}
