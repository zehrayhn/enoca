package com.example.enoca.service.impl;

import com.example.enoca.dto.response.GetOrderResponse;
import com.example.enoca.dto.response.PlaceOrderResponse;
import com.example.enoca.entity.*;
import com.example.enoca.exception.BusinessException;
import com.example.enoca.mapper.OrderMapper;
import com.example.enoca.repository.CartRepository;
import com.example.enoca.repository.CustomerRepository;
import com.example.enoca.repository.OrderRepository;
import com.example.enoca.repository.ProductRepository;
import com.example.enoca.service.CartService;
import com.example.enoca.service.CustomerService;
import com.example.enoca.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    @Override
    @Transactional
    public PlaceOrderResponse placeOrder(int customerId) {
        Customer customer = customerService.findCustomerById(customerId);

        Cart cart = customer.getCart();

        if (cart.getItems().isEmpty()) {
            throw new BusinessException(HttpStatus.NOT_FOUND, "Sepet boş, sipariş verilemez.");
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderCode(UUID.randomUUID().toString());
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            if (cartItem.getQuantity() > product.getStockQuantity()) {
                throw new BusinessException(HttpStatus.NOT_FOUND,"Stok yetersiz: " + product.getName());
            }

            // Stok güncelleme
            product.reduceStock(cartItem.getQuantity());
            productRepository.save(product);

            // Sipariş öğesi oluşturma
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductName(product.getName());
            orderItem.setProductPrice(product.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(orderItems.stream()
                .mapToDouble(item -> item.getProductPrice() * item.getQuantity())
                .sum());

        orderRepository.save(order);

        // Sepeti boşaltma
        cart.getItems().clear();
        cartRepository.save(cart);

        return OrderMapper.INSTANCE.toPlaceOrderResponse(order);
    }
    @Override
    public GetOrderResponse getOrderForCode(String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,"Sipariş bulunamadı: " + orderCode));
        return OrderMapper.INSTANCE.toGetOrderResponse(order);
    }
    @Override
    public List<GetOrderResponse> getAllOrdersForCustomer(int customerId) {
        Customer customer = customerService.findCustomerById(customerId);

        List<Order> orders = orderRepository.findAllByCustomer(customer);

        return orders.stream()
                .map(OrderMapper.INSTANCE::toGetOrderResponse)
                .collect(Collectors.toList());
    }
}
