package com.example.stackroute.foodieApplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Already Existing",code = HttpStatus.CONFLICT)
public class AlreadyExistingException extends Exception{
}
