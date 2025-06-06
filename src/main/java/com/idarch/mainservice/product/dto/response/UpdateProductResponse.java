package com.idarch.mainservice.product.dto.response;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idarch.mainservice.product.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProductResponse {
    private Long id;

    private String name;

    private Long stock;

    private Double initialPrice;

    private Double sellingPrice;

    private String categoryName;
    private String productTypeName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static UpdateProductResponse fromEntity(Product product) {
        if (product == null) {
            throw new NoSuchElementException();
        }

        return UpdateProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .stock(product.getStock())
            .initialPrice(product.getInitialPrice())
            .sellingPrice(product.getSellingPrice())
            .categoryName(product.getCategory().getName())
                .productTypeName(product.getType().getName())
                .createdAt(product.getCreatedAt())
            .updatedAt(product.getUpdatedAt())
            .build();
    }
}
