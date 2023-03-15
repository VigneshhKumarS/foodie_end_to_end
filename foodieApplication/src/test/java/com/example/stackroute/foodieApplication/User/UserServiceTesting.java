package com.example.stackroute.foodieApplication.User;

import com.example.stackroute.foodieApplication.exception.AlreadyExistingException;
import com.example.stackroute.foodieApplication.exception.NotFoundException;
import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.model.User;
import com.example.stackroute.foodieApplication.repository.UserRepository;
import com.example.stackroute.foodieApplication.service.UserServiceImpl;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTesting {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private User user,user1;
    private Restaurant restaurant;
    private Items items;
    private Cuisine cuisine;

    @BeforeEach
    public void setUp(){
        List<Restaurant> res = new ArrayList<>();
        List<Items> ite = new ArrayList<>();
        List<Cuisine> cuisines=new ArrayList<>();
      restaurant=new Restaurant("arun","arun@gmail.com","madurai",cuisines);
        user=new User("utkarshamalpure31@gmail.com","Utkarsha","Mumbai","8787878787","ROLE_USER",new Binary("".getBytes()),res,ite);
        user1=new User("arun@gmail.com","arun","madurai","568974131","ROLE_USER",new Binary("".getBytes()),res,ite);
    }
    @AfterEach
    public void clean(){
//        userRepository.deleteById(user.getEmailID());
        user=null;
    }
    @Test //
    public void registerUserSuccess()throws AlreadyExistingException {
        when(userRepository.findById(user.getEmailID())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user,userService.registerUser(user));
        verify(userRepository,times(1)).findById(user.getEmailID());
        verify(userRepository,times(1)).save(user);
    }
    @Test
    public void  registerUserFailure(){
        when(userRepository.findById(user.getEmailID())).thenReturn(Optional.of(user));
        assertThrows(AlreadyExistingException.class,()->userService.registerUser(user));
        verify(userRepository,times(1)).findById(user.getEmailID());
        verify(userRepository,times(0)).save(user);
    }
    @Test
    public void getAllUserSuccess(){
        when(userRepository.findAll()).thenReturn(List.of(user,user1));
        assertIterableEquals(Arrays.asList(user,user1),userService.getAllUserDetails());
        verify(userRepository,times(1)).findAll();
    }
    @Test
    public void getUserByIdSuccess()throws NotFoundException {
        when(userRepository.findById(user.getEmailID())).thenReturn(Optional.of(user));
        assertEquals(user,userService.getUserByEmailID(user.getEmailID()));
        verify(userRepository,times(2)).findById(user.getEmailID());
    }
    @Test
    public void getUserByIdFailure(){
        when(userRepository.findById(user.getEmailID())).thenReturn(Optional.ofNullable(null));
        assertThrows(NotFoundException.class,()->userService.getUserByEmailID(user.getEmailID()));
        verify(userRepository,times(1)).findById(user.getEmailID());
    }
    @Test
    public void getUserByRoleSuccess()throws NotFoundException{
        when(userRepository.getUserByRole(user.getRole())).thenReturn(List.of(user));
        List<User> userList=userRepository.getUserByRole(user.getRole());
        long c=userRepository.getUserByRole(user.getRole()).size();
        long c1=userList.size();
        assertEquals(c,c1);
        verify(userRepository,times(2)).getUserByRole(user.getRole());
    }
//    @Test
//    public void getUserByRoleFailure(){
//        when(userRepository.getUserByRole(user.getRole())).thenReturn(List.of(null));
////        List<User> userList=userRepository.getUserByRole(user.getRole());
////        long c=userRepository.getUserByRole(user.getRole()).size();
////        long c1=userList.size();
////        assertEquals(c,c1);
//        assertThrows(NotFoundException.class,()->userService.getUserByRole(user.getRole()));
//        verify(userRepository,times(1)).getUserByRole(user.getRole());
//    }
    @Test
    public void editUserProfileSuccess()throws IOException {
        when(userRepository.findById(user1.getEmailID())).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(user1);
        MultipartFile file=new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        assertEquals(user1,userService.editUserProfile("arun@gmail.com","arun","madurai","568974131","ROLE_USER", file));
        verify(userRepository,times(1)).findById(user1.getEmailID());
        verify(userRepository,times(1)).save(user1);
    }
    @Test
    public void addFavouriteRestaurantSuccess(){
        when(userRepository.findById(user1.getEmailID())).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(user1);
        assertEquals(user1,userService.addFavouriteRestaurant("arun@gmail.com",restaurant));
        verify(userRepository,times(1)).findById(user1.getEmailID());
        verify(userRepository,times(1)).save(user1);
    }
}
