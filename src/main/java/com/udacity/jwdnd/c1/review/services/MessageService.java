package com.udacity.jwdnd.c1.review.services;

import com.udacity.jwdnd.c1.review.mapper.MessageMapper;
import com.udacity.jwdnd.c1.review.model.ChatForm;
import com.udacity.jwdnd.c1.review.model.ChatMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {


    private final MessageMapper messageMapper;


    public MessageService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }



    public List<ChatMessage> getChatMessages(){
        return messageMapper.getAllMessages();
    }


    public void addMessage(ChatForm chatForm){

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUsername(chatForm.getUsername());

        switch (chatForm.getMessageType()){
            case "Say":chatMessage.setMessagetext(chatForm.getMessageText());break;
            case "Whisper":chatMessage.setMessagetext(chatForm.getMessageText().toLowerCase());break;
            case "Shout":chatMessage.setMessagetext(chatForm.getMessageText().toUpperCase());break;
        }

        if (chatMessage.getMessagetext().toLowerCase().contains("fuck")){
            chatMessage.setMessagetext("This Message is not displayed cause rude expressions were used");
        }

        this.messageMapper.createMessage(chatMessage);

    }
    /*String uppercase(){
        return this.message.toUpperCase();
    }*/

/*    String lowercase(){
        return  this.message.toLowerCase();
    }*/
}
