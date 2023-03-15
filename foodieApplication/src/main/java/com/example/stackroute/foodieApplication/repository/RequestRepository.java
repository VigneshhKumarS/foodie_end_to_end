package com.example.stackroute.foodieApplication.repository;

import com.example.stackroute.foodieApplication.model.OwnerRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestRepository extends MongoRepository<OwnerRequest,String> {
}
