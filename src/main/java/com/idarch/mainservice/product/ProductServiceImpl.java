package com.idarch.mainservice.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.criteria.Predicate;


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
    public List<Product> findAll(Long userId, String query, String category) {
        return productRepository.findAll((root, q, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("userId"), userId));
            if (query != null && !query.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + query.toLowerCase() + "%"));
            }
            if (category != null && !category.isBlank()) {
                predicates.add(cb.like(cb.lower(root.join("category").get("name")), "%" + category.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
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
