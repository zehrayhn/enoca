package com.example.enoca.service;

import com.example.enoca.dto.request.AddProductToCartRequest;
import com.example.enoca.dto.request.RemoveProductFromCartRequest;
import com.example.enoca.dto.request.UpdateCartRequest;
import com.example.enoca.dto.response.AddProductToCartResponse;
import com.example.enoca.dto.response.RemoveProductFromCartResponse;
import com.example.enoca.entity.Cart;
import com.example.enoca.entity.Product;
import com.example.enoca.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<?> getCart(int cartId);
    ResponseEntity<?> updateCart(int cartId, List<UpdateCartRequest> updateCartRequest);
    AddProductToCartResponse addProductToCart(int cartId, AddProductToCartRequest request);
    RemoveProductFromCartResponse removeProductFromCart(int cartId, int productId, RemoveProductFromCartRequest request);
    void emptyCart(int cartId);
    Cart findCartById(int cartId);
}
