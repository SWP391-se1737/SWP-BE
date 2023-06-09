package com.example.miniProject.service;

import com.example.miniProject.model.Orders;
import com.example.miniProject.model.Products;
import com.example.miniProject.model.Transactions;
import com.example.miniProject.model.Wallets;
import com.example.miniProject.repository.OrderRepository;
import com.example.miniProject.repository.ProductRepository;
import com.example.miniProject.repository.TransactionRepository;
import com.example.miniProject.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository repo;
    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private ProductRepository productRepo;

    public List<Orders> getAllOrders(){

        return repo.findAll();
    }
    public void createNewOrders(Orders order){
        try {
            repo.save(order);
            System.out.println("Create Order success" + order);
        } catch (Exception e) {
            throw new EntityNotFoundException("Create Order fail" + e);
        }
        // create transaction when create order
        Transactions trans = new Transactions();
        trans.setAmount(order.getTotalamount());
        trans.setOrder_id(order.getId());
        trans.setStatus(true);
        trans.setId(0);
        trans.setProduct_id(order.getProductId());
        trans.setDescription("mua hàng");
        trans.setDeposit_id(null);
        trans.setWallet_user(order.getBuyerid());
        transRepo.save(trans);
        System.out.println("Create Transaction success" + trans);
        // update wallet buyer
        Wallets existWallet = walletRepo.findByUserid(order.getBuyerid());
        Optional<Wallets> wallet = Optional.of(existWallet);
        if (wallet.get().getBalance() < order.getTotalamount()) {
            throw new EntityNotFoundException("Not enough money");
        } else {
            wallet.get().setBalance(wallet.get().getBalance() - order.getTotalamount());
            walletRepo.save(wallet.get());
            System.out.println("Update Wallet success" + wallet);
        }
        //update wallet admin
        Wallets existWalletAdmin = walletRepo.findByUserid(3);
        Optional<Wallets> walletAdmin = Optional.of(existWalletAdmin);
        walletAdmin.get().setBalance(walletAdmin.get().getBalance() + order.getTotalamount());
        walletRepo.save(walletAdmin.get());
        System.out.println("Update Wallet success" + walletAdmin);
        //set status product
        Optional<Products> product = productRepo.findById(order.getProductId());
        if(product.isPresent()) {
            product.get().setStatus("Hết hàng");
            productRepo.save(product.get());
            System.out.println("Update Product success" + product);
        } else {
            throw new EntityNotFoundException("Product not found");
        }
    }

    public void updateOrderById(int id, String status) {
        Optional<Orders> exist = repo.findById(id);
        System.out.println("Exist" + exist);
        if(exist.isPresent()){
            exist.get().setStatus(status);
        } else {
            throw new EntityNotFoundException("Order not found");
        }
    }




    public Optional<Orders> getOrderById(int id){
        return repo.findById(id);
    }

    public List<Orders> getOrderByBuyerId(int buyerid){
        return repo.findByBuyerid(buyerid);
    }
}
