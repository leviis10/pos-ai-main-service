package com.idarch.mainservice.product;

import java.util.List;

import com.idarch.mainservice.product.dto.request.CreateProductRequest;
import com.idarch.mainservice.product.dto.request.UpdateProductRequest;

public interface ProductService {
    Product create(Long userId, CreateProductRequest request);

    List<Product> findAll(Long userId, String query, String category);

    Product findById(Long userId, Long id);

    Product updateById(Long userId, Long id, UpdateProductRequest request);

    void deleteById(Long userId, Long id);
}
