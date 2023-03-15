package com.example.stackroute.foodieApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Items {
    private String itemName;
    private int price;
    private Binary itemImage;

}
