package com.example.stackroute.authentication;

import com.example.stackroute.authentication.model.User;
import com.example.stackroute.authentication.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationRepositoryTesting {
    @Autowired
    private UserRepository userRepository;
    private User user;
    @BeforeEach
    public void setUp(){
        user=new User("rani@gmail.com","rani","rani@09","madurai","8698846149","ROLE_USER");
    }
    @AfterEach
    public void clean(){
        userRepository.deleteById(user.getEmailID());
        user=null;
    }
    @Test
    public void addUser(){
        userRepository.save(user);
        User result=userRepository.findById(user.getEmailID()).get();
        assertEquals(user,result);
    }

}
