package com.example.stackroute.foodieApplication.service;

import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RestaurantService {
    public abstract Restaurant addRestaurant(String name,String ownerEmailId,String location, MultipartFile file) throws IOException;
    public abstract boolean deleteRestaurant(String restaurantName);
    public abstract Restaurant editRestaurant(Restaurant restaurant);
    public abstract User addItemsToCart(String emailID, Items items);
    public abstract User removeItemFromCart(String emailID,Items item);
    public abstract User placeOrder(String emailID);
    public abstract List<Restaurant> getAllRestaurants();
    public abstract List<Restaurant> getRestaurantByLocation(String location);
    public abstract List<Restaurant> getRestaurantByCuisineName(String cuisineName);
    public abstract List<Restaurant> getRestaurantByFoodName(String itemName);
    public abstract Restaurant getRestaurantByName(String name);
    public abstract List<Restaurant> getRestaurantByOwner(String ownerEmailID);
    public abstract Restaurant addCuisine(String name,String cuisineName);
    public abstract Restaurant addItemsToCuisine(String name,String cuisineName,String itemName,int price,MultipartFile file) throws IOException;
    public abstract Restaurant removeCuisine(String name, Cuisine cuisine);
    public abstract Restaurant removeItems(String name, String cuisineName,String itemName);
    public abstract User removeFavRestaurant(String emailID,Restaurant restaurant);
}
