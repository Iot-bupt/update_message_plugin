package com.bupt.updateMessage.controller;


import com.bupt.updateMessage.KafkaProducer.KafkaProducer;
import com.bupt.updateMessage.common.Constant;
import com.bupt.updateMessage.data.UpdateMessage;
import com.bupt.updateMessage.service.CheckAndSendMessage;
import com.bupt.updateMessage.service.UpdateMessageService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;


@RestController
@RequestMapping("/api/plugin")
public class UpdateMessageController {

    @Autowired
    UpdateMessageService updateMessageService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    CheckAndSendMessage checkAndSendMessage;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate ;

    @RequestMapping(value = "/updateMessage/insert", method = RequestMethod.POST)
    public String insertMessage(@RequestBody String msg){

        JsonObject jsonObj = (JsonObject)new JsonParser().parse(msg);
        UpdateMessage addMsg = new UpdateMessage(jsonObj);
        UpdateMessage insertmsg = updateMessageService.insertMessage(addMsg);

        checkAndSendMessage.checkAndSendMessage(insertmsg);

        return "success";
    }

    @RequestMapping(value = "/updateMessage/{messageType}", method = RequestMethod.GET)
    public List<UpdateMessage> getUpdateMessageByType(@PathVariable("messageType") String msgType){
        List<UpdateMessage> updateMessages = updateMessageService.getUpdateMessageByType(msgType);
        return updateMessages;
    }

    @RequestMapping(value = "/updateMessage/fromWeb", method = RequestMethod.GET)
    public List<UpdateMessage> getFromWebMessage(){
        List<UpdateMessage> updateMessages = updateMessageService.getFromWebMessage();
        return updateMessages;
    }

    @RequestMapping(value = "/updateMessage/fromModule", method = RequestMethod.GET)
    public List<UpdateMessage> getFromModuleMessage(){
        List<UpdateMessage> updateMessages = updateMessageService.getFromModuleMessage();
        return updateMessages;
    }

    @RequestMapping(value = "/allUpdateMessage", method = RequestMethod.GET)
    public List<UpdateMessage> getAllMessage(){
        List<UpdateMessage> updateMessages = updateMessageService.getAllMessage();
        return updateMessages;
    }

    @RequestMapping(value = "/updateMessage/{id}", method = RequestMethod.GET)
    public UpdateMessage getMessageById(@PathVariable("id") Integer id){
        UpdateMessage updateMessage = updateMessageService.getMessageById(id);
        return updateMessage;
    }

    @RequestMapping(value = "/updateMessage/{startTs}/{endTs}", method = RequestMethod.GET)
    public List<UpdateMessage> getTsMessage(@PathVariable("startTs") BigInteger startTs,
                                              @PathVariable("endTs") BigInteger endTs){
        List<UpdateMessage> updateMessages = updateMessageService.getTsMessage(startTs, endTs);
        return updateMessages;
    }

    @RequestMapping(value = "/removeAllMessage", method = RequestMethod.DELETE)
    public void removeAllMessage(){
        updateMessageService.removeAllMessage();
    }

    @RequestMapping(value = "/removeMessage/{id}", method = RequestMethod.DELETE)
    public void removeMessageById(@PathVariable("id") Integer id){
        updateMessageService.removeMessageById(id);
    }

    @MessageMapping("/fromModule")
    public void getFromModuleMsgBySocket(){
        List<UpdateMessage> updateMessages = updateMessageService.getFromModuleMessage();
       /* for(UpdateMessage updateMessage:updateMessages){
            simpMessagingTemplate.convertAndSend(Constant.SOCKET_UPDATEMESSAGE_RESPONSE+"/fromModule", updateMessage);
        }*/
        simpMessagingTemplate.convertAndSend(Constant.SOCKET_UPDATEMESSAGE_RESPONSE+"/fromModule", updateMessages);
    }

    @MessageMapping("/fromWeb")
    public void getFromWebMsgBySocket(){
        List<UpdateMessage> updateMessages = updateMessageService.getFromWebMessage();
       /* for(UpdateMessage updateMessage:updateMessages){
            simpMessagingTemplate.convertAndSend(Constant.SOCKET_UPDATEMESSAGE_RESPONSE+"/fromModule", updateMessage);
        }*/
        simpMessagingTemplate.convertAndSend(Constant.SOCKET_UPDATEMESSAGE_RESPONSE+"/fromWeb", updateMessages);
    }

}
