package com.idarch.mainservice.invoice;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.idarch.mainservice.invoice.dto.request.CreateInvoiceRequest;
import com.idarch.mainservice.invoice.invoicedetail.InvoiceDetail;
import com.idarch.mainservice.invoice.invoicedetail.InvoiceDetailService;
import com.idarch.mainservice.invoice.invoicedetail.dto.request.CreateInvoiceDetailRequest;
import com.idarch.mainservice.promo.Promo;
import com.idarch.mainservice.promo.PromoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final PromoService promoService;
    private final InvoiceDetailService invoiceDetailService;

    @Override
    @Transactional
    public Invoice create(Long userId, CreateInvoiceRequest request) {
        Invoice createdInvoice = createInvoice(userId, request);
        if (createdInvoice == null) {
            return null;
        }
        
        List<CreateInvoiceDetailRequest> invoicesDetailRequest = request.getInvoicesDetail().stream()
            .map(invoiceDetail -> CreateInvoiceDetailRequest.builder()
                .productId(invoiceDetail.getProductId())
                .productName(invoiceDetail.getProductName())
                .price(invoiceDetail.getPrice())
                .quantity(invoiceDetail.getQuantity())
                .build()
            )
            .toList();
        List<InvoiceDetail> createdInvoicesDetail = invoiceDetailService.createAll(
            userId,
            invoicesDetailRequest,
            createdInvoice
        );
        createdInvoice.setInvoicesDetail(createdInvoicesDetail);
        Invoice updatedInvoice = invoiceRepository.save(createdInvoice);
        Hibernate.initialize(updatedInvoice.getInvoicesDetail());
        return updatedInvoice;
    }

    private Invoice createInvoice(Long userId, CreateInvoiceRequest request) {
        Promo foundPromo = promoService.findById(request.getPromoId(), userId);
        if (foundPromo == null) {
            return null;
        }

        Invoice newInvoice = Invoice.builder()
            .userId(userId)
            .promo(foundPromo)
            .build();
        return invoiceRepository.save(newInvoice);
    }

    @Override
    @Transactional
    public List<Invoice> findAll(Long userId) {
        List<Invoice> foundInvoices = invoiceRepository.findAllByUserId(userId);
        foundInvoices.stream()
            .forEach(invoice -> Hibernate.initialize(invoice.getInvoicesDetail()));
        return foundInvoices;
    }

    @Override
    @Transactional
    public Invoice findById(Long id, Long userId) {
        Optional<Invoice> foundInvoice = invoiceRepository.findByIdAndUserId(id, userId);
        if (!foundInvoice.isPresent()) {
            return null;
        }
        
        Invoice result = foundInvoice.get();
        Hibernate.initialize(result.getInvoicesDetail());
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id, Long userId) {
        Invoice foundInvoice = findById(id, userId);
        if (foundInvoice == null) {
            return;
        }

        invoiceDetailService.deleteAll(foundInvoice.getInvoicesDetail());
        invoiceRepository.delete(foundInvoice);
    }
}
