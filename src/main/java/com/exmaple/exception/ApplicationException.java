package com.exmaple.exception;

import com.exmaple.constants.Errors;
import com.exmaple.error.ErrorInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {

    protected ErrorInfo errorInfo;

    protected HttpStatus httpStatus;

    public ApplicationException() {
        super();
    }

    public ApplicationException(Throwable t) {
        super(t);
    }

    public ApplicationException(Errors error) {
        this(error.getCode(), error.getMessage());
    }

    public ApplicationException(Errors error, HttpStatus status) {
        this(error.getMessage(), error.getCode(), status);

    }

    public ApplicationException(String errorMessage, String errorCode, HttpStatus status) {
        super();
        this.errorInfo = new ErrorInfo();
        errorInfo.setMessage(errorMessage);
        errorInfo.setCode(errorCode);
        this.httpStatus = status;
    }

    public ApplicationException(String errorMessage, String errorCode) {
        this(errorMessage, errorCode, null);
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


    /**
     * Custom toString to print out the ErrorInfo object along with the stack trace.
     */
    @Override
    public String toString() {
        return new StringBuilder(super.toString()).toString();
    }

    @Override
    public String getMessage() {
        StringBuilder exceptionMessageBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(super.getMessage())) {
            exceptionMessageBuilder.append(super.getMessage());
        }
        ErrorInfo errorInfo = this.getErrorInfo();
        if (errorInfo != null) {
            exceptionMessageBuilder.append("\n\t[ Error Code: ").append(errorInfo.getCode());
            exceptionMessageBuilder.append(" Error Message: ").append(errorInfo.getMessage())
                    .append(" ]");
        }
        return exceptionMessageBuilder.toString();
    }
}