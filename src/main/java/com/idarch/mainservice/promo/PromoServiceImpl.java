package com.idarch.mainservice.promo;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.idarch.mainservice.promo.dto.request.CreatePromoRequest;
import com.idarch.mainservice.promo.dto.request.UpdatePromoRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PromoServiceImpl implements PromoService {
    private final PromoRepository promoRepository;

    @Override
    public Promo create(Long userId, CreatePromoRequest request) {
        Promo newPromo = Promo.builder()
            .name(request.getName())
            .description(request.getDescription())
            .userId(userId)
            .build();
        return promoRepository.save(newPromo);
    }

    @Override
    public List<Promo> findAll(Long userId) {
        return promoRepository.findAllByUserId(userId);
    }

    @Override
    public Promo findById(Long id, Long userId) {
        try {
            Promo foundPromo = promoRepository.findByIdAndUserId(id, userId)
                .orElseThrow();
            return foundPromo;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public Promo updateById(Long id, Long userId, UpdatePromoRequest request) {
        Promo foundPromo = findById(id, userId);
        if (foundPromo == null) {
            return null;
        }
        foundPromo.setName(request.getName());
        foundPromo.setDescription(request.getDescription());
        return promoRepository.save(foundPromo);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        Promo foundPromo = findById(id, userId);
        if (foundPromo == null) {
            return;
        }
        promoRepository.delete(foundPromo);
    }
}
