package com.example.miniProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "BuyAt", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp buyAt;
    @Column(name = "ShipAt", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shipAt;
    @Column(name = "status")
    private String status;
    @Column(name = "BuyerId")
    private int buyerid;
    @Column(name = "ProductId")
    private int productId;
    @Column(name = "TotalAmount")
    private float totalamount;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "BuyCampusId")
    private int buycampusid;

    public Orders() {
    }

    public Orders(int id, Timestamp buyAt, LocalDateTime shipAt, String status, int buyerid, int productId, float totalamount, int quantity, int buycampusid) {
        this.id = id;
        this.buyAt = buyAt;
        this.shipAt = shipAt;
        this.status = status;
        this.buyerid = buyerid;
        this.productId = productId;
        this.totalamount = totalamount;
        this.quantity = quantity;
        this.buycampusid = buycampusid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getBuyAt() {
        return buyAt;
    }

    public void setBuyAt(Timestamp buyAt) {
        this.buyAt = buyAt;
    }

    public LocalDateTime getShipAt() {
        return shipAt;
    }

    public void setShipAt(LocalDateTime shipAt) {
        this.shipAt = shipAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(int buyerid) {
        this.buyerid = buyerid;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(float totalamount) {
        this.totalamount = totalamount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBuycampusid() {
        return buycampusid;
    }

    public void setBuycampusid(int buycampusid) {
        this.buycampusid = buycampusid;
    }
}
