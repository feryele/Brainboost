package com.pfa.brainboost.services;

import com.pfa.brainboost.request.signUpRequest;
import java.lang.String;
import org.springframework.stereotype.Service;


@Service
public class SignUpService {
    public String signUp(signUpRequest request) {
        return "works";
    }

}
