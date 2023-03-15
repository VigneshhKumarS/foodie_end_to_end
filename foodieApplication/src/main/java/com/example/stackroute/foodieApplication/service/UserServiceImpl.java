package com.example.stackroute.foodieApplication.service;

import com.example.stackroute.foodieApplication.exception.AlreadyExistingException;
import com.example.stackroute.foodieApplication.exception.NotFoundException;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.model.User;
import com.example.stackroute.foodieApplication.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User registerUser(User user)throws AlreadyExistingException {
        if (userRepository.findById(user.getEmailID()).isEmpty()){
            return userRepository.save(user);
        }
        else {
            throw new AlreadyExistingException();
        }
    }

    @Override
    public List<User> getAllUserDetails() {
        return userRepository.findAll();
    }

//    @Override
//    public List<User> getUserByRole(String role) {
//        return userRepository.getUserByRole(role);
//    }

    @Override
    public User getUserByEmailID(String emailID)throws NotFoundException {
        if (userRepository.findById(emailID).isPresent()){
            return userRepository.findById(emailID).get();
        }else {
            throw new NotFoundException();
        }
    }

    @Override
    public User addFavouriteRestaurant(String emailID, Restaurant restaurant) {
        User res=userRepository.findById(emailID).get();
        boolean msg=true;
        List<Restaurant> userFav = res.getRestaurant();
        for(Restaurant r : userFav){
            if(r.getName().equals(restaurant.getName())){
                msg=false;
                break;
            }
            else{
                msg=true;
            }
        }
        if(msg==true){
        res.getRestaurant().add(restaurant);
        return userRepository.save(res);
        }
        else{
            return null;
        }
    }

    @Override
    public User editUserProfile(String emailID, String name, String location, String mobileNo,String role, MultipartFile file) throws IOException {
        if (userRepository.findById(emailID).isPresent()) {
            User user1 = new User();
            user1.setEmailID(emailID);
            user1.setName(name);
            user1.setLocation(location);
            user1.setMobileNo(mobileNo);
//            user1.setRole(role);
            user1.setRestaurant(new ArrayList<Restaurant>());
            user1.setCartItems(new ArrayList<Items>());
            user1.setProfile(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            return userRepository.save(user1);
        }
        else{
            return null;
        }
    }

}
