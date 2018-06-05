package com.bupt.updateMessage.service;

import com.bupt.updateMessage.data.UpdateMessage;
import com.bupt.updateMessage.mapper.UpdateMessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@CacheConfig(cacheNames="messageCache")
public class UpdateMessageService {

    @Autowired
    UpdateMessageMapper updateMessageMapper;

    @CachePut(key = "#messageType")
    public UpdateMessage insertMessage(UpdateMessage updateMessage){
        int id = this.updateMessageMapper.addAMessage(updateMessage);
        return this.updateMessageMapper.getMessageById(id);
    }

    @Cacheable(key = "#messageType")
    public List<UpdateMessage> getUpdateMessageByType(String messageType){
        return null;
    }
}
