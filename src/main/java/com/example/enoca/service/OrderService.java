package com.example.enoca.service;

import com.example.enoca.dto.response.GetOrderResponse;
import com.example.enoca.dto.response.PlaceOrderResponse;

import java.util.List;

public interface OrderService {
    PlaceOrderResponse placeOrder(int customerId);
    GetOrderResponse getOrderForCode(String orderCode);
    List<GetOrderResponse> getAllOrdersForCustomer(int customerId);
}
