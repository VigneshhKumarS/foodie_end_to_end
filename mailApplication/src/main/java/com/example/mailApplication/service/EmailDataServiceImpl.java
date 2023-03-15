package com.example.mailApplication.service;

import com.example.mailApplication.model.EmailData;
import com.example.mailApplication.repository.EmailDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailDataServiceImpl implements EmailDataService{

    @Autowired
    private EmailDataRepository emailDataRepository;


    @Autowired
    private JavaMailSender mailSender;

    @Override
    public EmailData sendEmail(EmailData emailData) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vkuser310701@gmail.com");
        message.setTo(emailData.getMailID());
        message.setSubject(emailData.getSubject());
        message.setText(emailData.getMessage());
        mailSender.send(message);
        return emailDataRepository.save(emailData);
    }
}
