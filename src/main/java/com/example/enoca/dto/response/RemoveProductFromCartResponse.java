package com.example.enoca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RemoveProductFromCartResponse {
    private int cartId;
    private List<CartItemDto> items;
    private double totalAmount;
}