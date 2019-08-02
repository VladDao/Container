package com.plietnov.task1.entity;

import java.io.Serializable;

public abstract class Product implements Serializable {

    private int id;

    private String nameOfProduct;

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
