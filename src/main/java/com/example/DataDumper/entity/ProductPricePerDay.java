package com.example.DataDumper.entity;

import java.util.Date;

public class ProductPricePerDay {
    private Date date;
    private Double price;
    private int id;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "ProductPricePerDay{" +
                "date='" + date + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
