package com.pfa.brainboost.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.brainboost.entities.Cours;
@Repository
public interface CoursRepository extends JpaRepository<Cours,Long>{
    
}
