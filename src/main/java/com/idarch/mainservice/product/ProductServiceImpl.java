package com.idarch.mainservice.product;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.idarch.mainservice.category.Category;
import com.idarch.mainservice.category.CategoryService;
import com.idarch.mainservice.product.dto.request.CreateProductRequest;
import com.idarch.mainservice.product.dto.request.UpdateProductRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public Product create(Long userId, CreateProductRequest request) {
        Category foundCategory = categoryService.findById(request.getCategoryId(), userId);
        if (foundCategory == null) {
            return null;
        }

        Product newProduct = Product.builder()
            .name(request.getName())
            .stock(request.getStock())
            .initialPrice(request.getInitialPrice())
            .sellingPrice(request.getSellingPrice())
            .category(foundCategory)
            .imageUrl(request.getImageUrl())
            .userId(userId)
            .build();
        return productRepository.save(newProduct);
    }

    @Override
    public List<Product> findAll(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    @Override
    public Product findById(Long userId, Long id) {
        Optional<Product> foundProduct = productRepository.findByIdAndUserId(id, userId);
        if (!foundProduct.isPresent()) {
            return null;
        }

        return foundProduct.get();
    }

    @Override
    public Product updateById(Long userId, Long id, UpdateProductRequest request) {
        Product foundProduct = findById(userId, id);
        if (foundProduct == null) {
            return null;
        }

        foundProduct.setName(request.getName());
        foundProduct.setStock(request.getStock());
        foundProduct.setInitialPrice(request.getInitialPrice());
        foundProduct.setSellingPrice(request.getSellingPrice());
        if (!foundProduct.getCategory().getId().equals(request.getCategoryId())) {
            Category foundCategory = categoryService.findById(request.getCategoryId(), userId);
            if (foundCategory == null) {
                return null;
            }
            foundProduct.setCategory(foundCategory);
        }
        foundProduct.setImageUrl(request.getImageUrl());
        return productRepository.save(foundProduct);
    }

    @Override
    public void deleteById(Long userId, Long id) {
        Product foundProduct = findById(userId, id);
        if (foundProduct == null) {
            return;
        }

        productRepository.delete(foundProduct);
    }
}
