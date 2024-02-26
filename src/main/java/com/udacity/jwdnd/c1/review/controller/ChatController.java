package com.udacity.jwdnd.c1.review.controller;


import com.udacity.jwdnd.c1.review.services.MessageService;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.services.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/chat")
public class ChatController {
    public ChatController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }


    MessageService messageService;
    UserService userService;
/*
    @PostMapping("/logout")
    public String logOut(){


    }*/


    @PostMapping
    public String addMessage(ChatForm chatForm, Model model){

        chatForm.setUsername( userService.getCurrentUser());
        messageService.addMessage(chatForm);
        model.addAttribute("chatMessages", messageService.getChatMessages());
        return "chat";
    }

    @GetMapping
    public String show(ChatForm chatForm, Model model){
        model.addAttribute("chatMessages",messageService.getChatMessages());
        return "chat";
    }

    //wenn wir attribute in einem Model zurückgeben wollen welche nicht im POJO Vorhanden sind
    @ModelAttribute("allMessageTypes")
    public String[] allMessageTypes(){
        String[] types = new String[]{"Say", "Shout", "Whisper"};
        // Zum Beispiel Logging hier, um sicherzustellen, dass diese Methode aufgerufen wird
        System.out.println("Message Types: " + Arrays.toString(types));
        return types;    }

}
