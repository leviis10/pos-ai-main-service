package com.idarch.mainservice.promo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreatePromoRequest {
    @NotBlank
    private String name;

    private String description;

    private Long userId;
}
