package com.example.mailApplication.repository;

import com.example.mailApplication.model.EmailData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailDataRepository extends MongoRepository<EmailData,String> {
}
