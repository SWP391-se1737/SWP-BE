package com.example.miniProject.service;

import com.example.miniProject.model.Deposits;
import com.example.miniProject.repository.DepositRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepositService {
    @Autowired
    private DepositRepository repo;

    public List<Deposits> listDeposit(){
        return repo.findAll();
    }
    public void addDeposit(Deposits deposits) {
        repo.save(deposits);
    }

    public boolean deleteDeposit(int depoID) {
        if (repo.existsById(depoID)) {
            repo.deleteById(depoID);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateDeposit(Deposits newDeposit, int depoID) {
        Optional<Deposits> existingDeposit = repo.findById(depoID);
        if (existingDeposit.isPresent()) {
            Deposits deposits = existingDeposit.get();
            deposits.setUserID(newDeposit.getUserID());
            deposits.setExchangeProvider(newDeposit.getExchangeProvider());
            deposits.setCurrency_to(newDeposit.getCurrency_to());
            deposits.setCurrency_from(newDeposit.getCurrency_from());
            return true;
        } else {
            return false;
        }
    }
}
