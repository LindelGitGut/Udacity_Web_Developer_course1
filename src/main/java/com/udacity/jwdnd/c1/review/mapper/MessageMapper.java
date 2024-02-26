package com.udacity.jwdnd.c1.review.mapper;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Mapper
public interface MessageMapper {

    @Insert("INSERT INTO MESSAGES( username, messagetext)" +
            "VALUES ( #{username}, #{messagetext})")
    @Options(useGeneratedKeys = true, keyProperty = "messageid")
    int createMessage(ChatMessage chatMessage);

    @Delete("DELETE FROM MESSAGES WHERE messageid = #{messageid}")
    int deleteMessage(int messageid);

    @Select("SELECT FROM MESSAGES WHERE messageid = #{messageid}")
    ChatMessage getMessage (ChatMessage chatMessage);

    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getAllMessages();


}
