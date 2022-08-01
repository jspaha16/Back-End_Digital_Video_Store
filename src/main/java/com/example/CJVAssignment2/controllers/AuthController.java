package com.example.CJVAssignment2.controllers;


import com.example.CJVAssignment2.CustomizedResponse;
import com.example.CJVAssignment2.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value="/auth", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity login(@RequestBody UserModel user){



        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            var customizedResponse = new CustomizedResponse("You logged in successfully!", null);

            return new ResponseEntity(customizedResponse, HttpStatus.OK);
        }catch (BadCredentialsException ex){
            var customizedResponse = new CustomizedResponse("Your credentials are incorrect", null);

            return new ResponseEntity(customizedResponse, HttpStatus.UNAUTHORIZED);
        }



    }

}
