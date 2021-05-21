package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Insert("INSERT INTO MESSAGES(username,messagetext,messagetype) VALUES (#{username},#{messagetext},#{messageType})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insertMessage(ChatForm chatForm);

    @Select("SELECT * FROM MESSAGES WHERE messageid = #{messageId}")
    ChatForm getMessage(String messageId);

    @Select("SELECT * FROM MESSAGES")
    List<ChatForm> getAllMessages();
}
