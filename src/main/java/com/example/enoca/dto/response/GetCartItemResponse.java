package com.example.enoca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCartItemResponse {
    private int productId;
    private String productName;
    private double productPrice;
    private int quantity;
}
