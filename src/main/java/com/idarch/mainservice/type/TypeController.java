package com.idarch.mainservice.type;

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

import com.idarch.mainservice.type.dto.request.CreateTypeRequest;
import com.idarch.mainservice.type.dto.request.UpdateTypeRequest;
import com.idarch.mainservice.type.dto.response.CreateTypeResponse;
import com.idarch.mainservice.type.dto.response.GetTypeResponse;
import com.idarch.mainservice.type.dto.response.UpdateTypeResponse;
import com.idarch.mainservice.common.constants.HeaderConstants;
import com.idarch.mainservice.common.dto.response.SuccessResponse;
import com.idarch.mainservice.common.enums.ApplicationStatus;
import com.idarch.mainservice.common.utils.BaseResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/types")
public class TypeController {
    private final TypeService typeService;

    @PostMapping
    public ResponseEntity<SuccessResponse<CreateTypeResponse>> create(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @Valid @RequestBody CreateTypeRequest request
    ) {
        Type createdType = typeService.create(request, userId);
        CreateTypeResponse response = CreateTypeResponse.fromEntity(createdType);
        return BaseResponse.success(HttpStatus.CREATED, ApplicationStatus.OK, response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<GetTypeResponse>>> findAll(
            @RequestHeader(HeaderConstants.USER_ID) Long userId
    ) {
        List<Type> foundTypes = typeService.findAll(userId);
        List<GetTypeResponse> response = foundTypes.stream()
                .map(GetTypeResponse::fromEntity)
                .toList();
        return BaseResponse.success(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<GetTypeResponse>> findById(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @PathVariable Long id
    ) {
        Type foundType = typeService.findById(id, userId);
        GetTypeResponse response = GetTypeResponse.fromEntity(foundType);
        return BaseResponse.success(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<UpdateTypeResponse>> updateById(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @PathVariable Long id,
            @Valid @RequestBody UpdateTypeRequest request
    ) {
        Type updateType = typeService.updateById(id, userId, request);
        UpdateTypeResponse response = UpdateTypeResponse.fromEntity(updateType);
        return BaseResponse.success(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Object>> deleteById(
            @RequestHeader(HeaderConstants.USER_ID) Long userId,
            @PathVariable Long id
    ) {
        typeService.deleteById(id, userId);
        return BaseResponse.success(HttpStatus.NO_CONTENT, ApplicationStatus.NO_CONTENT);
    }
}
