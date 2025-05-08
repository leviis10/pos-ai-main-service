package com.idarch.mainservice.type.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idarch.mainservice.type.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateTypeResponse {
    private Long id;

    private String name;

    public static UpdateTypeResponse fromEntity(Type type) {
        if (type == null) {
            throw new NoSuchElementException();
        }

        return UpdateTypeResponse.builder()
            .id(type.getId())
            .name(type.getName())
            .build();
    }
}
