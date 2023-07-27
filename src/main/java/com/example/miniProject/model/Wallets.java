package com.example.miniProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Wallets")
public class Wallets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private int userid;
    @Column(name = "Balance")
     private double balance;

    public Wallets() {
    }

    public Wallets( int userid, double balance) {

        this.userid = userid;
        this.balance = balance;
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
