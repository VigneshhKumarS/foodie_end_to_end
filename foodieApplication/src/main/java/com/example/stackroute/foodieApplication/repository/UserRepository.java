package com.example.stackroute.foodieApplication.repository;

import com.example.stackroute.foodieApplication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {

//    @Query("{'role':{$in:[?0]}}")
//    public abstract List<User> getUserByRole(String role);
}
