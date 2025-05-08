package com.idarch.mainservice.invoice.invoicedetail;

import java.util.List;

import org.springframework.stereotype.Service;

import com.idarch.mainservice.invoice.Invoice;
import com.idarch.mainservice.invoice.invoicedetail.dto.request.CreateInvoiceDetailRequest;
import com.idarch.mainservice.product.Product;
import com.idarch.mainservice.product.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
    private final InvoiceDetailRepository invoiceDetailRepository;
    private final ProductService productService;

    @Override
    public List<InvoiceDetail> createAll(Long userId, List<CreateInvoiceDetailRequest> request, Invoice invoice) {
        List<InvoiceDetail> invoicesDetail = request.stream()
            .map(invoiceDetail -> InvoiceDetail.builder()
                .invoice(invoice)
                .product(getProduct(userId, invoiceDetail.getProductId()))
                .productName(invoiceDetail.getProductName())
                .price(invoiceDetail.getPrice())
                .quantity(invoiceDetail.getQuantity())
                .build()
            )
            .toList();
        return invoiceDetailRepository.saveAll(invoicesDetail);
    }

    private Product getProduct(Long userId, Long productId) {
        return productService.findById(userId, productId);
    }

    @Override
    public void deleteAll(List<InvoiceDetail> invoiceDetails) {
        invoiceDetailRepository.deleteAll(invoiceDetails);
    }
}
