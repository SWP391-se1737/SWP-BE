package com.example.miniProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Order_id", nullable = true)
    private Integer order_id;
    @Column(name = "Wallet_user")
    private int wallet_user;
    @Column(name = "Amount")
    private float amount;

    @Column(name = "Product_id", nullable = true)
    private Integer product_id;
    @Column(name = "Status")
    private String status;
    @Column(name = "deposit_id", nullable = true)
    private Integer deposit_id;
    @Column(name = "Transaction_Datetime", nullable = false, updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp transaction_datetime;

    public Transactions() {
    }

    public Transactions(int id, Integer order_id, int wallet_user, float amount, Integer product_id, String status, Integer deposit_id, Timestamp transaction_datetime) {
        this.id = id;
        this.order_id = order_id;
        this.wallet_user = wallet_user;
        this.amount = amount;
        this.product_id = product_id;
        this.status = status;
        this.deposit_id = deposit_id;
        this.transaction_datetime = transaction_datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public int getWallet_user() {
        return wallet_user;
    }

    public void setWallet_user(int wallet_user) {
        this.wallet_user = wallet_user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDeposit_id() {
        return deposit_id;
    }

    public void setDeposit_id(Integer deposit_id) {
        this.deposit_id = deposit_id;
    }

    public Timestamp getTransaction_datetime() {
        return transaction_datetime;
    }

    public void setTransaction_datetime(Timestamp transaction_datetime) {
        this.transaction_datetime = transaction_datetime;
    }
}
