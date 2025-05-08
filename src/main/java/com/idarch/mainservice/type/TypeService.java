package com.idarch.mainservice.type;

import java.util.List;

import com.idarch.mainservice.type.dto.request.CreateTypeRequest;
import com.idarch.mainservice.type.dto.request.UpdateTypeRequest;

public interface TypeService {
    Type create(CreateTypeRequest request, Long userId);

    List<Type> findAll(Long userId);

    Type findById(Long id, Long userId);

    Type updateById(Long id, Long userId, UpdateTypeRequest request);

    void deleteById(Long id, Long userId);
}
