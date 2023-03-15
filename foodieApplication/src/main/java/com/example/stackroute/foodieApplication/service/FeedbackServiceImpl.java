package com.example.stackroute.foodieApplication.service;

import com.example.stackroute.foodieApplication.model.Feedback;
import com.example.stackroute.foodieApplication.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Override
    public Feedback addFeedback(String emailID,String feedback) {
        Feedback feedback1=new Feedback(emailID,feedback);
        return feedbackRepository.save(feedback1);
    }
}
