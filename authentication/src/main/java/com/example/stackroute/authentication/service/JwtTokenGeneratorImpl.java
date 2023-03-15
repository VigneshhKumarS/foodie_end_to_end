package com.example.stackroute.authentication.service;


import com.example.stackroute.authentication.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtTokenGeneratorImpl implements JwtTokenGenerator{
    @Override
    public Map<String, String> generateJwt(User user) {
        Map<String,String> result=new HashMap<String,String>();
        Map<String,Object> userData=new HashMap<>();
        userData.put("emailID",user.getEmailID());
        userData.put("role",user.getRole());
        String jwt= Jwts.builder()
                .setClaims(userData)
                .setIssuedAt(new Date())
                .setIssuer("My Company")
                .signWith(SignatureAlgorithm.HS512,"I_Dont_Say")
                .compact();
        result.put("token",jwt);
        result.put("message","Login Successfull. Token Generated");
        result.put("role",user.getRole());
        return result;
    }
}
