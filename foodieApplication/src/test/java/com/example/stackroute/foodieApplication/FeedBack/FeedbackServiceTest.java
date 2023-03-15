package com.example.stackroute.foodieApplication.FeedBack;

import com.example.stackroute.foodieApplication.model.Feedback;
import com.example.stackroute.foodieApplication.repository.FeedbackRepository;
import com.example.stackroute.foodieApplication.service.FeedbackServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceTest {
    @Mock
    private FeedbackRepository feedbackRepository;
    @InjectMocks
    private FeedbackServiceImpl feedbackService;
    private Feedback fb;
    @BeforeEach
    public void setUp(){
        fb=new Feedback("utkarsha@gmail.com","good");
    }
    @AfterEach
    public void clean(){
        feedbackRepository.deleteById(fb.getEmailID());
        fb=null;
    }
    @Test
    public void addFeedback(){
        lenient().when(feedbackRepository.findById(fb.getEmailID())).thenReturn(Optional.of(fb));
        when(feedbackRepository.save(fb)).thenReturn(fb);
        assertEquals(fb,feedbackService.addFeedback(fb.getEmailID(),fb.getFeedback()));
        verify(feedbackRepository,times(1)).save(fb);
    }

}
