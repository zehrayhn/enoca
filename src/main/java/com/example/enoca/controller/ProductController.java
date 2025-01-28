package com.example.enoca.controller;

import com.example.enoca.dto.request.CreateProductRequest;
import com.example.enoca.dto.request.UpdateProductRequest;
import com.example.enoca.dto.response.CreateProductResponse;
import com.example.enoca.dto.response.GetProductResponse;
import com.example.enoca.dto.response.UpdateProductResponse;
import com.example.enoca.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/{id}")
    public GetProductResponse getProduct(@PathVariable int id){
        return productService.getProduct(id);
    }
    @PostMapping
    public CreateProductResponse createProduct(@Valid @RequestBody CreateProductRequest createProductRequest){
        return productService.createProduct(createProductRequest);
    }
    @PutMapping("/{id}")
    public UpdateProductResponse updateProduct(@PathVariable int id, @Valid @RequestBody UpdateProductRequest updateProductRequest){
        return productService.updateProduct(id,updateProductRequest);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


}
