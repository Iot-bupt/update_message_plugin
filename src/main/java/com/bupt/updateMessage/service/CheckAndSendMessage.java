package com.bupt.updateMessage.service;

import com.bupt.updateMessage.common.Constant;
import com.bupt.updateMessage.data.UpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class CheckAndSendMessage {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate ;

    public String checkAndSendMessage(UpdateMessage message){
        switch (message.getMessageType()){
            case "fromModule":
                simpMessagingTemplate.convertAndSend(Constant.SOCKET_UPDATEMESSAGE_RESPONSE+"/fromModule", message);
                break;
            case "fromWeb":
                simpMessagingTemplate.convertAndSend(Constant.SOCKET_UPDATEMESSAGE_RESPONSE+"/fromWeb", message);
                break;
            case "exception":
                simpMessagingTemplate.convertAndSend(Constant.SOCKET_UPDATEMESSAGE_RESPONSE+"/exception", message);
                break;
        }
        return "success";
    }
}
