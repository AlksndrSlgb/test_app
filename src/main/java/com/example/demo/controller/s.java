package com.example.demo.controller;


import com.example.demo.entity.dto.MessageDto;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/api/message")
public class s {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<?> saveOrGetMessage(@RequestBody MessageDto message) {
        if (!message.getMessage().startsWith("history ")) {
            return ResponseEntity.ok(messageService.saveMessage(message));
        } else {
            List<String> messages;
            try {
              messages = messageService.getMessages(message);
            } catch (UsernameNotFoundException e) {
                throw e;
            }
           return ResponseEntity.ok(messages);
        }

    }
}
