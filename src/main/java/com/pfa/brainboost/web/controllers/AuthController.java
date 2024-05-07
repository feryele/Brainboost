package com.pfa.brainboost.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfa.brainboost.DAO.UserOAuthRepository;
import com.pfa.brainboost.web.models.request.UserAuth;

@Controller
public class AuthController {

    @Autowired
    private UserOAuthRepository userRep;
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/signup")
    public String signUp(){
        return "signUp";
    }
    @PostMapping("/createUser")
     public ResponseEntity< UserAuth>  addUser(@RequestBody UserAuth user) {
        return ResponseEntity.ok(userRep.save(user));
    }

}
