package com.bupt.updateMessage.KafkaProducer;

import com.bupt.updateMessage.data.UpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public String kafkaProduce(UpdateMessage updateMessage){
        kafkaTemplate.send("updateMessage","",updateMessage);
        return  "success";
    }
}
