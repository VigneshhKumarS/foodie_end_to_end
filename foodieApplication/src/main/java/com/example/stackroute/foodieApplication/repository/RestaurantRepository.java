package com.example.stackroute.foodieApplication.repository;

import com.example.stackroute.foodieApplication.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RestaurantRepository extends MongoRepository<Restaurant,String> {
    @Query("{'location':{$in:[?0]}}")
    public abstract List<Restaurant> getRestaurantByLocation(String location);

    @Query("{'cuisines.cuisineName':{$in:[?0]}}")
    public abstract List<Restaurant> getRestaurantByCuisineName(String cuisineName);

    @Query("{'cuisines.items.itemName':{$in:[?0]}}")
    public abstract List<Restaurant> getRestaurantByFood(String itemName);

    @Query("{'ownerEmailID':{$in:[?0]}}")
    public abstract List<Restaurant> getRestaurantByOwner(String ownerEmailID);
}
