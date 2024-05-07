package com.pfa.brainboost.web.models;




import java.util.ArrayList;
import java.util.List;

import com.pfa.brainboost.web.models.request.UserAuth;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cours{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double price;
    private boolean isFree;
    //@ElementCollection
    private List<String> users;
    //private FeedBack feedback;

    public Cours (String title, String description, double price, boolean isFree, List<String> users) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.isFree = isFree;
        this.users = users;
    }

    public boolean getIsFree() {
        return this.isFree;
    }
}