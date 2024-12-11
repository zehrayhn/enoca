package com.example.enoca.service;

import com.example.enoca.dto.request.CreateProductRequest;
import com.example.enoca.dto.request.UpdateProductRequest;
import com.example.enoca.dto.response.CreateProductResponse;
import com.example.enoca.dto.response.GetProductResponse;
import com.example.enoca.dto.response.UpdateProductResponse;

public interface ProductService {
    GetProductResponse getProduct(int productId);
    CreateProductResponse createProduct(CreateProductRequest createProductRequest);
    UpdateProductResponse updateProduct(int id, UpdateProductRequest updateProductRequest);
    void deleteProduct(int id);
}
