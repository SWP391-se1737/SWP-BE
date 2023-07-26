package com.example.miniProject.service;

import com.example.miniProject.model.Categories;
import com.example.miniProject.model.Products;
import com.example.miniProject.model.Transactions;
import com.example.miniProject.model.Wallets;
import com.example.miniProject.repository.CategoriesRepository;
import com.example.miniProject.repository.ProductRepository;
import com.example.miniProject.repository.TransactionRepository;
import com.example.miniProject.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repo;
    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private WalletRepository walletRepo;

    @Autowired



    public List<Products> getAllProduct(){

        return repo.findAll();
    }
    // create product auto create transaction minus wallwet 10 point and bonus wallet admin 10 point
    public void createNewProduct(Products product) {
            // create product
            repo.save(product);
            System.out.println("Create Product success" + product);
            // create transaction when create product
            Transactions trans = new Transactions();
            trans.setAmount(10);
            trans.setProduct_id(product.getId());
            trans.setDeposit_id(null);
            trans.setOrder_id(null);
            trans.setId(0);
            trans.setWallet_user(product.getSeller_id());
            trans.setStatus("tạo sản phẩm");
            trans.setTransaction_datetime(new Timestamp(System.currentTimeMillis()));
            transRepo.save(trans);
            System.out.println("Create Transaction success" + trans);
            // update wallet seller
            Wallets existWallet = walletRepo.findByUserid(product.getSeller_id());
            Optional<Wallets> wallet = Optional.of(existWallet);
            if (wallet.get().getBalance() < 10) {
                throw new EntityNotFoundException("Not enough money");
            }
            wallet.get().setBalance(wallet.get().getBalance() - 10);
            walletRepo.save(wallet.get());
            System.out.println("Update Wallet success" + wallet);
            // update wallet admin
            Wallets existWalletAdmin = walletRepo.findByUserid(3);
            Optional<Wallets> walletAdmin = Optional.of(existWalletAdmin);
            walletAdmin.get().setBalance(walletAdmin.get().getBalance() + 10);
            walletRepo.save(walletAdmin.get());
            System.out.println("Update Wallet Admin success" + walletAdmin);
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
            exist.get().setSeller_id(product.getSeller_id());
            exist.get().setSellcampusid(product.getSellcampusid());
            exist.get().setCategoryid(product.getCategoryid());
            // save to db
            repo.save(exist.get());

        } else {

            throw new EntityNotFoundException("Not found: " + id);
        }

    }

    public void deleteProductById(int id){
         Optional<Products> exist = repo.findById(id);
        if(exist.isPresent()) {
            exist.get().setStatus("Hết hạn");
            // save to db
            repo.save(exist.get());
         } else  {
            throw new EntityNotFoundException("Not found: " + id);
        }

    }
    public void deleteProductByStudent(int id){
        Optional<Products> exist = repo.findById(id);
        if(exist.isPresent()) {
            exist.get().setStatus("Xóa bài");
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
    
    public void autoUpdateProductStatus(){
        List<Products> products = repo.findAll();
        for (Products product : products) {
            if (product.getStatus().equals("Còn hàng")) {
                if (product.getExpire().isBefore(new Timestamp(System.currentTimeMillis()).toLocalDateTime())) {
                    product.setStatus("Hết hạn");
                    repo.save(product);
                }
            }
        }
    }

    public List<Products> getProductBySellerId(int seller_id){
        return repo.findBySellerIdOrderByCreateATDesc(seller_id);
    }
}
