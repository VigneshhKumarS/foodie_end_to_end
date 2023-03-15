package com.example.stackroute.foodieApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cuisine {
    private String cuisineName;
    private List<Items> items;
}
