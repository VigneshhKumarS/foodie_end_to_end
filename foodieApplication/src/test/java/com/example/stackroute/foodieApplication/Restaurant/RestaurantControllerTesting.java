package com.example.stackroute.foodieApplication.Restaurant;

import com.example.stackroute.foodieApplication.controller.RestaurantController;
import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.service.RestaurantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTesting {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private RestaurantService restaurantService;
    @InjectMocks
    private RestaurantController restaurantController;
    private Restaurant restaurant,restaurant1;
    private Cuisine cuisine;
    private Items items;

    @BeforeEach
    public void setUp(){
        List<Items> ite = new ArrayList<>();
        List<Cuisine> cuisines=new ArrayList<>();
        items=new Items("Dosa",120);
        ite.add(items);
        cuisine=new Cuisine("South Indian",ite);
        cuisines.add(cuisine);
//        restaurant=new Restaurant("Shivay","shivay@gmail.com","Pune",cuisines);
//        restaurant1=new Restaurant("Shivay1","shivay1@gmail.com","Pune",cuisines);
        mockMvc= MockMvcBuilders.standaloneSetup(restaurantController).build();
    }
    @AfterEach
    public void clean(){
//        restaurantRepository.deleteById(restaurant.getName());
        restaurant=null; restaurant1=null;cuisine=null;items=null;
    }
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
//    @Test
//    public void addRestaurant() throws Exception {
//        when(restaurantService.addRestaurant(restaurant)).thenReturn(restaurant);
//        mockMvc.perform(
//                post("/restaurant-app/add-restaurant")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(convertToJson(restaurant)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(restaurantService,times(1)).addRestaurant(restaurant);
//    }
    @Test
    public void getAllRestaurant() throws Exception {
        when(restaurantService.getAllRestaurants()).thenReturn(List.of(restaurant,restaurant1));
        mockMvc.perform(
                        get("/restaurant-app/get-all-restaurants"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(restaurantService,times(1)).getAllRestaurants();
    }
    @Test
    public void deleteRestaurant() throws Exception {
        when(restaurantService.deleteRestaurant("Shivay")).thenReturn(true);
        mockMvc.perform(
                        delete("/restaurant-app/delete-restaurant/Shivay"))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(restaurantService,times(1)).deleteRestaurant("Shivay");
    }
    @Test
    public void getRestaurantByLocation() throws Exception {
        when(restaurantService.getRestaurantByLocation("Pune")).thenReturn(List.of(restaurant));
        mockMvc.perform(
                get("/restaurant-app/get-restaurant-by-location/Pune"))
                .andExpect((status().isOk())).andDo(MockMvcResultHandlers.print());
        verify(restaurantService,times(1)).getRestaurantByLocation("Pune");
    }

    @Test
    public void getRestaurantByCuisine() throws Exception {
        when(restaurantService.getRestaurantByCuisineName("South Indian")).thenReturn(List.of(restaurant));
        mockMvc.perform(
                        get("/restaurant-app/get-restaurant-by-cuisineName/South Indian"))
                .andExpect((status().isOk())).andDo(MockMvcResultHandlers.print());
        verify(restaurantService,times(1)).getRestaurantByCuisineName("South Indian");
    }
    @Test
    public void editRestaurant() throws Exception {
        when(restaurantService.editRestaurant(restaurant)).thenReturn(restaurant);
        mockMvc.perform(
                put("/restaurant-app/edit-restaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(restaurant)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(restaurantService,times(1)).editRestaurant(restaurant);
    }
//    @Test
//    public void getRestaurantOwnerId() throws Exception {
//        lenient().when(restaurantService.getRestaurantByOwner("shivay1@gmail.com")).thenReturn(restaurant1);
//        mockMvc.perform(
//                get("/restaurant-app/get-restaurant-by-owner"))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(restaurantService,times(1)).getRestaurantByOwner("shivay1@gmail.com");
//    }
}
