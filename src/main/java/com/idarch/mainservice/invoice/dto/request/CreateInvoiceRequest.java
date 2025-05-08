package com.idarch.mainservice.invoice.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateInvoiceRequest {
    @NotNull
    private Long promoId;

    private List<InvoiceDetail> invoicesDetail;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static class InvoiceDetail {
        private Long productId;

        private String productName;

        private Double price;

        private Integer quantity;
    }
}
