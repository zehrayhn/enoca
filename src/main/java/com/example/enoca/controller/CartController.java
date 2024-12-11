package com.example.enoca.controller;

import com.example.enoca.dto.request.AddProductToCartRequest;
import com.example.enoca.dto.request.RemoveProductFromCartRequest;
import com.example.enoca.dto.request.UpdateCartRequest;
import com.example.enoca.dto.response.AddProductToCartResponse;
import com.example.enoca.dto.response.RemoveProductFromCartResponse;
import com.example.enoca.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable int cartId) {
        return cartService.getCart(cartId);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<?> updateCart(@PathVariable int cartId,
                                                         @RequestBody List<UpdateCartRequest> updateCartRequest) {
        return ResponseEntity.ok(cartService.updateCart( cartId, updateCartRequest));
    }
    @PostMapping("/{cartId}/products")
    public ResponseEntity<AddProductToCartResponse> addProductToCart(
            @PathVariable int cartId,
            @RequestBody @Valid AddProductToCartRequest request) {
        AddProductToCartResponse response = cartService.addProductToCart(cartId,request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{cartId}/products")
    public ResponseEntity<RemoveProductFromCartResponse> removeProductFromCart(
            @PathVariable int cartId,
            @RequestParam int productId,
            @RequestBody @Valid RemoveProductFromCartRequest request) {
        RemoveProductFromCartResponse response = cartService.removeProductFromCart(cartId,productId,request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cartId}/empty")
    public ResponseEntity<Void> emptyCart(@PathVariable int cartId) {
        cartService.emptyCart(cartId);
        return ResponseEntity.noContent().build();
    }
}
