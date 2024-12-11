package com.example.enoca.service;

import com.example.enoca.dto.response.CreateCustomerResponse;
import com.example.enoca.dto.request.CreateCustomerRequest;
import com.example.enoca.entity.Customer;


public interface CustomerService {
    CreateCustomerResponse addCustomer(CreateCustomerRequest createCustomerRequest);
    Customer findCustomerById(int cartId);

}
