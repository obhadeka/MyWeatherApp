package com.exmaple.exception;

import com.exmaple.constants.Errors;
import com.exmaple.error.ErrorInfo;
import org.springframework.http.HttpStatus;

public class ServiceException extends ApplicationException {
    public ServiceException() {
        super();
    }

    public ServiceException(ErrorInfo errorInfo) {
        super();
        this.errorInfo = errorInfo;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public ServiceException(Errors error) {
        super(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ServiceException(Errors error, HttpStatus status) {
        super(error, status);

    }

    public ServiceException(Throwable t, ErrorInfo errorInfo) {
        this(t, errorInfo, HttpStatus.BAD_REQUEST);
    }

    public ServiceException(Throwable t, ErrorInfo errorInfo, HttpStatus status) {
        super(t);
        this.errorInfo = errorInfo;
        this.httpStatus = status;
    }
    public ServiceException(Throwable t) {
        super(t);
    }
}