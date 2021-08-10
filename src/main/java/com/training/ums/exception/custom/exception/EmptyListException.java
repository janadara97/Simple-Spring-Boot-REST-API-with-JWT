package com.training.ums.exception.custom.exception;

public class EmptyListException extends RuntimeException {
    private String errorCode;
    private String errorMessage;


    public EmptyListException() {
        
    }

    public EmptyListException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    
}
