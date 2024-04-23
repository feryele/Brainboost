package com.pfa.brainboost.services;

import java.util.List;

import com.pfa.brainboost.entities.Cours;

public interface CoursService {
    Cours addCours(Cours cours);
    Cours updateCours(Cours cours, Long id);
    Cours deleteCours(Long id);
    List<Cours> getAllCours();
    Cours getCours(Long id);





}
