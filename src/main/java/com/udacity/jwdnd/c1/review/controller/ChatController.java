package com.udacity.jwdnd.c1.review.controller;

import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping()
    public String loadChatPage(@ModelAttribute("chatForm") ChatForm chatForm, Model model){
        model.addAttribute("chats",messageService.getChats());
        return "chat";
    }

    @PostMapping()
    public String submitMessage(@ModelAttribute("chatForm") ChatForm chatForm, Model model){
        messageService.addMessageToChat(chatForm);
        model.addAttribute("chats",messageService.getChats());
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes(){
        return new String[]{"Say","Shout","Whisper"};
    }
}
