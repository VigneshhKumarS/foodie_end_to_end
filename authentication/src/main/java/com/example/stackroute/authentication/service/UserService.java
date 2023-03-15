package com.example.stackroute.authentication.service;

import com.example.stackroute.authentication.exceptions.UserAlreadyExistingException;
import com.example.stackroute.authentication.exceptions.UserNotExistsException;
import com.example.stackroute.authentication.feignClient.SignUpData;
import com.example.stackroute.authentication.model.EmailData;
import com.example.stackroute.authentication.model.User;

import java.util.List;

public interface UserService {
    public abstract User registerUser1(User user) throws UserAlreadyExistingException;
    public abstract User registerUser(SignUpData signUpData) throws UserAlreadyExistingException;

    public abstract User ownerUpdate(String emailID);
    public abstract User login(String emailID, String password) throws UserNotExistsException;
    public abstract void forgotPasswordEmail(String emailID);
    public abstract List<User> getUserByRole(String role);

}
