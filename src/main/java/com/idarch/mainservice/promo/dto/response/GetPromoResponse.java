package com.idarch.mainservice.promo.dto.response;

import java.util.NoSuchElementException;

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
public class GetPromoResponse {
    private Long id;

    private String name;

    private String description;

    public static GetPromoResponse fromEntity(Promo promo) {
        if (promo == null) {
            throw new NoSuchElementException();
        }

        return GetPromoResponse.builder()
            .id(promo.getId())
            .name(promo.getName())
            .description(promo.getDescription())
            .build();
    }
}
