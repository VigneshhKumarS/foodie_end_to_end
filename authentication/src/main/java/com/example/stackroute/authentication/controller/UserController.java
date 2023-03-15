package com.example.stackroute.authentication.controller;

import com.example.stackroute.authentication.exceptions.UserAlreadyExistingException;
import com.example.stackroute.authentication.exceptions.UserNotExistsException;
import com.example.stackroute.authentication.feignClient.SignUpData;
import com.example.stackroute.authentication.model.User;
import com.example.stackroute.authentication.service.JwtTokenGenerator;
import com.example.stackroute.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth-app")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    // URL : http://localhost:9999/auth-app/register-user

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody SignUpData signUpData) throws UserAlreadyExistingException {
        try{
            signUpData.setRole("ROLE_USER");
        return new ResponseEntity<>(userService.registerUser(signUpData), HttpStatus.OK);
        }
        catch (UserAlreadyExistingException ex){
            throw new UserAlreadyExistingException();
        }
    }

    // URL : http://localhost:9999/auth-app/login

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotExistsException {
        try{
//        return new ResponseEntity<>(userService.login(user.getEmailID(),user.getPassword()),HttpStatus.OK);
            User result=userService.login(user.getEmailID(),user.getPassword());
            return new ResponseEntity<>(jwtTokenGenerator.generateJwt(result),HttpStatus.OK);
        }
        catch(UserNotExistsException ex){
            throw new UserNotExistsException();
        }

    }

    // URL : http://localhost:9999/auth-app/forgot-password-recovery
    @PostMapping("/forgot-password-recovery")
    public ResponseEntity<?> forgotPassword(@RequestBody String emailID){
        userService.forgotPasswordEmail(emailID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/get-users-by-role/{role}")
    public ResponseEntity<?> getUserByRole(@PathVariable String role){
        return new ResponseEntity<>(userService.getUserByRole(role),HttpStatus.OK);
    }

    //URL : http://localhost:9999/auth-app/owner-update
    @PostMapping("/owner-update")
    public ResponseEntity<?> ownerUpdate(@RequestBody String emailID){
        return new ResponseEntity<>(userService.ownerUpdate(emailID),HttpStatus.OK);
    }

}
