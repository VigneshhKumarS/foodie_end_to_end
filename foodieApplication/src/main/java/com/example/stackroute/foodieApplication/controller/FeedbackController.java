package com.example.stackroute.foodieApplication.controller;

import com.example.stackroute.foodieApplication.model.Feedback;
import com.example.stackroute.foodieApplication.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    //http://localhost:8888/feedback/add-feedback
    @PostMapping("/add-feedback")
    public ResponseEntity<?> addFeedback(@RequestBody String feedback, HttpServletRequest request){
        String emailID=(String)request.getAttribute("current_user_emailID");
        return new ResponseEntity<>(feedbackService.addFeedback(emailID,feedback), HttpStatus.OK);
    }
}
