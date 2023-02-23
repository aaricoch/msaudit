package com.nttd.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private int code;
    private String message;
    private String errorMessage;
    private String description;
    private Object response;

    public ResponseDto() {
    }

    public ResponseDto(int code, String message, Object response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public ResponseDto(int code, String errorMessage, String description) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.description = description;
    }
}