package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class SecurityControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SecurityService securityService;

    @InjectMocks
    private SecurityController securityController;


    @BeforeEach
    public void setUp() throws IOException {
        mockMvc = MockMvcBuilders.standaloneSetup(securityController)
                .build();
    }

    @Test
    public void shouldReturnStringWhenSignup() throws Exception {
        //given
        when(securityService.addUser(any(User.class))).thenReturn("Okay");

        //when
        MvcResult result = mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\" : \"user12\",\"password\": \"password\"}"))
                .andDo(print())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        //then
        assertThat(content).isEqualTo("Okay");
    }
}
