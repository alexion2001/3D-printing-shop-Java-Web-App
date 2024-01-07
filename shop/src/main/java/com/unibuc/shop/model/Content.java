package com.unibuc.shop.model;

import javax.persistence.*;

@Entity
@Table(name="content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contentId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;


    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderId;

    public Content() {

    }

    public Content(long contentId, Product productId, Order orderId) {
        this.contentId = contentId;
        this.productId = productId;
        this.orderId = orderId;
    }

    public Content( Product productId, Order orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }
}
