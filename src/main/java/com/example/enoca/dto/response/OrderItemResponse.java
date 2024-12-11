package com.example.enoca.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
    private String productName;
    private double productPrice;
    private int quantity;
}
