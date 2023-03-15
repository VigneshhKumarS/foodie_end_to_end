package com.example.stackroute.foodieApplication.controller;

import com.example.stackroute.foodieApplication.exception.AlreadyExistingException;
import com.example.stackroute.foodieApplication.exception.NotFoundException;
import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.model.User;
import com.example.stackroute.foodieApplication.service.UserService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user-app")
public class UserController {

    @Autowired
    private UserService userService;

    // URL : http://localhost:8888/user-app/register-user

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws AlreadyExistingException {
        try{
            user.setProfile(new Binary("".getBytes()));
            user.setRestaurant(new ArrayList<Restaurant>());
            user.setCartItems(new ArrayList<Items>());
            return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
        }catch (AlreadyExistingException ex){
            ex.printStackTrace();
            throw new AlreadyExistingException();
        }
    }

    // URL : http://localhost:8888/user-app/get-all-users

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUserDetails(){
//        List<User> userList = userService.getAllUserDetails();
//        System.out.println(userList);
//        List<User> filteredUserList = new ArrayList<>();
//        for(User u : userList){
//            if(u.getRole()=="ROLE_USER"){
//                System.out.println(true);
//                filteredUserList.add(u);
//            }
//        }
//        System.out.println(filteredUserList);
        return new ResponseEntity<>(userService.getAllUserDetails(),HttpStatus.OK);
    }

//    // URL : http://localhost:8888/user-app/get-users-by-role
//
//    @GetMapping("/get-users-by-role/{role}")
//    public ResponseEntity<?> getUserByRole(@PathVariable String role){
//        return new ResponseEntity<>(userService.getUserByRole(role),HttpStatus.OK);
//    }

    // URL : http://localhost:8888/user-app/get-user-byID

    @GetMapping("/get-user-byID")
    public ResponseEntity<?> getUserByID(HttpServletRequest servletRequest) throws NotFoundException {
        try{
            String emailID=(String)servletRequest.getAttribute("current_user_emailID");
            return new ResponseEntity<>(userService.getUserByEmailID(emailID),HttpStatus.OK);
        }catch (NotFoundException ex){
            ex.printStackTrace();
            throw new NotFoundException();
        }
    }

    // URL : http://localhost:8888/user-app/add-favourite-restaurant

    @PostMapping("/add-favourite-restaurant")
    public ResponseEntity<?> addFavouriteRestaurant(@RequestParam String name,@RequestParam String location,HttpServletRequest servletRequest){
        String emailID=(String)servletRequest.getAttribute("current_user_emailID");
        Restaurant restaurant=new Restaurant(name,null,location,null,new ArrayList<Cuisine>());
        return new ResponseEntity<>(userService.addFavouriteRestaurant(emailID,restaurant),HttpStatus.OK);
    }

    // URL : http://localhost:8888/user-app/edit-user-profile

    @PostMapping("/edit-user-profile")
    public ResponseEntity<?> editUserProfile(@RequestParam String emailID,@RequestParam String name,@RequestParam String location, @RequestParam String mobileNo,@RequestParam String role, @RequestParam MultipartFile file,HttpServletRequest servletRequest) throws IOException {
//        String emailID = (String)servletRequest.getAttribute("current_user_emailID");
        return new ResponseEntity<>(userService.editUserProfile(emailID,name,location,mobileNo,role,file),HttpStatus.OK);
    }




}
