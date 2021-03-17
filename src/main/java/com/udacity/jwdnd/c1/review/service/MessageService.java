package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.mapper.MessageMapper;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<ChatForm> chatFormList;
    private MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper) {
        this.chatFormList = new ArrayList<>();
        this.messageMapper = messageMapper;
    }

    public String uppercase(String message){
        return message.toUpperCase();
    }

    public String lowercase(String message){
        return message.toLowerCase();
    }

    public int addMessageToChat(ChatForm chatForm){
//        chatFormList.add(chatForm);
        String message = handleMessageType(chatForm);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        chatForm.setUsername(authentication.getPrincipal().toString());
        chatForm.setMessagetext(message);
        return messageMapper.insertMessage(chatForm);
//        String chatResponse = null;
//
//        if (messageSent < 0){
//            chatResponse = "Failed sending message. Please retry";
//        }
//
//        if (chatResponse != null){
//
//        }else {
//
//        }
    }

    public String handleMessageType(ChatForm chatForm){
        String message = "";
        switch (chatForm.getMessageType()){
            case "Say":
                message = chatForm.getMessagetext();
                break;
            case "Shout":
                message = uppercase(chatForm.getMessagetext());
                break;
            case "Whisper":
                message = lowercase(chatForm.getMessagetext());
                break;
            default:
                break;
        }
        return message;
    }

    public String getChats(){
        chatFormList = messageMapper.getAllMessages();

        if (chatFormList != null){
            if (!chatFormList.isEmpty()){
                StringBuilder chatsStringBuilder = new StringBuilder();
                for (ChatForm chatForm:chatFormList) {
                    chatsStringBuilder.append(chatForm.getUsername()).append(" -> ").append(chatForm.getMessagetext()).append("\n");
                }
                return chatsStringBuilder.toString();
            }else{
                return "null";
            }
        }else{
            return "null";
        }
    }


}
