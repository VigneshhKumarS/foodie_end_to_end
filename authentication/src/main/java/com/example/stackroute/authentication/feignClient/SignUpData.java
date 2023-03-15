package com.example.stackroute.authentication.feignClient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpData {
    private String emailID,name,password,location,mobileNo,role;
}
