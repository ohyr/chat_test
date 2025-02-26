package com.example.chat_backend.Listener;

import com.example.chat_backend.constant.KafkaConstants;
import com.example.chat_backend.db.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message){
        System.out.println(message.toString()+"메시지 수신");
        template.convertAndSend("/topic/"+message.getServer(), message);

    }

}
/*
@KafkaListener 어노테이션을 통해 Kafka로부터 메시지를 받을 수 있음

template.convertAndSend를 통해 WebSocket으로 메시지를 전송

Message를 작성할 때 경로 잘 보고 import하시길...
 */