package com.example.miniProject.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "BuyAt")
    private Date buyAt;
    @Column(name = "ShipAt")
    private Date shipAt;
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

    public Orders(int id, Date buyAt, Date shipAt, String status, int buyerid, int productId, float totalamount, int quantity, int buycampusid) {
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

    public Date getBuyAt() {
        return buyAt;
    }

    public void setBuyAt(Date buyAt) {
        this.buyAt = buyAt;
    }

    public Date getShipAt() {
        return shipAt;
    }

    public void setShipAt(Date shipAt) {
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
