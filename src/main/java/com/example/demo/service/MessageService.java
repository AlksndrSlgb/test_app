package com.example.demo.service;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.MessageDto;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private  UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(MessageDto message) {
        Optional<User> user = userService.findUserByName(message.getName());
        if (user.isPresent()) {
            Message messageFromDto = Message.builder()
                    .user(user.get())
                    .message(message.getMessage()).build();
            return messageRepository.save(messageFromDto);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public List<String> getMessages(MessageDto message) {
        Optional<User> user = userService.findUserByName(message.getName());
        if (user.isPresent()) {
            String[] values = message.getMessage().split(" ");
            Integer limit = Integer.parseInt(values[1]);
            return messageRepository.getLimitedMessageByUserId(user.get().getId(), limit);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
