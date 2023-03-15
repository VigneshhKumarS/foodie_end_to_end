package com.example.stackroute.authentication.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="FOODIE-APP",url="localhost:8888")
public interface UserProxy {
    @PostMapping("/user-app/register-user")
    public abstract ResponseEntity<?> sendDTOtoFoodieApplication(@RequestBody UserDTO userDTO);
}
