package com.example.enoca.mapper;

import com.example.enoca.dto.request.CreateProductRequest;
import com.example.enoca.dto.request.UpdateProductRequest;
import com.example.enoca.dto.response.CreateProductResponse;
import com.example.enoca.dto.response.GetProductResponse;
import com.example.enoca.dto.response.UpdateProductResponse;
import com.example.enoca.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    GetProductResponse getProductResponseMapper(Product product);

    Product createProductMapper(CreateProductRequest createProductRequest);

    CreateProductResponse createProductResponseMapper(Product product);

    void updateProductMapper(UpdateProductRequest updateProductRequest,@MappingTarget Product existingProduct);

    UpdateProductResponse updateProductResponseMapper(Product product);
}
