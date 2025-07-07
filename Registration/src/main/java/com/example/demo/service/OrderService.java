package com.example.demo.service;

import com.example.demo.model.Order;

import java.util.List;
import com.example.demo.dto.ItemQuantityDTO;

public interface OrderService {
	Order placeOrder(int userId, List<ItemQuantityDTO> itemsWithQty);

    List<Order> getOrdersByUserId(int userId);

    //  NEW METHOD
    Order getOrderById(int orderId);
 // ✅ PAYMENT STATUS METHODS
    List<Order> getOrdersByPaymentStatus(int userId, String paymentStatus);
    Order markPaymentDone(int orderId);
    
    
}
