package com.example.enoca.controller;

import com.example.enoca.dto.response.GetOrderResponse;
import com.example.enoca.dto.response.PlaceOrderResponse;
import com.example.enoca.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<PlaceOrderResponse> placeOrder(
            @PathVariable int customerId){
        PlaceOrderResponse response = orderService.placeOrder(customerId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{orderCode}")
    public ResponseEntity<GetOrderResponse> getOrderForCode(@PathVariable String orderCode) {
        GetOrderResponse response = orderService.getOrderForCode(orderCode);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<GetOrderResponse>> getAllOrdersForCustomer(@PathVariable int customerId) {
        List<GetOrderResponse> response = orderService.getAllOrdersForCustomer(customerId);
        return ResponseEntity.ok(response);
    }

}
