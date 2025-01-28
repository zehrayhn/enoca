package com.example.enoca.controller;

import com.example.enoca.dto.response.CreateCustomerResponse;
import com.example.enoca.dto.request.CreateCustomerRequest;
import com.example.enoca.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping()
    public CreateCustomerResponse addCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
      return customerService.addCustomer(createCustomerRequest);
    }

}
