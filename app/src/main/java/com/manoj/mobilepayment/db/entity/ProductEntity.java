package com.manoj.mobilepayment.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.manoj.mobilepayment.model.Product;

/**
 * Created by MBaghela1 on 11/9/2017.
 */

@Entity(tableName = "products")
public class ProductEntity implements Product {


    @PrimaryKey
    private int id;
    private String title;
    private String details;
    private int price;

    public ProductEntity(){

    }
    public ProductEntity(Product product){
        this.id = product.getId();
        this.title = product.getTitle();
        this.details = product.getDetails();
        this.price = product.getPrice();
    }


    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
