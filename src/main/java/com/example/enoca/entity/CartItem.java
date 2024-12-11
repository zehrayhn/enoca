package com.example.enoca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem extends BaseEntity{

    private int quantity;

    @JsonIgnore
    @ManyToOne
    private Cart cart;
    @JsonIgnore
    @ManyToOne
    private Product product;
}
