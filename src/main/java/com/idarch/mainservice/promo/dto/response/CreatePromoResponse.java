package com.idarch.mainservice.promo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idarch.mainservice.promo.Promo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePromoResponse {
    private Long id;

    private String name;

    private String description;

    private Long userId;

    public static CreatePromoResponse fromEntity(Promo promo) {
        return CreatePromoResponse.builder()
            .id(promo.getId())
            .name(promo.getName())
            .description(promo.getDescription())
            .userId(promo.getUserId())
            .build();
    }
}
