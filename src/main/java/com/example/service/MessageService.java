package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository,AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }
    
    public Message postMessage(Message message){
        Account account = accountRepository.findAccount(message.getPostedBy());
        System.out.println(account);
        if(message.getMessageText() != "" && message.getMessageText().length() < 255 && account != null){
            messageRepository.save(message);
            return message;
        }
        return null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int id){
        Message optMessage = messageRepository.findMessageById(id);
        if(optMessage == null){
            return null;
        }
        return optMessage;
    }

    public Message deletMessage(int id){
        Message message = this.getMessageById(id);
        if(message != null){
            messageRepository.delete(message);
            return message;
        }
        return null;
        
    }

    public Message updateMessage(Message message, int id){
        Message oldMessage = this.getMessageById(id);
        if(oldMessage != null && message.getMessageText() != "" && message.getMessageText().length() < 255){
            Message newMessage = oldMessage;
            newMessage.setMessageText(message.getMessageText());
            messageRepository.save(newMessage);
            return newMessage;
        }
        return null;
    }

    public List<Message> getMessageByAccount(int id){
        return messageRepository.findMessageByPoster(id);
    }
}
