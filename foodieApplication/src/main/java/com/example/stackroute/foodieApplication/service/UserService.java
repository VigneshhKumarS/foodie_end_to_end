package com.example.stackroute.foodieApplication.service;

import com.example.stackroute.foodieApplication.exception.AlreadyExistingException;
import com.example.stackroute.foodieApplication.exception.NotFoundException;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public abstract User registerUser(User user)throws AlreadyExistingException;
    public abstract List<User> getAllUserDetails();
//    public abstract List<User> getUserByRole(String role);
    public abstract User getUserByEmailID(String emailID)throws NotFoundException;
    public abstract User addFavouriteRestaurant(String emailID, Restaurant restaurant);
    public abstract User editUserProfile(String emailID, String name,String location, String mobileNo,String role, MultipartFile file) throws IOException;
}
