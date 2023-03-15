package com.example.mailApplication.controller;

import com.example.mailApplication.model.EmailData;
import com.example.mailApplication.service.EmailDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailDataController {

    @Autowired
    private EmailDataService emailDataService;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailData emailData){
        System.out.println(emailDataService.sendEmail(emailData));
        return new ResponseEntity<>("Mail Sent Successfully :)", HttpStatus.OK);
    }
}
