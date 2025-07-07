package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import com.example.demo.model.User;

public interface PaymentRepository extends JpaRepository<Order, Integer> {
	List<Order> findByUserAndPaymentStatus(User user, String paymentStatus);
    List<Order> findByUser(User user);
	void save(Payment payment);
   
}
