package com.idarch.mainservice.invoice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idarch.mainservice.common.constants.HeaderConstants;
import com.idarch.mainservice.common.dto.response.SuccessResponse;
import com.idarch.mainservice.common.enums.ApplicationStatus;
import com.idarch.mainservice.common.utils.BaseResponse;
import com.idarch.mainservice.invoice.dto.request.CreateInvoiceRequest;
import com.idarch.mainservice.invoice.dto.response.CreateInvoiceResponse;
import com.idarch.mainservice.invoice.dto.response.GetInvoiceResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<SuccessResponse<CreateInvoiceResponse>> create(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @RequestBody CreateInvoiceRequest request
    ) {
        Invoice createdInvoice = invoiceService.create(userId, request);
        CreateInvoiceResponse response = CreateInvoiceResponse.fromEntity(createdInvoice);
        return BaseResponse.success(HttpStatus.CREATED, ApplicationStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<GetInvoiceResponse>>> findAll(
        @RequestHeader(HeaderConstants.USER_ID) Long userId
    ) {
        List<Invoice> foundInvoices = invoiceService.findAll(userId);
        List<GetInvoiceResponse> response = foundInvoices.stream()
            .map(GetInvoiceResponse::fromEntity)
            .toList();
        return BaseResponse.success(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<GetInvoiceResponse>> findById(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @PathVariable Long id
    ) {
        Invoice foundInvoice = invoiceService.findById(id, userId);
        GetInvoiceResponse response = GetInvoiceResponse.fromEntity(foundInvoice);
        return BaseResponse.success(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Object>> deleteById(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @PathVariable Long id
    ) {
        invoiceService.deleteById(id, userId);
        return BaseResponse.success(HttpStatus.NO_CONTENT, ApplicationStatus.NOT_FOUND);
    }
}
