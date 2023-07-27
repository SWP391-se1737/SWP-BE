package com.example.miniProject.service;

import com.example.miniProject.model.*;
import com.example.miniProject.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
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
    @Autowired
    private MovingItemRepository movingItemRepo;
    @Autowired
    private ProductMovingRepository productMovingRepo;

    public List<Orders> getAllOrders(){

        return repo.findAllByOrderByBuyAtDesc();
    }
    public void createNewOrders(Orders order){
        try {
            // create order

            repo.save(order);
            System.out.println("Create Order success" + order);

            Optional<Products> product = productRepo.findById(order.getProductId());

            if (product.isPresent()) {
                if (product.get().getSellcampusid() != order.getBuycampusid()) {
                    //create moving item
                    MovingItems movingItems = new MovingItems();
                    movingItems.setId(0);
                    movingItems.setProductID(order.getProductId());
                    movingItemRepo.save(movingItems);
                    System.out.println("Create Moving Item success" + movingItems);
                    //create product moving
                    ProductMovings productMovings = new ProductMovings();
                    productMovings.setMovingId(movingItems.getId());
                    productMovings.setMovingDate(null);
                    productMovings.setArrivalDate(null);
                    productMovings.setFromLocation(product.get().getSellcampusid());
                    productMovings.setToLocation(order.getBuycampusid());
                    productMovings.setStatus("Đang chuẩn bị");
                    productMovings.setShipperId(null);
                    productMovingRepo.save(productMovings);
                    System.out.println("Create Product Moving success" + productMovings);
                }
            } else {
                throw new EntityNotFoundException("Product not found");
            }


            // create transaction when create order
            Transactions trans = new Transactions();
            trans.setAmount(order.getTotalamount());
            trans.setOrder_id(order.getId());
            trans.setId(0);
            trans.setProduct_id(order.getProductId());
            trans.setStatus("mua hàng");
            trans.setDeposit_id(null);
            trans.setWallet_user(order.getBuyerid());
            trans.setTransaction_datetime(new Timestamp(System.currentTimeMillis()));
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
//        Optional<Products> product = productRepo.findById(order.getProductId());
            if (product.isPresent()) {
                product.get().setStatus("Hết hàng");
                productRepo.save(product.get());
                System.out.println("Update Product success" + product);
            } else {
                throw new EntityNotFoundException("Product not found");
            }
        }catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        }
    }

    public void updateOrderById(int id, String status) {


        if(status.equals("Đã nhận hàng")) {
            // update wallet admin
            Optional<Orders> order = repo.findById(id);
            if (order.isPresent()) {

                Wallets existWalletAdmin = walletRepo.findByUserid(3);
                Optional<Wallets> walletAdmin = Optional.of(existWalletAdmin);
                walletAdmin.get().setBalance(walletAdmin.get().getBalance() - order.get().getTotalamount());
                walletRepo.save(walletAdmin.get());
                System.out.println("Update Wallet success" + walletAdmin);


            } else {
                throw new EntityNotFoundException("Order not found");
            }
            //update wallet seller
            Optional<Products> existProduct = productRepo.findById(order.get().getProductId());
            if (existProduct.isPresent()) {
                Wallets existWalletSeller = walletRepo.findByUserid(existProduct.get().getSeller_id());
                Optional<Wallets> walletSeller = Optional.of(existWalletSeller);
                walletSeller.get().setBalance(walletSeller.get().getBalance() + order.get().getTotalamount());
                walletRepo.save(walletSeller.get());
                System.out.println("Update Wallet success" + walletSeller);
            } else {
                throw new EntityNotFoundException("Product not found");
            }
            order.get().setStatus(status);
            repo.save(order.get());
            // create transaction
            Transactions trans = new Transactions();
            trans.setId(0);
            trans.setOrder_id(order.get().getId());
            trans.setWallet_user(3);
            trans.setAmount(order.get().getTotalamount());
            trans.setProduct_id(order.get().getProductId());
            trans.setDeposit_id(null);
            trans.setStatus("Admin chuyển tiền Seller");
            trans.setTransaction_datetime(new Timestamp(System.currentTimeMillis()));
            transRepo.save(trans);

        }else if (status.equals("Không nhận hàng")){
            // update wallet buyer

            Optional<Orders> order = repo.findById(id);
            if (order.isPresent()) {

                Wallets existWallet = walletRepo.findByUserid(order.get().getBuyerid());
                Optional<Wallets> wallet = Optional.of(existWallet);
                wallet.get().setBalance(wallet.get().getBalance() + order.get().getTotalamount());
                walletRepo.save(wallet.get());
                System.out.println("Update Wallet success" + wallet);

                //update wallet admin
                Wallets existWalletAdmin = walletRepo.findByUserid(3);
                Optional<Wallets> walletAdmin = Optional.of(existWalletAdmin);
                walletAdmin.get().setBalance(walletAdmin.get().getBalance() - order.get().getTotalamount());
                walletRepo.save(walletAdmin.get());
                System.out.println("Update Wallet  success" + walletAdmin);
                // set status product
                Optional<Products> product = productRepo.findById(order.get().getProductId());
                if (product.isPresent()) {
                    product.get().setStatus("Còn hàng");
                    productRepo.save(product.get());
                    System.out.println("Update Product success" + product);
                } else {
                    throw new EntityNotFoundException("Product not found");
                }
                order.get().setStatus(status);
                repo.save(order.get());
                //create transaction when update wallet
                Transactions tran = new Transactions();
                tran.setId(0);
                tran.setOrder_id(order.get().getId());
                tran.setWallet_user(3);
                tran.setAmount(order.get().getTotalamount());
                tran.setProduct_id(order.get().getProductId());
                tran.setDeposit_id(null);
                tran.setStatus("Admin chuyển tiền Buyer");
                tran.setTransaction_datetime(new Timestamp(System.currentTimeMillis()));
                transRepo.save(tran);

            } else {
                throw new EntityNotFoundException("Order not found");
            }


        }else{
            Optional<Orders> order = repo.findById(id);
            if(order.isPresent()) {
                order.get().setStatus(status);
                repo.save(order.get());
                System.out.println("Update Order success" + order);
            } else {
                throw new EntityNotFoundException("Order not found");
            }
        }


    }
    public void deleteOrderById(int id) {
        Optional<Orders> order = repo.findById(id);
        if(order.isPresent()) {
            order.get().setStatus("Hủy đơn hàng");
            repo.save(order.get());
            // update wallet buyer
            Wallets existWallet = walletRepo.findByUserid(order.get().getBuyerid());
            Optional<Wallets> wallet = Optional.of(existWallet);
            wallet.get().setBalance(wallet.get().getBalance() + order.get().getTotalamount());
            walletRepo.save(wallet.get());
            System.out.println("Update Wallet success" + wallet);
            //update wallet admin
            Wallets existWalletAdmin = walletRepo.findByUserid(3);
            Optional<Wallets> walletAdmin = Optional.of(existWalletAdmin);
            walletAdmin.get().setBalance(walletAdmin.get().getBalance() - order.get().getTotalamount());
            walletRepo.save(walletAdmin.get());
            System.out.println("Update Wallet success" + walletAdmin);
            // set status product
            Optional<Products> product = productRepo.findById(order.get().getProductId());
            if (product.isPresent()) {
                product.get().setStatus("Còn hàng");
                productRepo.save(product.get());
                System.out.println("Update Product success" + product);
            } else {
                throw new EntityNotFoundException("Product not found");
            }
            System.out.println("Delete Order success" + order);
            // create transaction
            Transactions transaction = new Transactions();
            transaction.setId(0);
            transaction.setOrder_id(order.get().getId());
            transaction.setWallet_user(order.get().getBuyerid());
            transaction.setAmount(order.get().getTotalamount());
            transaction.setProduct_id(order.get().getProductId());
            transaction.setDeposit_id(null);
            transaction.setStatus("hủy hàng");
            transaction.setTransaction_datetime(new Timestamp(System.currentTimeMillis()));
            transRepo.save(transaction);
            System.out.println("Create Transaction success" + transaction);

        } else {
            throw new EntityNotFoundException("Order not found");
        }
    }




    public Optional<Orders> getOrderById(int id){
        return repo.findById(id);
    }

    public List<Orders> getOrderByBuyerId(int buyerid){
        return repo.findByBuyerIdAndOrderByBuyAtDesc(buyerid);
    }



}
