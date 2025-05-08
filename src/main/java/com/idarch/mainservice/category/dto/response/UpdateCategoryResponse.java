package com.idarch.mainservice.category.dto.response;

import java.util.NoSuchElementException;

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
public class UpdateCategoryResponse {
    private Long id;

    private String name;

    public static UpdateCategoryResponse fromEntity(Category category) {
        if (category == null) {
            throw new NoSuchElementException();
        }

        return UpdateCategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }
}
