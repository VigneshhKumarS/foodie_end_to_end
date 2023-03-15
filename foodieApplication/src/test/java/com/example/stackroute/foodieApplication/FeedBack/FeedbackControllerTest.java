package com.example.stackroute.foodieApplication.FeedBack;

import com.example.stackroute.foodieApplication.controller.FeedbackController;
import com.example.stackroute.foodieApplication.model.Feedback;
import com.example.stackroute.foodieApplication.service.FeedbackService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FeedbackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private FeedbackService feedbackService;
    @InjectMocks
    private FeedbackController feedbackController;
    public Feedback fb;
    @BeforeEach
    public void setUp(){
        fb=new Feedback("nikhilmalpure9@gmail.com","nice");
    }
    @AfterEach
    public void clean(){
        fb=null;
    }
    public static String convertToJson(final Object object) {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(object);
        } catch (JsonProcessingException je) {
            throw new RuntimeException(je);
        }
        return result;
    }
    @Test
    public void addFeedback() throws Exception {
        when(feedbackService.addFeedback(fb.getEmailID(),fb.getFeedback())).thenReturn(fb);
        System.out.println(fb.getEmailID());
        mockMvc.perform(
                post("/feedback/add-feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(fb)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(feedbackService,times(1)).addFeedback(fb.getEmailID(), fb.getFeedback());
    }
}
