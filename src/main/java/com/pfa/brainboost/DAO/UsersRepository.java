package com.pfa.brainboost.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pfa.brainboost.web.models.User;

public interface UsersRepository extends JpaRepository<User,Long>{

    List<User> findByNameContainingIgnoreCase(String name);
    
}