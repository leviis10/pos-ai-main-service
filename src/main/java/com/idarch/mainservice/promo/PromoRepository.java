package com.idarch.mainservice.promo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long> {
    List<Promo> findAllByUserId(Long userId);

    Optional<Promo> findByIdAndUserId(Long id, Long userId);
}
