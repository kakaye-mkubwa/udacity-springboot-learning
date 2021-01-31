package com.udacity.jwdnd.c1.review.service;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<ChatForm> chatFormList;

    public MessageService() {
        this.chatFormList = new ArrayList<>();
    }

    public String uppercase(String message){
        return message.toUpperCase();
    }

    public String lowercase(String message){
        return message.toLowerCase();
    }

    public void addMessageToChat(ChatForm chatForm){
        chatFormList.add(chatForm);
    }

    public String handleMessageType(ChatForm chatForm){
        String message = "";
        switch (chatForm.getMessageType()){
            case "Say":
                message = chatForm.getMessage();
                break;
            case "Shout":
                message = uppercase(chatForm.getMessage());
                break;
            case "Whisper":
                message = lowercase(chatForm.getMessage());
                break;
            default:
                break;
        }
        return message;
    }

    public String getChats(){
        if (!chatFormList.isEmpty()){
            StringBuilder chatsStringBuilder = new StringBuilder();
            for (ChatForm chatForm:chatFormList) {
                chatsStringBuilder.append(chatForm.getUsername()).append(" -> ").append(handleMessageType(chatForm)).append("\n");
            }

            return chatsStringBuilder.toString();
        }else{
            return "null";
        }
    }


}
