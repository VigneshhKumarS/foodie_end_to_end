package com.example.stackroute.authentication.service;



import com.example.stackroute.authentication.model.User;

import java.util.Map;

public interface JwtTokenGenerator {
    public abstract Map<String,String> generateJwt(User user);
}
