package com.idarch.mainservice.promo;

import java.util.List;

import com.idarch.mainservice.promo.dto.request.CreatePromoRequest;
import com.idarch.mainservice.promo.dto.request.UpdatePromoRequest;

public interface PromoService {
    Promo create(Long userId, CreatePromoRequest request);

    List<Promo> findAll(Long userId);

    Promo findById(Long id, Long userId);

    Promo updateById(Long id, Long userId, UpdatePromoRequest request);

    void deleteById(Long id, Long userId);
}
