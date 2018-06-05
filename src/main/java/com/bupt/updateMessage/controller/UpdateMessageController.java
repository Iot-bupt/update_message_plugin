package com.bupt.updateMessage.controller;


import com.bupt.updateMessage.data.UpdateMessage;
import com.bupt.updateMessage.service.UpdateMessageService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/plugin")
public class UpdateMessageController {

    @Autowired
    UpdateMessageService updateMessageService;

    @RequestMapping(value = "/updateMessage/insert", method = RequestMethod.POST)
    public UpdateMessage insertMessage(@RequestBody String msg){

        JsonObject jsonObj = (JsonObject)new JsonParser().parse(msg);
        UpdateMessage addMsg = new UpdateMessage(jsonObj);
        UpdateMessage insertmsg = updateMessageService.insertMessage(addMsg);
        return insertmsg;
    }

    @RequestMapping(value = "/updateMesage/{messageType}", method = RequestMethod.GET)
    public List<UpdateMessage> getUpdateMessageByType(@PathVariable("messageType") String msgType){
        List<UpdateMessage> updateMessages = updateMessageService.getUpdateMessageByType(msgType);
        return updateMessages;
    }

}
