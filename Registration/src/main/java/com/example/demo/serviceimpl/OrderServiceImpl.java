package com.example.demo.serviceimpl;

import com.example.demo.dto.ItemQuantityDTO;
import com.example.demo.model.Items;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ItemRepository itemRepo;

    @Override
    public Order placeOrder(int userId, List<ItemQuantityDTO> itemsWithQty) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) return null;

        List<OrderItem> orderItems = new ArrayList<>();
        float total = 0;

        for (ItemQuantityDTO dto : itemsWithQty) {
            Items item = itemRepo.findById(dto.getItemId()).orElse(null);
            if (item != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setItem(item);
                orderItem.setQuantity(dto.getQuantity());
                orderItems.add(orderItem);
                total += item.getItemPrice() * dto.getQuantity();
            }
        }

        Order order = new Order(user, orderItems);
        order.setBillAmount(total);

        // Link each OrderItem with the created Order
        for (OrderItem oi : orderItems) {
            oi.setOrder(order);
        }

        return orderRepo.save(order);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        return (user != null) ? orderRepo.findByUser(user) : null;
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepo.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> getOrdersByPaymentStatus(int userId, String paymentStatus) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return orderRepo.findByUserAndPaymentStatus(user, paymentStatus);
        }
        return null;
    }

    @Override
    public Order markPaymentDone(int orderId) {
        Order order = orderRepo.findById(orderId).orElse(null);
        if (order != null) {
            order.setPaymentStatus("Done");
            return orderRepo.save(order);
        }
        return null;
    }
}
