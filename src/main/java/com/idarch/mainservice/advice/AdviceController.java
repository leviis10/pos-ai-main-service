package com.idarch.mainservice.advice;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.idarch.mainservice.common.dto.response.ErrorResponse;
import com.idarch.mainservice.common.enums.ApplicationStatus;
import com.idarch.mainservice.common.utils.BaseResponse;

@RestControllerAdvice
public class AdviceController {
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse<Map>> handleNoSuchElementException(NoSuchElementException e) {
        return BaseResponse.error(HttpStatus.NOT_FOUND, ApplicationStatus.NOT_FOUND, Collections.EMPTY_MAP);
    }
}
