package com.idarch.mainservice.category.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idarch.mainservice.category.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCategoryResponse {
    private Long id;

    private String name;

    public static CreateCategoryResponse fromEntity(Category category) {
        return CreateCategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }
}
