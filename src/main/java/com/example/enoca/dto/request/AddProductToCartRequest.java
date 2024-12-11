package com.example.enoca.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductToCartRequest {
    private int productId;
    private int quantity;
}
