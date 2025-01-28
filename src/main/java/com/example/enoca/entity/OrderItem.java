package com.example.enoca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private double productPrice;

    @Column(nullable = false)
    private int quantity;



}
