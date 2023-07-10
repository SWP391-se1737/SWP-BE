package com.example.miniProject.service;

import com.example.miniProject.model.Categories;
import com.example.miniProject.model.Products;
import com.example.miniProject.repository.CategoriesRepository;
import com.example.miniProject.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Products> getAllProduct(){

        return repo.findAll();
    }

    public void createNewProduct(Products product){
            System.out.println(product.getCreateAT());
            repo.save(product);

    }

    public void updateProductById(int id, Products product) {
        Optional<Products> exist = repo.findById(id);
        System.out.println("Exist" + exist);
        if (exist.isPresent()) {
            exist.get().setName(product.getName());
            exist.get().setImage(product.getImage());
            exist.get().setDescription(product.getDescription());
            exist.get().setPrice(product.getPrice());
            exist.get().setCreateAT(product.getCreateAT());
            exist.get().setExpire(product.getExpire());
            exist.get().setStatus(product.getStatus());
            exist.get().setQuantity(product.getQuantity());
            exist.get().setBuycampus_id(product.getBuycampus_id());
            exist.get().setSeller_id(product.getSeller_id());
            exist.get().setSellcampusid(product.getSellcampusid());
            exist.get().setCategoryid(product.getCategoryid());

        } else {

            throw new EntityNotFoundException("Not found: " + id);
        }

    }

    public void deleteProductById(int id){
         Optional<Products> exist = repo.findById(id);
        if(exist.isPresent()) {
            exist.get().setStatus("Hết hàng");
            // save to db
            repo.save(exist.get());
         } else  {
            throw new EntityNotFoundException("Not found: " + id);
        }

    }
    public  Optional<Products> getProductById(int   id){return repo.findById(id);}

    public List<Products> searchProductByName(String name){
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Products> filterProductByCategory(int categoryid){
        return repo.findByCategory_id(categoryid);
    }

    public List<Products> filterProductByCampus(int sellcampusid){
        List<Products> products = repo.findAll();
        if (sellcampusid == 0) {
            return products;
        } else {
            List<Products> result = new ArrayList<>();
            for (Products product : products) {
                if (product.getSellcampusid() == sellcampusid) {
                    result.add(product);
                }
            }
            return result;
        }
    }

    public List<Products> getAllProductOrderByCreate_ATDesc(){
        return repo.findAllByOrderByCreateATDesc();
    }

    public List<Products> getProductsByCategory_idAndSellCampus_id(int category_id,int sellcampus_id){
        if (sellcampus_id == 0 && category_id == 0) {
            return repo.findAll();
        } else {
            return repo.findByCategory_idAndSellCampus_id(category_id, sellcampus_id);
        }
    }

    public List<Products> getProductBySellerId(int seller_id){
        return repo.findBySeller_id(seller_id);
    }
}
