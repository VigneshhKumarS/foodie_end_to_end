package com.example.stackroute.authentication.service;

import com.example.stackroute.authentication.exceptions.UserAlreadyExistingException;
import com.example.stackroute.authentication.exceptions.UserNotExistsException;
import com.example.stackroute.authentication.feignClient.SignUpData;
import com.example.stackroute.authentication.feignClient.UserDTO;
import com.example.stackroute.authentication.feignClient.UserProxy;
import com.example.stackroute.authentication.model.EmailData;
import com.example.stackroute.authentication.model.User;
import com.example.stackroute.authentication.rabbitMq.MailDTO;
import com.example.stackroute.authentication.rabbitMq.MailProducer;
import com.example.stackroute.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserProxy userProxy;

    @Autowired
    private MailProducer mailProducer;
    @Override
    public User registerUser1(User user) throws UserAlreadyExistingException {
        if(userRepository.findById(user.getEmailID()).isPresent()){
            throw new UserAlreadyExistingException();
        }
        else{
        return userRepository.save(user);
        }
    }

    @Override
    public User registerUser(SignUpData signUpData) throws UserAlreadyExistingException {
        UserDTO userDTO = new UserDTO(signUpData.getEmailID(),signUpData.getName(),signUpData.getLocation(),signUpData.getMobileNo());
        ResponseEntity re = userProxy.sendDTOtoFoodieApplication(userDTO);
        User user = new User(signUpData.getEmailID(),signUpData.getName(),signUpData.getPassword(),signUpData.getLocation(),signUpData.getMobileNo(),"ROLE_USER");
        MailDTO mailDTO = new MailDTO(user.getEmailID(),"Registration Successfull",user.getName()+" Hi there. You've Registered Successfully. Thank You for choosing us :)");
        mailProducer.sendMailDTOtoQueue(mailDTO);
        return userRepository.save(user);
    }

    @Override
    public User ownerUpdate(String emailID) {
        System.out.println(emailID);
        User user = userRepository.findById(emailID).get();
        System.out.println(user);
        User user1=new User();
        user1.setEmailID(emailID);
        user1.setName(user.getName());
        user1.setRole("ROLE_OWNER");
        user1.setPassword(user.getPassword());
        user1.setLocation(user.getLocation());
        user1.setMobileNo(user.getMobileNo());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("foodiehunt789.fh@gmail.com");
        mailMessage.setTo(emailID);
        mailMessage.setSubject("Request Accepted");
        mailMessage.setText("Your Owner Request Was Accepted Successfully");
        javaMailSender.send(mailMessage);
//        UserDTO userDTO = new UserDTO(user.getEmailID(),user.getName(),user.getLocation(),user.getMobileNo());
//        ResponseEntity re = userProxy.sendDTOtoFoodieApplication(userDTO);
        return userRepository.save(user1);
    }

    @Override
    public User login(String emailID, String password) throws UserNotExistsException {
        if(userRepository.findById(emailID).isEmpty()){
            throw new UserNotExistsException();
        }
        else
        return userRepository.findByEmailIDAndPassword(emailID,password);
    }

    @Override
    public void forgotPasswordEmail(String emailID) {
        User user = userRepository.findById(emailID).get();
        String password = user.getPassword();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("vkuser310701@gmail.com");
        mailMessage.setTo(emailID);
        mailMessage.setSubject("Password Recovery - Foodie Hunt");
        mailMessage.setText("Your Account Password is : "+password );
        javaMailSender.send(mailMessage);
    }

    @Override
    public List<User> getUserByRole(String role) {
        return userRepository.findAllByRole(role);
    }
}
