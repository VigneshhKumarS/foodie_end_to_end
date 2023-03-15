package com.example.stackroute.foodieApplication.service;

import com.example.stackroute.foodieApplication.model.Feedback;

public interface FeedbackService {
    public abstract Feedback addFeedback(String emailID,String feedback);
}
