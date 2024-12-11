package com.example.enoca.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PlaceOrderResponse {
    private String orderCode;
    private LocalDateTime orderDate;
    private double totalAmount;
    private List<OrderItemResponse> orderItems;
}
