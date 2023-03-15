package com.example.stackroute.foodieApplication.User;

import com.example.stackroute.foodieApplication.controller.UserController;

import com.example.stackroute.foodieApplication.exception.NotFoundException;
import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.model.User;
import com.example.stackroute.foodieApplication.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.Binary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTesting {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private User user,user1;
    private Restaurant restaurant;
    private Items items;
    private Cuisine cuisine;
    @BeforeEach
    public void setUp(){
        List<Restaurant> res = new ArrayList<>();
        List<Items> ite = new ArrayList<>();
//        List<Cuisine> cuisines=new ArrayList<>();
//        restaurant=new Restaurant("arun","arun@gmail.com","madurai",cuisines);
        user=new User("utkarshamalpure311@gmail.com","Utkarsha","Mumbai","8787878787","ROLE_USER",new Binary("".getBytes()),res,ite);
        user1=new User("arun@gmail.com","arun","madurai","568974131","ROLE_USER",new Binary("".getBytes()),res,ite);
        mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }
    @AfterEach
    public void clean(){
//        userRepository.deleteById(user.getEmailID());
        user=null; user1=null;
    }
    //method for converting the java object to json object
    public static String convertToJson(final Object object){
        String result="";
        ObjectMapper mapper=new ObjectMapper();
        try {
            result=mapper.writeValueAsString(object);
        }catch (JsonProcessingException je){
            throw new RuntimeException(je);
        }
        return result;
    }

    @Test
    public void registerUserSuccess() throws Exception {
        lenient().when(userService.registerUser(user)).thenReturn(user);
        mockMvc.perform(
                post("/user-app/register-user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());//give the response and print it
        verify(userService,times(1)).registerUser(user);
    }

    @Test //Get All User Details
    public void getAllUser() throws Exception {
        when(userService.getAllUserDetails()).thenReturn(List.of(user,user1));
        mockMvc.perform(
                        get("/user-app/get-all-users"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).getAllUserDetails();
    }
    @Test
    public void getUserById() throws Exception {
        lenient().when(userService.getUserByEmailID(user.getEmailID())).thenReturn(user);
        mockMvc.perform(
                get("/user-app/get-user-byID"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).getUserByEmailID(user.getEmailID());
    }

}
