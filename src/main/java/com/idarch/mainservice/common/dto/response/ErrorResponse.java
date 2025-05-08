package com.idarch.mainservice.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ErrorResponse<T> {
    private Integer status;

    private String applicationStatus;

    private T data;
}
