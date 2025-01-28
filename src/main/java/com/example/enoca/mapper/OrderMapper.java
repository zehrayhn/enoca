package com.example.enoca.mapper;

import com.example.enoca.dto.response.GetOrderResponse;
import com.example.enoca.dto.response.OrderItemResponse;
import com.example.enoca.dto.response.PlaceOrderResponse;
import com.example.enoca.entity.Order;
import com.example.enoca.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    PlaceOrderResponse toPlaceOrderResponse(Order order);

    @Mapping(target = "productName", source = "productName")
    @Mapping(target = "productPrice", source = "productPrice")
    @Mapping(target = "quantity", source = "quantity")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    GetOrderResponse toGetOrderResponse(Order order);
}
