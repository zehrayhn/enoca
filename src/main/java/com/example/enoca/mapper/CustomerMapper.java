package com.example.enoca.mapper;

import com.example.enoca.dto.request.CreateCustomerRequest;
import com.example.enoca.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer createCustomerMapper(CreateCustomerRequest customer);

}
