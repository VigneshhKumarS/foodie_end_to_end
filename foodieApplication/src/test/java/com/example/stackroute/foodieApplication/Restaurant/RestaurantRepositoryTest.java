package com.example.stackroute.foodieApplication.Restaurant;

import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@ExtendWith(SpringExtension.class)
@DataMongoTest
public class RestaurantRepositoryTest {
    @Autowired
    private RestaurantRepository restaurantRepository;
    private Restaurant restaurant;
    private Cuisine cuisine;
    private Items items;
    @BeforeEach
    public void setUp(){
        List<Items> itemsList=new ArrayList<>();
        List<Cuisine> cuisineList=new ArrayList<>();
        restaurant=new Restaurant("Shivay","shivay@gmail.com","Pune",cuisineList);
    }
    @AfterEach
    public void clean(){
        restaurant=null;
        restaurantRepository.deleteAll();
    }
    @Test //test case for findAll()
    public void testFindAll(){
        restaurantRepository.save(restaurant);
        List<Restaurant> restaurantList=restaurantRepository.findAll();
        long c1=restaurantList.size();
        long c2=restaurantRepository.count();
        assertEquals("equal",c1,c2);
    }
    @Test
    public void testFindById(){
        restaurantRepository.save(restaurant);
//        restaurantRepository.deleteById(restaurant.getName());
        assertEquals("true",true,restaurantRepository.findById(restaurant.getName()).isPresent());
    }
    @Test
    public void deleteById(){
        restaurantRepository.save(restaurant);
        restaurantRepository.deleteById(restaurant.getName());
        assertEquals("true",true,restaurantRepository.findById(restaurant.getName()).isEmpty());
    }


}
