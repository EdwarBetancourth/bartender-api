package com.bartender.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private static String CODE = "400";
    private static HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static String MESSAGE = "Input Validation Error.";

    private String code;
    private String message;
    private Object errors;
    private HttpStatus status;

    public String getCode() {
        return CODE;
    }

    public HttpStatus getStatus() {
        return STATUS;
    }

    public String getMessage() {
        return MESSAGE;
    }
}
