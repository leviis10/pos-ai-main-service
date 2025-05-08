package com.idarch.mainservice.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.idarch.mainservice.category.dto.request.CreateCategoryRequest;
import com.idarch.mainservice.category.dto.request.UpdateCategoryRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category create(CreateCategoryRequest request, Long userId) {
        Category newCategory = Category.builder()
            .name(request.getName())
            .userId(userId)
            .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public List<Category> findAll(Long userId) {
        return categoryRepository.findAllByUserId(userId);
    }

    @Override
    public Category findById(Long id, Long userId) {
        Optional<Category> foundCategory = categoryRepository.findByIdAndUserId(id, userId);
        if (!foundCategory.isPresent()) {
            return null;
        }

        return foundCategory.get();
    }

    @Override
    public Category updateById(Long id, Long userId, UpdateCategoryRequest request) {
        Category foundCategory = findById(id, userId);
        if (foundCategory == null) {
            return null;
        }

        foundCategory.setName(request.getName());
        return categoryRepository.save(foundCategory);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        Category foundCategory = findById(id, userId);
        if (foundCategory == null) {
            return;
        }

        categoryRepository.delete(foundCategory);
    }
}
