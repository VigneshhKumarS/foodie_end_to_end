package com.example.stackroute.foodieApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Not Found" ,code = HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {
}
