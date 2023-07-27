package com.example.miniProject.service;

import com.example.miniProject.model.MovingItems;
import com.example.miniProject.repository.MovingItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class MovingItemService {
    @Autowired
    private MovingItemRepository repo;

    public List<MovingItems> listItem(){
        return repo.findAll();
    }
    public void addItem(MovingItems items) {
        repo.save(items);
    }
    public boolean deleteItem(int itemID) {
        if (repo.existsById(itemID)) {
            repo.deleteById(itemID);
            return true;
        } else {
            return false;
        }
    }


    public List <MovingItems> findItemById(int id){
        return repo.findById(id);
    }
}
