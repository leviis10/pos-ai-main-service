package com.idarch.mainservice.product;

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

import com.idarch.mainservice.common.constants.HeaderConstants;
import com.idarch.mainservice.common.dto.response.SuccessResponse;
import com.idarch.mainservice.common.enums.ApplicationStatus;
import com.idarch.mainservice.common.utils.BaseResponse;
import com.idarch.mainservice.product.dto.request.CreateProductRequest;
import com.idarch.mainservice.product.dto.request.UpdateProductRequest;
import com.idarch.mainservice.product.dto.response.CreateProductResponse;
import com.idarch.mainservice.product.dto.response.GetProductResponse;
import com.idarch.mainservice.product.dto.response.UpdateProductResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<SuccessResponse<CreateProductResponse>> create(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @RequestBody CreateProductRequest request
    ) {
        Product createdProduct = productService.create(userId, request);
        CreateProductResponse response = CreateProductResponse.fromEntity(createdProduct);
        return BaseResponse.success(HttpStatus.CREATED, ApplicationStatus.OK, response);
    };

    @GetMapping
    public ResponseEntity<SuccessResponse<List<GetProductResponse>>> findAll(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @RequestParam(required = false) String query,
        @RequestParam(required = false) String category
    ) {
        List<Product> foundProducts = productService.findAll(userId, query, category);
        List<GetProductResponse> response = foundProducts.stream()
            .map(GetProductResponse::fromEntity)
            .toList();
        return BaseResponse.success(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<GetProductResponse>> findById(
        @PathVariable Long id,
        @RequestHeader(HeaderConstants.USER_ID) Long userId
    ) {
        Product foundProduct = productService.findById(userId, id);
        GetProductResponse response = GetProductResponse.fromEntity(foundProduct);
        return BaseResponse.success(response);
    };

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<UpdateProductResponse>> updateById(
        @PathVariable Long id,
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @RequestBody UpdateProductRequest request
    ) {
        Product updatedProduct = productService.updateById(userId, id, request);
        UpdateProductResponse response = UpdateProductResponse.fromEntity(updatedProduct);
        return BaseResponse.success(response);
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Object>> deleteById(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @PathVariable Long id
    ) {
        productService.deleteById(userId, id);
        return BaseResponse.success(HttpStatus.NO_CONTENT, ApplicationStatus.NO_CONTENT);
    };
}
