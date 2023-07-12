package com.example.miniProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Wallets")
public class Wallets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "UserId")
    private int userid;
    @Column(name = "Balance")
     private double balance;

    public Wallets() {
    }

    public Wallets(int id, int userid, double balance) {
        this.id = id;
        this.userid = userid;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
