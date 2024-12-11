package com.example.enoca.mapper;

import com.example.enoca.dto.response.*;
import com.example.enoca.entity.Cart;
import com.example.enoca.entity.CartItem;
import com.example.enoca.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    // Cart nesnesini GetCartResponse nesnesine dönüştüren metot
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "items", target = "cartItems")
    GetCartResponse cartToGetCartResponse(Cart cart);
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "items", target = "cartItems")
    UpdateCartResponse cartToUpdateCartResponse(Cart cart);
    AddProductToCartResponse toAddProductToCartResponse(Cart cart);
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "productPrice")
    CartItemDto toCartItemDto(CartItem cartItem);
    List<CartItemDto> toCartItemDtoList(List<CartItem> cartItems);

    // CartItem nesnesini GetCartItemResponse nesnesine dönüştüren metot
    default GetCartItemResponse cartItemToGetCartItemResponse(CartItem cartItem) {
        Product product = cartItem.getProduct();
        return new GetCartItemResponse(
                product.getId(), product.getName(), product.getPrice(), cartItem.getQuantity());
    }

    // CartItem listesi için dönüşüm
    default List<GetCartItemResponse> mapCartItems(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::cartItemToGetCartItemResponse)
                .collect(Collectors.toList());
    }
    RemoveProductFromCartResponse toRemoveProductFromCartResponse(Cart cart);
}
