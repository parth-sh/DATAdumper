package com.example.DataDumper.entity;

public class ProductPricePerDay {
    private String date;
    private Double price;
    private int id;

    public ProductPricePerDay(String date, Double price, int id) {
        this.date = date;
        this.price = price;
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
