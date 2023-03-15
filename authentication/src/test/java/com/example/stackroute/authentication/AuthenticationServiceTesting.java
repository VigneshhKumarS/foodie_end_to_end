package com.example.stackroute.authentication;

import com.example.stackroute.authentication.exceptions.UserAlreadyExistingException;
import com.example.stackroute.authentication.model.User;
import com.example.stackroute.authentication.repository.UserRepository;
import com.example.stackroute.authentication.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTesting {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User user;
    @BeforeEach
    public void setUp(){
        user=new User("rani@gmail.com","rani","rani@09","madurai","8698846149","ROLE_USER");
    }
    @AfterEach
    public void clean(){
        user=null;
    }
    @Test
    public void registerUser() throws UserAlreadyExistingException {
        when(userRepository.findById(user.getEmailID())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user,userService.registerUser1(user));
        verify(userRepository,times(1)).findById(user.getEmailID());
        verify(userRepository,times(1)).save(user);
    }
}
