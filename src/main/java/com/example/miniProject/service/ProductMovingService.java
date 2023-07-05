package com.example.miniProject.service;

import com.example.miniProject.model.ProductMovings;
import com.example.miniProject.repository.ProductMovingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductMovingService {
    @Autowired
    private ProductMovingRepository repo;

    public List<ProductMovings> getAllProductMoving(){ return repo.findAll();}


    public void createNewProductMoving(ProductMovings productMoving){
            repo.save(productMoving);
    }

    public void updateProductMovingByMovingId( int MovingId, ProductMovings ProductMoving){
        Optional<ProductMovings> exist = repo.findById(MovingId);
        System.out.println("Exist" + exist);
        if(exist.isPresent()){
            exist.get().setMovingDate(ProductMoving.getMovingDate());
            exist.get().setArrivalDate(ProductMoving.getArrivalDate());
            exist.get().setFromLocation(ProductMoving.getFromLocation());
            exist.get().setToLocation(ProductMoving.getToLocation());
            exist.get().setStatus(ProductMoving.isStatus());
            exist.get().setShipperId(ProductMoving.getShipperId());

        }else{

            throw new RuntimeException("Not found: " + MovingId);
        }

    }
    public void deleteProductMovingByMovingId(int MovingId, ProductMovings ProductMoving){
        Optional<ProductMovings> exist = repo.findById(MovingId);
        if(exist.isPresent()){
            exist.get().setStatus(ProductMoving.isStatus());
        }else{
            throw new RuntimeException("Not found: " + MovingId);
        }
    }
    public Optional<ProductMovings> getProductMovingByMovingId(int MovingId){
       return repo.findById(MovingId);
    }
}
