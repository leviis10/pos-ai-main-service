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
public class GetCategoryResponse {
    private Long id;

    private String name;

    public static GetCategoryResponse fromEntity(Category category) {
        if (category == null) {
            throw new NoSuchElementException();
        }

        return GetCategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }
}
