package com.api.back.service.User;

import com.api.back.entities.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class EventService implements IEventService {
    @Autowired
    private KafkaTemplate<String, String> _kafka;
    @Value("${topic.name}")
    private String topic;

    @Override
    public void send(String topic, String event) {

        _kafka.send(topic, event);
    }

   @KafkaListener(topics = "${topic.name}", groupId = "ms-back")
   public void consume(ConsumerRecord<String, User> event)
   {
       System.out.println("nossa api ->" + event.value());
   }
}
