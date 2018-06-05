package com.bupt.updateMessage.controller;


import com.alibaba.fastjson.JSON;
import com.bupt.updateMessage.data.UpdateMessage;
import com.bupt.updateMessage.service.UpdateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/plugin")
public class UpdateMessageController {

    @Autowired
    UpdateMessageService updateMessageService;

    @RequestMapping(value = "/updateMessage/insert", method = RequestMethod.POST)
    public UpdateMessage insertMessage(@RequestBody String msg){

        UpdateMessage msg1 = JSON.parseObject(msg, UpdateMessage.class);
        UpdateMessage insertmsg = updateMessageService.insertMessage(msg1);
        return insertmsg;
    }

}
