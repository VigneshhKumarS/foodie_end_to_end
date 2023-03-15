package com.example.stackroute.foodieApplication.controller;

import com.example.stackroute.foodieApplication.model.OwnerRequest;
import com.example.stackroute.foodieApplication.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request-app")
public class RequestController {
    @Autowired
    private RequestService requestService;

    // URL : http://localhost:8888/request-app/add-request
    @PostMapping("/add-request")
    public ResponseEntity<?> addRequest(@RequestBody OwnerRequest ownerRequest){
        return new ResponseEntity<>(requestService.requestRepo(ownerRequest), HttpStatus.OK);
    }

    // URL : http://localhost:8888/request-app/get-all-request
    @GetMapping("/get-all-request")
    public ResponseEntity<?> getAllRequests(){
        return new ResponseEntity<>(requestService.allRequests(), HttpStatus.OK);
    }

    // URL : http://localhost:8888/request-app/delete-req-by-ID

    @DeleteMapping("/delete-req-by-ID/{emailID}")
    public ResponseEntity<?> removeReqByID(@PathVariable String emailID){
        return new ResponseEntity<>(requestService.removeReq(emailID),HttpStatus.OK);
    }
}
