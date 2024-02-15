package com.tutorialCrud.model;


import java.math.BigDecimal;
import java.util.List;

public class Item {
    private String name;
    private List<String> tags;
    private BigDecimal price;
    private int quantity;

    public Item() {
    }

    public Item(String name, List<String> tags, BigDecimal price, int quantity) {
        this.name = name;
        this.tags = tags;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

