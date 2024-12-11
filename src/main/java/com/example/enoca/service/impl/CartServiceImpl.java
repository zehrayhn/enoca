package com.example.enoca.service.impl;

import com.example.enoca.dto.request.AddProductToCartRequest;
import com.example.enoca.dto.request.RemoveProductFromCartRequest;
import com.example.enoca.dto.request.UpdateCartRequest;
import com.example.enoca.dto.response.AddProductToCartResponse;
import com.example.enoca.dto.response.GetCartResponse;
import com.example.enoca.dto.response.RemoveProductFromCartResponse;
import com.example.enoca.entity.Cart;
import com.example.enoca.entity.CartItem;
import com.example.enoca.entity.Product;
import com.example.enoca.exception.BusinessException;
import com.example.enoca.mapper.CartMapper;
import com.example.enoca.repository.CartRepository;
import com.example.enoca.repository.ProductRepository;
import com.example.enoca.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    @Override
    public ResponseEntity<?> getCart(int cartId) {
        Cart cart=findCartById(cartId);
        return cart.getItems().isEmpty()
                ? ResponseEntity.ok("Sepet boş.")
                : ResponseEntity.ok(CartMapper.INSTANCE.cartToGetCartResponse(cart));
    }
    @Override
    @Transactional
    public ResponseEntity<?> updateCart(int cartId, List<UpdateCartRequest> updateCartRequest) {
        Cart cart = findCartById(cartId);

        for (UpdateCartRequest update : updateCartRequest) {
            switch (update.getAction()) {
                case "ADD": // Ürünün miktarını artır
                    AddProductToCartRequest request = new AddProductToCartRequest();
                    request.setProductId(update.getProductId());
                    request.setQuantity(update.getQuantity());
                    addProductToCart(cart.getId(), request);
                    break;
                case "REMOVE": // Ürünü sepetten çıkar
                    RemoveProductFromCartRequest removeProductFromCartRequest=new RemoveProductFromCartRequest();
                    removeProductFromCartRequest.setQuantity(update.getQuantity());
                    removeProductFromCart(cart.getId(), update.getProductId(),removeProductFromCartRequest);
                    break;

                default:
                    throw new BusinessException(HttpStatus.BAD_REQUEST,"Invalid update action: " + update.getAction());
            }
        }
        return null;
    }
    @Override
    @Transactional
    public AddProductToCartResponse addProductToCart(int cartId, AddProductToCartRequest request) {
        Cart cart = findCartById(cartId);
        Product product = findProductById(request.getProductId());

        int currentQuantityInCart = cart.getItems().stream()
                .filter(item -> Objects.equals(item.getProduct().getId(), product.getId()))
                .mapToInt(CartItem::getQuantity)
                .sum();

        // Toplam miktar kontrolü
        int totalQuantity = currentQuantityInCart + request.getQuantity();

        // Ürün stok kontrolü
        if (product.getStockQuantity() < totalQuantity) {
            throw new BusinessException(HttpStatus.NOT_FOUND,"Stok yetersiz. Mevcut stok: " + product.getStockQuantity());
        }

        // Sepette ürün var mı kontrol et
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> Objects.equals(item.getProduct().getId(), product.getId()))
                .findFirst()
                .orElse(null);

        if (cartItem == null) {
            // Sepette ürün yoksa ekle
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(request.getQuantity());
            cart.getItems().add(cartItem);
        } else {
            // Sepette ürün varsa miktarı artır
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        }

        // Sepet toplamını güncelle
        double totalAmount = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalAmount(totalAmount);

        // Kaydet ve cevap dön
        cartRepository.save(cart);
        return CartMapper.INSTANCE.toAddProductToCartResponse(cart);
    }

    @Override
    @Transactional
    public RemoveProductFromCartResponse removeProductFromCart(int cartId, int productId, RemoveProductFromCartRequest request) {
        // Sepeti getir
        Cart cart = findCartById(cartId);

        // Ürünü kontrol et
        Product product = findProductById(productId);

        // Sepette ürün var mı kontrol et
        CartItem cartItem = cart.getItems().stream()
                .filter(item -> Objects.equals(item.getProduct().getId(), product.getId()))
                .findFirst()
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,"Sepette bu ürün bulunmamaktadır."));

        if (request.getQuantity() >= cartItem.getQuantity()) {
            // Ürün miktarı sıfır veya belirtilen miktar mevcut miktara eşit/üstünse tamamen kaldır
            cart.getItems().remove(cartItem);
        } else {
            // Miktarı azalt
            cartItem.setQuantity(cartItem.getQuantity() - request.getQuantity());
        }

        // Sepet toplamını güncelle
        double totalAmount = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalAmount(totalAmount);

        // Kaydet ve cevap dön
        cartRepository.save(cart);
        return CartMapper.INSTANCE.toRemoveProductFromCartResponse(cart);
    }

    @Override
    @Transactional
    public void emptyCart(int cartId) {
        Cart cart = findCartById(cartId);
        // Stokları geri yükle
        for (CartItem item : cart.getItems()) {
            item.getProduct().increaseStock(item.getQuantity());
        }

        cart.getItems().clear();
        cart.setTotalAmount(0);
        cartRepository.save(cart);
    }



    public Product findProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Ürün bulunamadı: " + productId));
    }

    public Cart findCartById(int cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Sepet bulunamadı: " + cartId));
    }

}