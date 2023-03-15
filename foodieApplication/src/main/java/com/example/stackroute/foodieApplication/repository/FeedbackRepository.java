package com.example.stackroute.foodieApplication.repository;

import com.example.stackroute.foodieApplication.model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepository extends MongoRepository<Feedback,String> {
}
