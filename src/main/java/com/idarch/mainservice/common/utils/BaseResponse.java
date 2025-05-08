package com.idarch.mainservice.common.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.idarch.mainservice.common.dto.response.ErrorResponse;
import com.idarch.mainservice.common.dto.response.SuccessResponse;
import com.idarch.mainservice.common.enums.ApplicationStatus;

public class BaseResponse {
    public static <T> ResponseEntity<SuccessResponse<T>> success(
        HttpStatus status,
        ApplicationStatus applicationStatus,
        T body
    ) {
        SuccessResponse<T> response = SuccessResponse.<T>builder()
            .status(status.value())
            .applicationStatus(applicationStatus.getValue())
            .data(body)
            .build();
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<SuccessResponse<T>> success(
        HttpStatus httpStatus,
        ApplicationStatus applicationStatus
    ) {
        return success(httpStatus, applicationStatus, null);
    }

    public static <T> ResponseEntity<SuccessResponse<T>> success(T body) {
        return success(HttpStatus.OK, ApplicationStatus.OK, body);
    }

    public static <T> ResponseEntity<ErrorResponse<T>> error(
        HttpStatus status,
        ApplicationStatus applicationStatus,
        T body
    ) {
        ErrorResponse<T> response = ErrorResponse.<T>builder()
            .status(status.value())
            .applicationStatus(applicationStatus.getValue())
            .data(body)
            .build();
        return ResponseEntity.status(status).body(response);
    }
}
