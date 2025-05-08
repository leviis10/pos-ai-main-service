package com.idarch.mainservice.invoice.invoicedetail.dto.request;

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
public class CreateInvoiceDetailRequest {
    @NotBlank
    private Long productId;

    @NotBlank
    private String productName;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;
}
