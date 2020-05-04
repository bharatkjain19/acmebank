package com.acmebank.accountmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AccountException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errorMessage;
    private final String fieldName;
    private final Object fieldValue;

    public AccountException(String errorMessage, String fieldName, Object fieldValue) {
        super(String.format("%s  %s : '%s'", errorMessage, fieldName, fieldValue));
        this.errorMessage = errorMessage;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
