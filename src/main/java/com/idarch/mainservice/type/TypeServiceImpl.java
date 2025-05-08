package com.idarch.mainservice.type;

import java.util.List;
import java.util.Optional;

import com.idarch.mainservice.type.dto.request.CreateTypeRequest;
import org.springframework.stereotype.Service;
import com.idarch.mainservice.type.dto.request.UpdateTypeRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    @Override
    public Type create(CreateTypeRequest request, Long userId) {
        Type newType = Type.builder()
                .name(request.getName())
                .userId(userId)
                .build();
        return typeRepository.save(newType);
    }

    @Override
    public List<Type> findAll(Long userId) {
        return typeRepository.findAllByUserId(userId);
    }

    @Override
    public Type findById(Long id, Long userId) {
        Optional<Type> foundType = typeRepository.findByIdAndUserId(id, userId);
        if (!foundType.isPresent()) {
            return null;
        }

        return foundType.get();
    }

    @Override
    public Type updateById(Long id, Long userId, UpdateTypeRequest request) {
        Type foundType = findById(id, userId);
        if (foundType == null) {
            return null;
        }

        foundType.setName(request.getName());
        return typeRepository.save(foundType);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        Type foundType = findById(id, userId);
        if (foundType == null) {
            return;
        }

        typeRepository.delete(foundType);
    }
}
