package com.example.enoca.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateCartRequest {
    private String action;
    private int productId;
    private int quantity;
}
