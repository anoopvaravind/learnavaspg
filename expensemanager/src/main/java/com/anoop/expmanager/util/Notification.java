package com.anoop.expmanager.util;

/**
 * Created by 587570 on 3/21/2017.
 */
public class Notification {
    boolean success;
    boolean error;
    String message;

    public Notification(boolean success, boolean error, String message) {
        this.success = success;
        this.error = error;
        this.message = message;
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
}
