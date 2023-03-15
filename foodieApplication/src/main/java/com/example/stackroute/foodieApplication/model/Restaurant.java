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
public class Restaurant {
    @Id
    private String name;
    private String ownerEmailID;
    private String location;
    private Binary restImage;
    private List<Cuisine> cuisines;

}
