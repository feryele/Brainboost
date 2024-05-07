package com.pfa.brainboost.DAO;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.brainboost.web.models.Cours;

public interface CoursRepository extends JpaRepository<Cours, Long> {

    List<Cours> findByTitleContainingIgnoreCase(String title);
}