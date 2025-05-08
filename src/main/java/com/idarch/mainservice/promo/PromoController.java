package com.idarch.mainservice.promo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idarch.mainservice.common.constants.HeaderConstants;
import com.idarch.mainservice.common.dto.response.SuccessResponse;
import com.idarch.mainservice.common.enums.ApplicationStatus;
import com.idarch.mainservice.common.utils.BaseResponse;
import com.idarch.mainservice.promo.dto.request.CreatePromoRequest;
import com.idarch.mainservice.promo.dto.request.UpdatePromoRequest;
import com.idarch.mainservice.promo.dto.response.CreatePromoResponse;
import com.idarch.mainservice.promo.dto.response.GetPromoResponse;
import com.idarch.mainservice.promo.dto.response.UpdatePromoResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/promos")
public class PromoController {
    private final PromoService promoService;
    
    @PostMapping
    public ResponseEntity<SuccessResponse<CreatePromoResponse>> create(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @RequestBody CreatePromoRequest request
    ) {
        Promo createdPromo = promoService.create(userId, request);
        CreatePromoResponse response = CreatePromoResponse.fromEntity(createdPromo);
        return BaseResponse.success(HttpStatus.CREATED, ApplicationStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<GetPromoResponse>>> findAll(
        @RequestHeader(HeaderConstants.USER_ID) Long userId
    ) {
        List<Promo> foundPromos = promoService.findAll(userId);
        List<GetPromoResponse> response = foundPromos.stream()
            .map(GetPromoResponse::fromEntity)
            .toList();
        return BaseResponse.success(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<GetPromoResponse>> findById(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @PathVariable Long id
    ) {
        Promo foundPromo = promoService.findById(id, userId);
        GetPromoResponse response = GetPromoResponse.fromEntity(foundPromo);
        return BaseResponse.success(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<UpdatePromoResponse>> updateById(
        @PathVariable Long id,
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @RequestBody UpdatePromoRequest request
    ) {
        Promo updatedPromo = promoService.updateById(id, userId, request);
        UpdatePromoResponse response = UpdatePromoResponse.fromEntity(updatedPromo);
        return BaseResponse.success(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Object>> deleteById(
        @RequestHeader(HeaderConstants.USER_ID) Long userId,
        @PathVariable Long id
    ) {
        promoService.deleteById(id, userId);
        return BaseResponse.success(HttpStatus.NO_CONTENT, ApplicationStatus.NO_CONTENT);
    }
}
