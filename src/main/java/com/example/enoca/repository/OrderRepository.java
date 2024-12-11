package com.example.enoca.repository;

import com.example.enoca.entity.Customer;
import com.example.enoca.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository <Order, Integer>{
    Optional<Order> findByOrderCode(String orderCode);

    List<Order> findAllByCustomer(Customer customer);
}
