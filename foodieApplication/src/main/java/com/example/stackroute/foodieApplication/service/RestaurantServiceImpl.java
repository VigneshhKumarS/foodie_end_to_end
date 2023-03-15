package com.example.stackroute.foodieApplication.service;

import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.model.User;
import com.example.stackroute.foodieApplication.repository.RestaurantRepository;
import com.example.stackroute.foodieApplication.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public Restaurant addRestaurant(String name,String ownerEmailId,String location,MultipartFile file) throws IOException {
//        restaurant.setRestImage(restaurant.getRestImage());
        Restaurant restaurant1=new Restaurant();
        restaurant1.setName(name);
        restaurant1.setOwnerEmailID(ownerEmailId);
        restaurant1.setLocation(location);
        restaurant1.setCuisines(new ArrayList<Cuisine>());
        restaurant1.setRestImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        return restaurantRepository.save(restaurant1);
    }

    @Override
    public boolean deleteRestaurant(String restaurantName) {
        restaurantRepository.deleteById(restaurantName);
        return true;
    }

    @Override
    public Restaurant editRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public User addItemsToCart(String emailID, Items items) {
        User res=userRepository.findById(emailID).get();
        res.getCartItems().add(items);
        return userRepository.save(res);
    }

    @Override
    public User removeItemFromCart(String emailID, Items item) {
        User res=userRepository.findById(emailID).get();
//        res.getCartItems().remove(item);
        List<Items> allItems=res.getCartItems();
        allItems.remove(item);
        res.setCartItems(allItems);
        return userRepository.save(res);
    }

    @Override
    public User placeOrder(String emailID) {
        User res=userRepository.findById(emailID).get();
        res.setCartItems(new ArrayList<Items>());
        userRepository.save(res);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("vkuser310701@gmail.com");
        mailMessage.setTo(emailID);
        mailMessage.setSubject("Order Status : ");
        mailMessage.setText("Dear "+res.getName()+", your Order has been placed Successfully. It'll be delivered by our delivery executive soon. Thank You :)");
        javaMailSender.send(mailMessage);
        return res;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> getRestaurantByLocation(String location) {
        return restaurantRepository.getRestaurantByLocation(location);
    }

    @Override
    public List<Restaurant> getRestaurantByCuisineName(String cuisineName) {
        return restaurantRepository.getRestaurantByCuisineName(cuisineName);
    }

    @Override
    public List<Restaurant> getRestaurantByFoodName(String itemName) {
        return restaurantRepository.getRestaurantByFood(itemName);
    }

    @Override
    public Restaurant getRestaurantByName(String name) {

        return restaurantRepository.findById(name).get();
    }

    @Override
    public List<Restaurant> getRestaurantByOwner(String ownerEmailID) {
        return restaurantRepository.getRestaurantByOwner(ownerEmailID);
    }

    @Override
    public Restaurant addCuisine(String name,String cuisineName) {
        Restaurant restaurant=restaurantRepository.findById(name).get();
        List<Cuisine> cuisines=restaurant.getCuisines();
        Cuisine cuisine=new Cuisine();
        cuisine.setCuisineName(cuisineName);
        cuisine.setItems(new ArrayList<Items>());
        cuisines.add(cuisine);
        restaurant.setCuisines(cuisines);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant addItemsToCuisine(String name, String cuisineName, String itemName,int price,MultipartFile file) throws IOException {
        Restaurant restaurant=restaurantRepository.findById(name).get();
        List<Cuisine> allCuisines=restaurant.getCuisines();
        Cuisine cuisine=new Cuisine();
        for(Cuisine c:allCuisines){
            if(c.getCuisineName().equals(cuisineName)){
                cuisine=c;
                break;
            }
        }
        if(cuisine!=null){
            List<Items> itemsList=cuisine.getItems();
            Items items=new Items();
            items.setItemName(itemName);
            items.setPrice(price);
            items.setItemImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            itemsList.add(items);
            cuisine.setItems(itemsList);
//            allCuisines.add(cuisine);
            restaurant.setCuisines(allCuisines);
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant removeCuisine(String name, Cuisine cuisine) {
        Restaurant restaurant=restaurantRepository.findById(name).get();
        List<Cuisine> cuisinesList=restaurant.getCuisines();
        Cuisine cuisine1=new Cuisine();
        for(Cuisine c:cuisinesList){
            if (c.getCuisineName().equals(cuisine.getCuisineName())){
                cuisine1=c;
            }
        }
        if (cuisine1!=null){
            cuisinesList.remove(cuisine1);
             restaurant.setCuisines(cuisinesList);
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant removeItems(String name, String cuisineName, String itemName) {
        Restaurant restaurant=restaurantRepository.findById(name).get();
        List<Cuisine> cuisineList=restaurant.getCuisines();
        List<Items> itemsList=new ArrayList<>();
        Cuisine cuisine=new Cuisine();
        Items items1=new Items();
        for (Cuisine c:cuisineList){
            if(c.getCuisineName().equals(cuisineName)){
                cuisine=c;
                itemsList=cuisine.getItems();
                for (Items i:itemsList){
                    if (i.getItemName().equals(itemName)){
                        items1=i;
                    }
                }
            }
        }
        if (cuisine!=null&&items1!=null){
            itemsList.remove(items1);
            cuisine.setItems(itemsList);
            restaurant.setCuisines(cuisineList);
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public User removeFavRestaurant(String emailID, Restaurant restaurant) {
        User res=userRepository.findById(emailID).get();
        System.out.println(res);
        List<Restaurant> allRestaurant=res.getRestaurant();
        allRestaurant.remove(restaurant);
        res.setRestaurant(allRestaurant);
        return userRepository.save(res);
    }
}
