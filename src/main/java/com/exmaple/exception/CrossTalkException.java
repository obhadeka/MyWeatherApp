package com.exmaple.exception;

public class CrossTalkException extends RuntimeException {
	
	public enum CrossTalkType{
        TID,
        CONTENT
    }
    private String requestValue;
    private String responseValue;
    private CrossTalkType crossTalkType;
    
    public CrossTalkException() {
        super();
    }

    public CrossTalkException(CrossTalkType type, String requestValue, String responseValue) {
        super();
        this.requestValue = requestValue;
        this.responseValue = responseValue;
        this.crossTalkType = type;
    }

    public CrossTalkType getCrossTalkType() {
		return crossTalkType;
	}

	/**
     * Custom toString to print out the Status object along with the stack trace.
     */
    @Override
    public String toString() {
        StringBuilder exceptionMessageBuilder = new StringBuilder();
        exceptionMessageBuilder.append(super.toString()).append(": [");
        exceptionMessageBuilder.append("CrossTalk type: ").append(crossTalkType).append(" Request value: {").append(requestValue).append("} did not match Response value {").append(responseValue).append("}].");
        return exceptionMessageBuilder.toString();
    }
}
