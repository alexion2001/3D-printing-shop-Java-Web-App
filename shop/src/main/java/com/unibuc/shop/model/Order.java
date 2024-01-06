package com.unibuc.shop.model;

import javax.persistence.*;


@Entity
@Table(name="order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    private String date;


    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customerNumber;

    public Order() {
    }

    public Order(long orderId, String date, Customer customer) {
        this.orderId = orderId;
        this.date = date;
        this.customerNumber = customer;
    }
    public Order(String date, Customer customer) {
        this.date = date;
        this.customerNumber = customer;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer getCustomerId() {
        return customerNumber;
    }

    public void setCustomerId(Customer customer) {
        this.customerNumber = customer;
    }
}
