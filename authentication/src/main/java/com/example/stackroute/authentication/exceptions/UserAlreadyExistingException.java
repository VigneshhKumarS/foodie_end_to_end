package com.example.stackroute.authentication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "User Already Exists",code = HttpStatus.CONFLICT)
public class UserAlreadyExistingException extends Exception{
}
