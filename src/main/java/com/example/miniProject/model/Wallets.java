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
     private float balance;

    public Wallets() {
    }

    public Wallets(int id, int userid, float balance) {
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

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
