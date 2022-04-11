package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.MessageDto;
import com.example.demo.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {MessageService.class})
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @MockBean
    UserService userService;

    @MockBean
    MessageRepository messageRepository;

    @Test
    public void whenUserIsNotPresentThenThrowException() {
        //given
        MessageDto message = MessageDto.builder()
                .name("user")
                .message("message")
                .build();

        //when //then
        try {
            messageService.saveMessage(message);
        } catch (UsernameNotFoundException e) {
            assertThat(e.getMessage()).isNotBlank();
        }
    }

    @Test
    public void whenMessagesExistThenReturnListOfMessages() {
        //given
        MessageDto message = MessageDto.builder()
                .name("user")
                .message("message 2")
                .build();
        User user = User.builder()
                .name("name")
                .build();
        List<String> messages = List.of("1", "2", "3");
        given(messageRepository.getLimitedMessageByUserId(anyLong(), anyInt())).willReturn(messages);
        given(userService.findUserByName(anyString())).willReturn(Optional.of(user));

        //when
        List<String> listOfMessages = messageService.getMessages(message);

        //then
        assertThat(listOfMessages).isSameAs(messages);
    }
}
