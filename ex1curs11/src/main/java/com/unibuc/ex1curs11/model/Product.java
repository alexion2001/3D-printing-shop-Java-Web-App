package com.unibuc.ex1curs11.model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    private String name;

    private double price;

    @ManyToOne
    @JoinColumn(name="filament_id")
    private Filament filamentType;


    public Product(long productId) {
        this.productId = productId;
    }

    public Product(long productId, String name, double price, Filament filamentType) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.filamentType = filamentType;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Filament getFilamentType() {
        return filamentType;
    }

    public void setFilamentType(Filament filamentType) {
        this.filamentType = filamentType;
    }

    @Override
    public String toString() {
        return "Product: " +
                "name=" + name +
                ", price=" + price;
    }
}