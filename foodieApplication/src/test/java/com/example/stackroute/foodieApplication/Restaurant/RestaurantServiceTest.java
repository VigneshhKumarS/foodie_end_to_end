package com.example.stackroute.foodieApplication.Restaurant;

import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.model.User;
import com.example.stackroute.foodieApplication.repository.RestaurantRepository;
import com.example.stackroute.foodieApplication.service.RestaurantServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;
    @InjectMocks
    private RestaurantServiceImpl restaurantService;
    private Restaurant restaurant,restaurant1;
    private Cuisine cuisine;
    private Items items;

    @BeforeEach
    public void setUp(){
        List<Items> ite = new ArrayList<>();
        List<Cuisine> cuisines=new ArrayList<>();
//        items=new Items("Dosa",120);
        ite.add(items);
        cuisine=new Cuisine("South Indian",ite);
        cuisines.add(cuisine);
//        restaurant=new Restaurant("Shivay","shivay@gmail.com","Pune",cuisines);
//        restaurant1=new Restaurant("Shivay1","shivay1@gmail.com","Pune",cuisines);

    }
    @AfterEach
    public void clean(){
        restaurantRepository.deleteById(restaurant.getName());
        restaurant=null;
    }
//    @Test
//    public void addRestaurantSuccess(){
//        lenient().when(restaurantRepository.findById(restaurant.getName())).thenReturn(Optional.of(restaurant));
//        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
//        assertEquals(restaurant,restaurantService.addRestaurant(restaurant));
////        verify(restaurantRepository,times(1)).findById(restaurant.getName());
//        verify(restaurantRepository,times(1)).save(restaurant);
//    }
    @Test
    public void deleteRestaurantSuccess(){
        lenient().when(restaurantRepository.findById(restaurant.getName())).thenReturn(Optional.of(restaurant));
        boolean result=restaurantService.deleteRestaurant(restaurant.getName());
        assertEquals(true,result);
//        restaurantRepository.findById(restaurant.getName()).isEmpty();
        verify(restaurantRepository,times(1)).deleteById(restaurant.getName());
    }
    @Test
    public void getAllRestaurant(){
        lenient().when(restaurantRepository.findAll()).thenReturn(List.of(restaurant,restaurant1));
        assertIterableEquals(Arrays.asList(restaurant,restaurant1),restaurantService.getAllRestaurants());
        verify(restaurantRepository,times(1)).findAll();
    }
    @Test
    public void editRestaurant(){
        lenient().when(restaurantRepository.findById(restaurant.getName())).thenReturn(Optional.of(restaurant));
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);
        assertEquals(restaurant,restaurantService.editRestaurant(restaurant));
//        Mockito.verify(restaurantRepository,times(1)).findById(restaurant.getName());
        verify(restaurantRepository,times(1)).save(restaurant);
    }

    @Test
    public void getRestaurantByLocation(){
        when(restaurantRepository.getRestaurantByLocation(restaurant.getLocation())).thenReturn(List.of(restaurant));
        List<Restaurant> restaurantList=restaurantRepository.getRestaurantByLocation(restaurant.getLocation());
        long c=restaurantRepository.getRestaurantByLocation(restaurant.getLocation()).size();
        long c1=restaurantList.size();
        assertEquals(c,c1);
        verify(restaurantRepository,times(2)).getRestaurantByLocation(restaurant.getLocation());
    }
    @Test
    public void getRestaurantByCuisineName(){
        lenient().when(restaurantRepository.getRestaurantByCuisineName(cuisine.getCuisineName())).thenReturn(List.of(restaurant));
        List<Restaurant> restaurantList=restaurantRepository.getRestaurantByCuisineName(cuisine.getCuisineName());
        long c=restaurantRepository.getRestaurantByCuisineName(cuisine.getCuisineName()).size();
        long c1=restaurantList.size();
        assertEquals(c,c1);
        verify(restaurantRepository,times(2)).getRestaurantByCuisineName(cuisine.getCuisineName());
    }

    @Test
    public void getRestaurantByFoodName(){
        lenient().when(restaurantRepository.getRestaurantByFood(items.getItemName())).thenReturn(List.of(restaurant));
        List<Restaurant> restaurantList=restaurantRepository.getRestaurantByFood(items.getItemName());
        long c=restaurantRepository.getRestaurantByFood(items.getItemName()).size();
        long c1=restaurantList.size();
        assertEquals(c,c1);
        verify(restaurantRepository,times(2)).getRestaurantByFood(items.getItemName());
    }
    @Test
    public void getRestaurantByOwner(){
        when(restaurantRepository.getRestaurantByOwner(restaurant.getOwnerEmailID())).thenReturn(restaurant);
        assertEquals(restaurant,restaurantService.getRestaurantByOwner(restaurant.getOwnerEmailID()));
        verify(restaurantRepository,times(1)).getRestaurantByOwner(restaurant.getOwnerEmailID());
    }



}
