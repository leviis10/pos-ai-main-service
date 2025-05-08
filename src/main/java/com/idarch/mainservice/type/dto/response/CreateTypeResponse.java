package com.idarch.mainservice.type.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idarch.mainservice.category.Category;
import com.idarch.mainservice.type.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTypeResponse {
    private Long id;

    private String name;

    public static CreateTypeResponse fromEntity(Type type) {
        return CreateTypeResponse.builder()
            .id(type.getId())
            .name(type.getName())
            .build();
    }
}
