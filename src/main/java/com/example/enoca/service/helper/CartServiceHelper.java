package com.example.enoca.service.helper;

import com.example.enoca.dto.request.AddProductToCartRequest;
import com.example.enoca.dto.request.RemoveProductFromCartRequest;
import com.example.enoca.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceHelper {
    private final CartService cartService;

    public void addProductToCartProxy(int cartId, AddProductToCartRequest request) {
        cartService.addProductToCart(cartId, request);
    }

    public void removeProductFromCartProxy(int cartId, int productId, RemoveProductFromCartRequest request) {
        cartService.removeProductFromCart(cartId, productId, request);
    }
}
