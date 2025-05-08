package com.idarch.mainservice.invoice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByUserId(Long userId);

    Optional<Invoice> findByIdAndUserId(Long id, Long userId);
}
