package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity registerAccount(@RequestBody Account account){
        //Account userTaken = accountService.getAccount(account);
        System.out.println(accountService.hasAccountName(account.getUsername()));
        if(!accountService.hasAccountName(account.getUsername())){
            return ResponseEntity.status(409).body(null);
        }
        else{
            Account newAccount = accountService.Register(account);
            System.out.println(newAccount);
            if(newAccount!=null){
                ResponseEntity.status(200).body(newAccount);
            }
            else{
                ResponseEntity.status(400).body(null);
            }
        }
        return ResponseEntity.status(200).body(account);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account account){
        Account login = accountService.getAccount(account);
        if(login != null){
            return ResponseEntity.status(200).body(login);
        }
            return ResponseEntity.status(401).body(null);
    }

    @PostMapping("/messages")
    public ResponseEntity postMessage(@RequestBody Message message){
        Message newMessage = messageService.postMessage(message);
        if(newMessage != null){
            return ResponseEntity.status(200).body(newMessage);
        }
            return ResponseEntity.status(400).body(null);
    }

    @GetMapping("/messages")
    public ResponseEntity getAllMessages(){
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messages);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity getMessageById(@PathVariable int messageId){
        Message message = messageService.getMessageById(messageId);
        return ResponseEntity.status(200).body(message);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessageById(@PathVariable int messageId){
        Message oldmessage = messageService.getMessageById(messageId);

        if(oldmessage != null){
            Message newMessage = messageService.deletMessage(messageId);
            return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(200).body(null);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateMessage(@RequestBody Message message, @PathVariable int messageId){
        Message newmMessage = messageService.updateMessage(message, messageId);
        if(newmMessage != null){
            return ResponseEntity.status(200).body(1);
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity getAllMessagesFromUser(@PathVariable int accountId){
        List<Message> messages = messageService.getMessageByAccount(accountId);
        return ResponseEntity.status(200).body(messages);
    }
}
