package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ✅ Updated from ManyToMany to OneToMany to support quantity using OrderItem
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    private LocalDateTime orderDate; // ✅ Order time
    private float billAmount;

    @Column(name = "payment_status")
    private String paymentStatus = "Pending";  // ✅ Payment status field

    public Order() {
        this.orderDate = LocalDateTime.now(); // auto-set
    }

    // ✅ Updated constructor to accept orderItems instead of items
    public Order(User user, List<OrderItem> orderItems) {
        this.user = user;
        this.orderItems = orderItems;
        this.orderDate = LocalDateTime.now();
        this.billAmount = calculateBillAmount();
    }

    // ✅ Updated bill calculation to use quantity
    private float calculateBillAmount() {
        if (orderItems == null) return 0.0f;
        return (float) orderItems.stream()
            .mapToDouble(oi -> oi.getItem().getItemPrice() * oi.getQuantity())
            .sum();
    }

    // Getters and Setters

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public float getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(float billAmount) {
        this.billAmount = billAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // ✅ Updated toString to include orderItems
    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", user=" + user + ", orderItems=" + orderItems + ", orderDate=" + orderDate
                + ", billAmount=" + billAmount + ", paymentStatus=" + paymentStatus + "]";
    }
}
