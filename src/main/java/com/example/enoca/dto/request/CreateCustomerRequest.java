package com.example.enoca.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCustomerRequest {
    @NotBlank(message = "Username cannot be empty")
    private String firstName;
    private String lastName;
}
