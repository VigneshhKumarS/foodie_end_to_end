package com.example.mailApplication.rabbitmq;

import com.example.mailApplication.model.EmailData;
import com.example.mailApplication.service.EmailDataService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailConsumer {

    @Autowired
    private EmailDataService emailDataService;

    @RabbitListener(queues = "mail_queue")
    public void getEmailDtoFromQueue(EmailDTO emailDTO){
        EmailData emailData = new EmailData(emailDTO.getMailID(),emailDTO.getSubject(),emailDTO.getMessage());
        emailDataService.sendEmail(emailData);
        System.out.println(emailData);
    }
}
