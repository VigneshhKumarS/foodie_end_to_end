package com.example.stackroute.foodieApplication.User;

import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.model.User;
import com.example.stackroute.foodieApplication.repository.UserRepository;
import org.bson.types.Binary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepositoryTesting {
    @Autowired
    private UserRepository userRepository;
    private User user;
    private Restaurant restaurant;
    private Items items;
    private Cuisine cuisine;

    @BeforeEach
    public void setUp(){
       List<Restaurant> res = new ArrayList<>();
       List<Items> ite = new ArrayList<>();
        user=new User("utkarshamalpure31@gmail.com","Utkarsha","Mumbai","8787878787","ROLE_USER",new Binary("".getBytes()),res,ite);
    }

    @AfterEach
    public void clean(){
       userRepository.deleteById(user.getEmailID());
       user=null;
    }

    @Test //register user
    public void userRegSuccess(){
        userRepository.save(user);
        assertEquals("true",true,userRepository.findById(user.getEmailID()).isPresent());
    }
    @Test //delete
    public void deleteUser(){
        userRepository.save(user);
        userRepository.deleteById(user.getEmailID());
        assertEquals("true",true,userRepository.findById(user.getEmailID()).isEmpty());
    }
    @Test //get all users
    public void getAllUser(){
        userRepository.save(user);
        List<User> userList=userRepository.findAll();
        long c1=userList.size();
        long c2=userRepository.count();
        assertEquals("equal",c1,c2);
    }


}
