package com.pfa.brainboost.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.brainboost.services.SignUpService;
import com.pfa.brainboost.request.signUpRequest;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/signUp" )
@AllArgsConstructor


public class SignUpController {
    
    
    private final SignUpService signUpService;

    public String signUp(@RequestBody signUpRequest request){
        return signUpService.signUp(request);
    }

}
