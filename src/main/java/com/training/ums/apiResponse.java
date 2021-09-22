package com.training.ums;

import antlr.collections.List;

public class apiResponse {
    private boolean success;
    private String message;
    private Object data;


    public apiResponse() {
      
    }
    
    public apiResponse(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    
    
}
