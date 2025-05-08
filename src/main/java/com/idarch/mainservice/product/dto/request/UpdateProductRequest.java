package com.idarch.mainservice.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateProductRequest {
    @NotBlank
    private String name;

    @NotNull
    private Long stock;

    @NotNull
    private Double initialPrice;

    @NotNull
    private Double sellingPrice;

    @NotNull
    private Long categoryId;
    private String productTypeId;

    private String imageUrl;
}
