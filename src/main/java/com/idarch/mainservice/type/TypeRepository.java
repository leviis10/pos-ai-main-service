package com.idarch.mainservice.type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    List<Type> findAllByUserId(Long userId);

    Optional<Type> findByIdAndUserId(Long id, Long userId);
}
