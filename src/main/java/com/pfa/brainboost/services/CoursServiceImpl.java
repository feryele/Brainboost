package com.pfa.brainboost.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.brainboost.DAO.CoursRepository;
import com.pfa.brainboost.entities.Cours;
@Service
public class CoursServiceImpl implements CoursService{
    @Autowired
    private CoursRepository coursRep;

    @Override
    public Cours addCours(Cours cours) {
        return coursRep.save(cours);
    }

    @Override
    public Cours updateCours(Cours cours, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCours'");
    }

    @Override
    public Cours deleteCours(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCours'");
    }

    @Override
    public List<Cours> getAllCours() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCours'");
    }

    @Override
    public Cours getCours(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCours'");
    }
    
}
