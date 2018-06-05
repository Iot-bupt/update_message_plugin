package com.bupt.updateMessage.mapper;

import com.bupt.updateMessage.data.UpdateMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UpdateMessageMapper {
    @Insert("INSERT INTO update_message (message, messageType, ts) VALUES (#{message}, #{messageType}, #{ts})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int addAMessage(UpdateMessage updateMessage);

    @Select("SELECT * FROM update_message")
    List<UpdateMessage> getAllMessage();

    @Select("SELECT * FROM update_message where messageType = #{messageType}")
    List<UpdateMessage> getMessageByType(@Param("messageType")String messageType);

    @Select("SELECT * FROM update_message where id = #{id}")
    UpdateMessage getMessageById(@Param("id") Integer id );

    @Delete("DELETE FROM update_message")
    void removeAllMessage();
}
