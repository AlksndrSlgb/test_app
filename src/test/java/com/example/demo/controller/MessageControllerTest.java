package com.example.demo.controller;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.MessageDto;
import com.example.demo.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    public void setUp() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(messageController)
                .build();
    }

    @Test
    void shouldReturnSavedMessage() throws Exception {
        //given
        User user = User.builder()
                .name("name")
                .password("pasww")
                .build();
        Message message = Message.builder()
                .message("mesage")
                .user(user)
                .build();
        when(messageService.saveMessage(any(MessageDto.class))).thenReturn(message);


        //when then
        mockMvc.perform(post("/api/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\" : \"user\",\"message\" : \"histor 1\"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}