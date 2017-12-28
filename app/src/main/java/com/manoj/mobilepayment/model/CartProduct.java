package com.manoj.mobilepayment.model;

/**
 * Created by MBaghela1 on 11/16/2017.
 */

public class CartProduct {

    private int itemsCount=0;
    private Product product;



    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
