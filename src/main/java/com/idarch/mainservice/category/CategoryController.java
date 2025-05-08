package com.idarch.mainservice.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idarch.mainservice.category.dto.request.CreateCategoryRequest;
import com.idarch.mainservice.category.dto.request.UpdateCategoryRequest;
import com.idarch.mainservice.category.dto.response.CreateCategoryResponse;
import com.idarch.mainservice.category.dto.response.GetCategoryResponse;
import com.idarch.mainservice.category.dto.response.UpdateCategoryResponse;
import com.idarch.mainservice.common.constants.HeaderConstants;
import com.idarch.mainservice.common.dto.response.SuccessResponse;
import com.idarch.mainservice.common.enums.ApplicationStatus;
import com.idarch.mainservice.common.utils.BaseResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<SuccessResponse<CreateCategoryResponse>> create(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @Valid @RequestBody CreateCategoryRequest request
    ) {
        Category createdCategory = categoryService.create(request, userId);
        CreateCategoryResponse response = CreateCategoryResponse.fromEntity(createdCategory);
        return BaseResponse.success(HttpStatus.CREATED, ApplicationStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<GetCategoryResponse>>> findAll(
        @RequestHeader(HeaderConstants.USER_ID) Long userId
    ) {
        List<Category> foundCategories = categoryService.findAll(userId);
        List<GetCategoryResponse> response = foundCategories.stream()
            .map(GetCategoryResponse::fromEntity)
            .toList();
        return BaseResponse.success(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<GetCategoryResponse>> findById(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @PathVariable Long id
    ) {
        Category foundCategory = categoryService.findById(id, userId);
        GetCategoryResponse response = GetCategoryResponse.fromEntity(foundCategory);
        return BaseResponse.success(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<UpdateCategoryResponse>> updateById(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @PathVariable Long id,
        @Valid @RequestBody UpdateCategoryRequest request
    ) {
        Category updatedCategory = categoryService.updateById(id, userId, request);
        UpdateCategoryResponse response = UpdateCategoryResponse.fromEntity(updatedCategory);
        return BaseResponse.success(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Object>> deleteById(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @PathVariable Long id
    ) {
        categoryService.deleteById(id, userId);
        return BaseResponse.success(HttpStatus.NO_CONTENT, ApplicationStatus.NO_CONTENT);
    }
}
