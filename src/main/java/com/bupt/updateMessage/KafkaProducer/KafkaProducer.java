package com.bupt.updateMessage.KafkaProducer;

import com.bupt.updateMessage.data.UpdateMessage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public String kafkaProduce(UpdateMessage updateMessage){
        Gson gs = new Gson();
        String ObjectStr = gs.toJson(updateMessage);

        kafkaTemplate.send("updateMessage","",ObjectStr);
        return  "success";
    }
}
