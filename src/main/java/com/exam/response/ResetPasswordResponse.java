package com.exam.response;

import java.util.ArrayList;
import java.util.List;

import com.exam.validation.ValidationError;

public class ResetPasswordResponse {

    private String message;
    private List<ValidationError> errors;

    public ResetPasswordResponse() {
        this.errors = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void addValidationError(String field, String message) {
        ValidationError error = new ValidationError(field, message);
        errors.add(error);
    }
}
