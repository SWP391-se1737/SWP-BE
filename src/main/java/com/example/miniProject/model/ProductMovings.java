package com.example.miniProject.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "ProductMovings")
public class ProductMovings {

    @Id
    @Column(name = "MovingId")
    private int MovingId;
    @Column(name= "MovingDate")
    private Date MovingDate;
    @Column(name = "ArrivalDate")
    private Date ArrivalDate;
    @Column(name = "FromLocation")
    private int FromLocation;
    @Column(name = "ToLocation")
    private int ToLocation;
    @Column(name = "Status")
    private boolean status;
    @Column(name = "ShipperId")
    private int ShipperId;

    public ProductMovings() {
    }

    public ProductMovings(int movingId, Date movingDate, Date arrivalDate, int fromLocation, int toLocation, boolean status, int shipperId) {
        this.MovingId = movingId;
        this.MovingDate = movingDate;
        this.ArrivalDate = arrivalDate;
        this.FromLocation = fromLocation;
        this.ToLocation = toLocation;
        this.status = status;
        this.ShipperId = shipperId;
    }

    public int getMovingId() {
        return MovingId;
    }

    public void setMovingId(int movingId) {
        MovingId = movingId;
    }

    public Date getMovingDate() {
        return MovingDate;
    }

    public void setMovingDate(Date movingDate) {
        MovingDate = movingDate;
    }

    public Date getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public int getFromLocation() {
        return FromLocation;
    }

    public void setFromLocation(int fromLocation) {
        FromLocation = fromLocation;
    }

    public int getToLocation() {
        return ToLocation;
    }

    public void setToLocation(int toLocation) {
        ToLocation = toLocation;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getShipperId() {
        return ShipperId;
    }

    public void setShipperId(int shipperId) {
        ShipperId = shipperId;
    }
}
