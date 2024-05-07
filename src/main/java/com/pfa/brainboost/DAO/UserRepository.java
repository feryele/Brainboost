package com.pfa.brainboost.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfa.brainboost.web.models.request.UserAuth;

public interface UserRepository extends JpaRepository<UserAuth, Long>{
    
}
