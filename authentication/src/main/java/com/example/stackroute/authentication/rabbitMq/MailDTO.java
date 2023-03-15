package com.example.stackroute.authentication.rabbitMq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MailDTO {
    private String mailID;
    private String subject,message;
}
