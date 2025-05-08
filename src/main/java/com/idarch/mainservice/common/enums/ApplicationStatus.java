package com.idarch.mainservice.common.enums;

public enum ApplicationStatus {
    OK("success-1"),
    NO_CONTENT("success-2"),
    NOT_FOUND("error-1");

    private String value;

    ApplicationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
