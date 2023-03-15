package com.example.stackroute.foodieApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class User {
    @Id
    private String emailID;
    private String name, location, mobileNo;
//    private String role;
    private Binary profile;
    private List<Restaurant> restaurant;
    private List<Items> cartItems;

}
