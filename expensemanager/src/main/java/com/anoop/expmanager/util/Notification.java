package com.anoop.expmanager.util;

/**
 * Created by 587570 on 3/21/2017.
 */
public class Notification {
    boolean success;
    boolean error;
    String message;
    Object response;

    public Notification(boolean success, boolean error, String message, Object response) {
        this.success = success;
        this.error = error;
        this.message = message;
        this.response = response;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
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

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
