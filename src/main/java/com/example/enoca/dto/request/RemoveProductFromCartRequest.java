package com.example.enoca.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveProductFromCartRequest {

    @Min(value = 1, message = "Ürün miktarı en az 1 olmalıdır.")
    private int quantity;
}
