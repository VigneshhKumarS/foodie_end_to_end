package com.example.stackroute.foodieApplication.controller;

import com.example.stackroute.foodieApplication.model.Cuisine;
import com.example.stackroute.foodieApplication.model.Items;
import com.example.stackroute.foodieApplication.model.Restaurant;
import com.example.stackroute.foodieApplication.service.RestaurantService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/restaurant-app")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // URL : http://localhost:8888/restaurant-app/add-restaurant

    @PostMapping("/add-restaurant")
    public ResponseEntity<?> addRestaurant(@RequestParam String name,@RequestParam String ownerEmailId,@RequestParam String location,@RequestBody MultipartFile file) throws IOException {
        return new ResponseEntity<>(restaurantService.addRestaurant(name,ownerEmailId,location,file), HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/delete-restaurant

    @DeleteMapping("/delete-restaurant/{restaurantName}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable String restaurantName){
        return new ResponseEntity<>(restaurantService.deleteRestaurant(restaurantName),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/edit-restaurant

    @PutMapping("/edit-restaurant")
    public ResponseEntity<?> editRestaurant(@RequestBody Restaurant restaurant){
        return new ResponseEntity<>(restaurantService.editRestaurant(restaurant),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/add-items-to-cart

    @PostMapping("/add-items-to-cart")
    public ResponseEntity<?> addItemsToCart(@RequestParam String itemName, @RequestParam int price, HttpServletRequest servletRequest){
        String emailID=(String)servletRequest.getAttribute("current_user_emailID");
        Items items=new Items(itemName,price,null);
        return new ResponseEntity<>(restaurantService.addItemsToCart(emailID,items),HttpStatus.OK);
    }
    // URL : http://localhost:8888/restaurant-app/add-quantity/{itemName}/{price}
    @PostMapping("/add-quantity/{itemName}/{price}")
    public ResponseEntity<?> addQuantity(@PathVariable String itemName,@PathVariable int price,@RequestBody String emailID,HttpServletRequest servletRequest){
//        String emailID=(String)servletRequest.getAttribute("current_user_emailID");
        Items items=new Items(itemName,price,null);
        return new ResponseEntity<>(restaurantService.addItemsToCart(emailID,items),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/get-all-restaurants

    @GetMapping("/get-all-restaurants")
    public ResponseEntity<?> getAllRestaurants(){
        return new ResponseEntity<>(restaurantService.getAllRestaurants(),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/get-restaurant-by-location

    @GetMapping("/get-restaurant-by-location/{location}")
    public ResponseEntity<?> getRestaurantsByLocation(@PathVariable String location){
        return new ResponseEntity<>(restaurantService.getRestaurantByLocation(location),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/get-restaurant-by-cuisineName

    @GetMapping("/get-restaurant-by-cuisineName/{cuisineName}")
    public ResponseEntity<?> getRestaurantByCuisineName(@PathVariable String cuisineName) {
        return new ResponseEntity<>(restaurantService.getRestaurantByCuisineName(cuisineName), HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/get-restaurant-by-itemName

    @GetMapping("/get-restaurant-by-itemName/{itemName}")
    public ResponseEntity<?> getRestaurantByFoodName(@PathVariable String itemName){
        return new ResponseEntity<>(restaurantService.getRestaurantByFoodName(itemName),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/get-restaurant-by-name
    @GetMapping("/get-restaurant-by-name/{name}")
    public ResponseEntity<?> getRestaurantByName(@PathVariable String name){
        return new ResponseEntity<>(restaurantService.getRestaurantByName(name),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/get-restaurant-by-owner

    @GetMapping("/get-restaurant-by-owner")
    public ResponseEntity<?> getRestaurantByOwnerName(HttpServletRequest servletRequest){
        String emailID=(String)servletRequest.getAttribute("current_user_emailID");
        return new ResponseEntity<>(restaurantService.getRestaurantByOwner(emailID),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/remove-item-from-cart
    @PostMapping("/remove-item-from-cart/{itemName}/{price}")
    public ResponseEntity<?> deleteItemsFromCart(@PathVariable String itemName,@PathVariable int price,@RequestBody String emailID, HttpServletRequest servletRequest){
//        String emailID=(String)servletRequest.getAttribute("current_user_emailID");
        System.out.println(emailID);
        Items item=new Items(itemName,price,null);
        System.out.println(item);
        return new ResponseEntity<>(restaurantService.removeItemFromCart(emailID,item),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/remove-restaurant-from-fav-list
    @PostMapping("/remove-restaurant-from-fav-list")
    public ResponseEntity<?> deleteRestaurantFromFav(@RequestBody Restaurant restaurant,HttpServletRequest servletRequest){
        String emailID=(String)servletRequest.getAttribute("current_user_emailID");
        System.out.println(emailID);
        return new ResponseEntity<>(restaurantService.removeFavRestaurant(emailID,restaurant),HttpStatus.OK);
    }


    // URL : http://localhost:8888/restaurant-app/place-order
    @GetMapping("/place-order")
    public ResponseEntity<?> placeOrder(HttpServletRequest servletRequest){
        String emailID=(String)servletRequest.getAttribute("current_user_emailID");
        return new ResponseEntity<>(restaurantService.placeOrder(emailID),HttpStatus.OK);
    }

    // URL : http://localhost:8888/restaurant-app/add-cuisines/
    @PostMapping("/add-cuisines/{name}")
    public ResponseEntity<?> addCuisine(@PathVariable String name,@RequestBody String cuisineName){
        return new ResponseEntity<>(restaurantService.addCuisine(name, cuisineName),HttpStatus.OK);
    }
    // URL : http://localhost:8888/restaurant-app/add-items-to-cuisine/
    @PostMapping("/add-items-to-cuisine/{name}/{cuisineName}")
    public ResponseEntity<?> addItemsToCuisine(@PathVariable String name,@PathVariable String cuisineName,@RequestParam String itemName,@RequestParam int price,@RequestParam MultipartFile file) throws IOException {
        return new ResponseEntity<>(restaurantService.addItemsToCuisine(name, cuisineName, itemName,price,file),HttpStatus.OK);
    }
    // URL : http://localhost:8888/restaurant-app/remove-cuisine-from-rest/
    @PostMapping("/remove-cuisine-from-rest/{name}")
    public ResponseEntity<?> removeCuisine(@PathVariable String name, @RequestBody Cuisine cuisine){
        return new ResponseEntity<>(restaurantService.removeCuisine(name, cuisine),HttpStatus.OK);
    }
    // URL : http://localhost:8888/restaurant-app/remove-items-from-cuisine/
    @PostMapping("/remove-items-from-cuisine/{name}/{cuisineName}")
    public ResponseEntity<?> removeItem(@PathVariable String name, @PathVariable String cuisineName,@RequestBody String itemName){
        return new ResponseEntity<>(restaurantService.removeItems(name,cuisineName,itemName),HttpStatus.OK);
    }

}
