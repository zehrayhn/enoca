package com.example.enoca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartResponse {
    private int customerId;
    private double totalAmount;
    private List<GetCartItemResponse> cartItems;
}
