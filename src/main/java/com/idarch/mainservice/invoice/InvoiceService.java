package com.idarch.mainservice.invoice;

import java.util.List;

import com.idarch.mainservice.invoice.dto.request.CreateInvoiceRequest;

public interface InvoiceService {
    Invoice create(Long userId, CreateInvoiceRequest request);

    List<Invoice> findAll(Long userId);

    Invoice findById(Long id, Long userId);

    void deleteById(Long id, Long userId);
}
