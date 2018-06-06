package com.bupt.updateMessage.service;

import com.bupt.updateMessage.data.UpdateMessage;
import com.bupt.updateMessage.mapper.UpdateMessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@Service
//@CacheConfig(cacheNames="messageCache")
//@Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
public class UpdateMessageService {

    @Autowired
    UpdateMessageMapper updateMessageMapper;

    //@CachePut(value = "messageCache", key = "#updateMessage.messageType") // id + type
    public UpdateMessage insertMessage(UpdateMessage updateMessage){
        this.updateMessageMapper.addAMessage(updateMessage);
        return updateMessage;
    }

    //@Cacheable(value = "messageCache", key = "#messageType")
    public List<UpdateMessage> getUpdateMessageByType(String messageType){
        return this.updateMessageMapper.getMessageByType(messageType);
    }

    public List<UpdateMessage> getAllMessage(){
        return this.updateMessageMapper.getAllMessage();
    }

    public List<UpdateMessage> getFromWebMessage(){
        return this.updateMessageMapper.getFromWebMessage();
    }

    public List<UpdateMessage> getFromModuleMessage(){
        return this.updateMessageMapper.getFromModuleMessage();
    }

    public List<UpdateMessage> getTsMessage(BigInteger startTs, BigInteger endTs){
        return this.updateMessageMapper.getTsMessage(startTs, endTs);
    }

    public UpdateMessage getMessageById(int id){
        return this.updateMessageMapper.getMessageById(id);
    }

    public void removeAllMessage(){
        this.updateMessageMapper.removeAllMessage();
    }

    public void removeMessageById(int id){
        this.updateMessageMapper.removeMessageById(id);
    }


    public boolean checkMessageType(UpdateMessage updateMessage){
        if(updateMessage.getMessageType().equals("fromModule")){
            return true;
        } else {
            return false;
        }
    }
}
