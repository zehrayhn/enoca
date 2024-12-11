package com.example.enoca.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProductRequest {
    @NotBlank(message = "Product name cannot be empty.")
    private String name;

    @Min(value = 0, message = "Price must be a positive value.")
    private double price;

    @Min(value = 0, message = "Stock quantity must be a positive value.")
    private int stockQuantity;



}
