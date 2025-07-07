package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ItemQuantityDTO;
import com.example.demo.model.Order; // ✅ Your app's Order class
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import com.razorpay.RazorpayClient;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Value("${razorpay.key_id}")
    private String razorpayKeyId;

    @Value("${razorpay.key_secret}")
    private String razorpayKeySecret;

    @PostMapping("/place/{userId}")
    public ResponseEntity<Order> placeOrder(
            @PathVariable int userId,
            @RequestBody List<ItemQuantityDTO> itemsWithQty) {
        Order order = orderService.placeOrder(userId, itemsWithQty);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable int userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/bill/{orderId}")
    public ResponseEntity<Float> getBillAmount(@PathVariable int orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return ResponseEntity.ok(order.getBillAmount());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}/status/{paymentStatus}")
    public ResponseEntity<List<Order>> getOrdersByPaymentStatus(
            @PathVariable int userId,
            @PathVariable String paymentStatus) {
        List<Order> orders = orderService.getOrdersByPaymentStatus(userId, paymentStatus);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/payment-done/{orderId}")
    public ResponseEntity<Order> markPaymentAsDone(@PathVariable int orderId) {
        Order updatedOrder = orderService.markPaymentDone(orderId);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Razorpay Order Creation
    @PostMapping("/create-payment-order")
    public ResponseEntity<?> createRazorpayOrder(@RequestBody Map<String, Object> data) {
        try {
            int amount = (int) data.get("amount"); // Amount in rupees
            RazorpayClient client = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

            JSONObject options = new JSONObject();
            options.put("amount", amount * 100); // Convert to paise
            options.put("currency", "INR");
            options.put("receipt", "receipt_" + System.currentTimeMillis());

            com.razorpay.Order razorpayOrder = client.orders.create(options);

            // ✅ Only this line is changed for frontend compatibility
            return ResponseEntity.ok(razorpayOrder.toJson().toMap());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating Razorpay order: " + e.getMessage());
        }
    }

    // ✅ Payment Status Update
    @PostMapping("/update-payment-status/{orderId}")
    		//"/{userId}")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable int orderId) {
    	//@PathVariable int userId
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setPaymentStatus("Done");
            orderRepository.save(order);
            //payment payment =new payment();
            //payment.setOrder(optionalOrder);
           //User user = userService.getUserById(userId);
          //  payment.setUser(user);
        //    paymentrepository.save(payment);
            return ResponseEntity.ok("Payment status updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        }
    }
}
