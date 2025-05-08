package com.idarch.mainservice.invoice.invoicedetail;

import java.util.List;

import com.idarch.mainservice.invoice.Invoice;
import com.idarch.mainservice.invoice.invoicedetail.dto.request.CreateInvoiceDetailRequest;

public interface InvoiceDetailService {
    List<InvoiceDetail> createAll(Long userId, List<CreateInvoiceDetailRequest> request, Invoice invoice);

    void deleteAll(List<InvoiceDetail> invoiceDetails);
}
