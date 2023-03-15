package com.example.stackroute.foodieApplication.FeedBack;

import com.example.stackroute.foodieApplication.model.Feedback;
import com.example.stackroute.foodieApplication.repository.FeedbackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class FeedBackRepositoryTest {
    @Autowired
    private FeedbackRepository feedbackRepository;
    private Feedback feedback;
    @BeforeEach
    public void setUp(){
        feedback=new Feedback("utkarsha@gmail.com","good");
    }
    @AfterEach
    public void clean(){
        feedback=null;
        feedbackRepository.deleteAll();
    }
    @Test
    public void addFeedback(){
        feedbackRepository.save(feedback);
        assertEquals("true",true,feedbackRepository.findById(feedback.getEmailID()).isPresent());
    }
}
