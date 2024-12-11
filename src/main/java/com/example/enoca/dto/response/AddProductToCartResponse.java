package com.example.enoca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AddProductToCartResponse {
    private int cartId;
    private List<GetCartItemResponse> items;
    private double totalAmount;
}