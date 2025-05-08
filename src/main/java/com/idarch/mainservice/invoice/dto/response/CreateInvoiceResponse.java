package com.idarch.mainservice.invoice.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idarch.mainservice.invoice.Invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateInvoiceResponse {
    private Long id;

    private LocalDateTime createdAt;

    private Long userId;

    private Long promoId;

    private List<InvoiceDetail> invoiceDetails;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class InvoiceDetail {
        private String productName;

        private Double price;

        private Integer quantity;

        public static InvoiceDetail fromEntity(
            com.idarch.mainservice.invoice.invoicedetail.InvoiceDetail invoiceDetail
        ) {
            return InvoiceDetail.builder()
                .productName(invoiceDetail.getProductName())
                .price(invoiceDetail.getPrice())
                .quantity(invoiceDetail.getQuantity())
                .build();
        }
    }

    public static CreateInvoiceResponse fromEntity(Invoice invoice) {
        if (invoice == null) {
            throw new NoSuchElementException();
        }

        List<InvoiceDetail> invoiceDetails = invoice.getInvoicesDetail().stream()
            .map(InvoiceDetail::fromEntity)
            .toList();

        return CreateInvoiceResponse.builder()
            .id(invoice.getId())
            .createdAt(invoice.getCreatedAt())
            .userId(invoice.getUserId())
            .promoId(invoice.getPromo().getId())
            .invoiceDetails(invoiceDetails)
            .build();
    }
}
