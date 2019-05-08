package com.exmaple.constants;

public enum Errors {
    // Input validation errors
    CITY_NAME_BLANK("WFA_10001", "City name cannot be blank."),
    CITY_NAME_INVALID("WFA_10002", "City name invalid."),
    QUERY_PARAM_CONTAINS_NON_PRINTABLE_ASCII("WFA_10003", "Query param should only contain printable ASCII characters."),
    QUERY_PARAM_INVALID_LENGTH("WFA_10004", "One of the query parameter length is greater than max length."),


    // Security errors

    // Business errors

    UNMAPPED_ERROR("WFA_90000", "Server cannot handle this request."),
    CROSS_TALK_ERROR("WFA_90001", "Cross talk error."),
    GENERIC_ERROR("WFA_99999", "Internal server error.");

    private final String code;
    private final String message;

    Errors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
