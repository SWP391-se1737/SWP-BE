package com.example.miniProject.service;



import com.example.miniProject.model.Campuses;
import com.example.miniProject.repository.CampusesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CampusService {
    @Autowired
    private CampusesRepository repo;

    public List<Campuses> listCampus(){
        return repo.findAll();
    }
    public void addCampus(Campuses cp) {
        repo.save(cp);
    }
    public boolean deleteCampus(int cpID) {
        if (repo.existsById(cpID)) {
            repo.deleteById(cpID);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateCampus(Campuses newCampus, int campusId) {
        Optional<Campuses> existingCampus = repo.findById(campusId);
        if (existingCampus.isPresent()) {
            Campuses campuses = existingCampus.get();
            campuses.setName(newCampus.getName());
            campuses.setAddress(newCampus.getAddress());
            campuses.setLatitude(newCampus.getLatitude());
            campuses.setLongitude(newCampus.getLongitude());
            return true;
        } else {
            return false;
        }
    }
}
