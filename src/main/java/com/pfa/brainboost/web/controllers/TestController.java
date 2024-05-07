package com.pfa.brainboost.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pfa.brainboost.DAO.CoursRepository;
import com.pfa.brainboost.web.models.Cours;
import com.pfa.brainboost.web.models.request.UserAuth;

@Controller
public class TestController {

    @Autowired
    private CoursRepository cr;


    @GetMapping("/profil")
    public String getProfil(){
        return "logged";
    }

    @GetMapping("/acceuil")
    public String getAcceuil(){
        return "pageMain";
    }

    @GetMapping("/payement")
    public String getPayement(){
        return "payement";
    }




    
}
