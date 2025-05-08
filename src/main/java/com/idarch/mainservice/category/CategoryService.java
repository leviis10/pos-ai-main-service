package com.idarch.mainservice.category;

import java.util.List;

import com.idarch.mainservice.category.dto.request.CreateCategoryRequest;
import com.idarch.mainservice.category.dto.request.UpdateCategoryRequest;

public interface CategoryService {
    Category create(CreateCategoryRequest request, Long userId);

    List<Category> findAll(Long userId);

    Category findById(Long id, Long userId);

    Category updateById(Long id, Long userId, UpdateCategoryRequest request);

    void deleteById(Long id, Long userId);
}
