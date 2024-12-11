package com.example.enoca.service.impl;

import com.example.enoca.dto.request.CreateProductRequest;
import com.example.enoca.dto.request.UpdateProductRequest;
import com.example.enoca.dto.response.CreateProductResponse;
import com.example.enoca.dto.response.GetProductResponse;
import com.example.enoca.dto.response.UpdateProductResponse;
import com.example.enoca.entity.Customer;
import com.example.enoca.entity.Product;
import com.example.enoca.exception.BusinessException;
import com.example.enoca.mapper.ProductMapper;
import com.example.enoca.repository.ProductRepository;
import com.example.enoca.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public GetProductResponse getProduct(int productId) {
        Product product = findProductById(productId);

        return ProductMapper.INSTANCE.getProductResponseMapper(product);
    }
    @Override
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {
        Product product=ProductMapper.INSTANCE.createProductMapper(createProductRequest);
        Product createdProduct=productRepository.save(product);
        return ProductMapper.INSTANCE.createProductResponseMapper(createdProduct);
    }

    @Override
    public UpdateProductResponse updateProduct(int id, UpdateProductRequest updateProductRequest) {
        Product existingProduct = findProductById(id);

        ProductMapper.INSTANCE.updateProductMapper(updateProductRequest,existingProduct);

        Product updatedProduct = productRepository.save(existingProduct);

        return ProductMapper.INSTANCE.updateProductResponseMapper(updatedProduct);

    }
    @Override
    public void deleteProduct(int id) {
        Product existingProduct = findProductById(id);
        productRepository.delete(existingProduct);
    }

    @Override
    public Product findProductById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "Product not found with ID: " + productId));
    }


}
