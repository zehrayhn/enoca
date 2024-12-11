package com.example.enoca.service.impl;

import com.example.enoca.dto.response.CreateCustomerResponse;
import com.example.enoca.dto.request.CreateCustomerRequest;
import com.example.enoca.entity.Cart;
import com.example.enoca.entity.Customer;
import com.example.enoca.exception.BusinessException;
import com.example.enoca.mapper.CustomerMapper;
import com.example.enoca.repository.CustomerRepository;
import com.example.enoca.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public CreateCustomerResponse addCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer= CustomerMapper.INSTANCE.createCustomerMapper(createCustomerRequest);

        if (customer.getCart() == null) {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart);
        }
        Customer createdCustomer=customerRepository.save(customer);

        return new CreateCustomerResponse(
                createdCustomer.getFirstName(),
                createdCustomer.getLastName()
        );
    }
    @Override
    public Customer findCustomerById(int customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Müşteri bulunamadı: " + customerId));
    }
}
