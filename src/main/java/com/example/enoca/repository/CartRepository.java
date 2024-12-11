package com.example.enoca.repository;

import com.example.enoca.entity.Cart;
import com.example.enoca.entity.Customer;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Optional<Cart> findByCustomerId(int customerId);


    Optional<Cart> findByCustomer(Customer customer);
}
