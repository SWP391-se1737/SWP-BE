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
    public int addDeposit(Deposits deposits) {
        Deposits savedDeposit = repo.save(deposits);
        return savedDeposit.getId(); // Giả sử ID của Deposit được lưu trong trường "id"
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
            deposits.setUser_id(newDeposit.getUser_id());
            deposits.setExchangeProvider(newDeposit.getExchangeProvider());
            deposits.setCurrency_to(newDeposit.getCurrency_to());
            deposits.setCurrency_from(newDeposit.getCurrency_from());
            return true;
        } else {
            return false;
        }
    }
}
