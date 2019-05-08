package com.exmaple.error;

import java.io.Serializable;

public class ErrorInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String code;
    protected String message;

    public ErrorInfo() {
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String value) {
        this.message = value;
    }
}
