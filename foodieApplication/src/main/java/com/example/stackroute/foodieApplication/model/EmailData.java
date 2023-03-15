package com.example.stackroute.foodieApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailData {
    private String mailID;
    private String subject;
    private String message;
}
