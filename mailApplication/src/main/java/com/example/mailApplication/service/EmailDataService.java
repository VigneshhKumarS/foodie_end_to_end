package com.example.mailApplication.service;

import com.example.mailApplication.model.EmailData;

public interface EmailDataService {
    public abstract EmailData sendEmail(EmailData emailData);
}
