package com.example.enoca.service.helper;

import com.example.enoca.dto.request.AddProductToCartRequest;
import com.example.enoca.dto.request.RemoveProductFromCartRequest;
import com.example.enoca.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CartServiceHelper {
    private CartService cartService;

    @Autowired
    public void setCartService(@Lazy CartService cartService) {
        this.cartService = cartService;
    }

    public void addProductToCartProxy(int cartId, AddProductToCartRequest request) {
        cartService.addProductToCart(cartId, request);
    }

    public void removeProductFromCartProxy(int cartId, int productId, RemoveProductFromCartRequest request) {
        cartService.removeProductFromCart(cartId, productId, request);
    }
}
