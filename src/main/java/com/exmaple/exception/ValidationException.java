package com.exmaple.exception;

import com.exmaple.constants.Errors;
import com.exmaple.error.ErrorInfo;
import org.springframework.http.HttpStatus;

public class ValidationException extends ApplicationException {
    public ValidationException() {
        super();
    }

    public ValidationException(Errors error) {
        this(error, HttpStatus.BAD_REQUEST);
    }

    public ValidationException(Errors error, HttpStatus status) {
        super(error, status);
    }
    public ValidationException(ErrorInfo errorInfo) {
        this(errorInfo, HttpStatus.BAD_REQUEST);
    }

    public ValidationException(ErrorInfo errorInfo, HttpStatus status) {
        super();
        this.errorInfo = errorInfo;
        this.httpStatus = status;
    }
}