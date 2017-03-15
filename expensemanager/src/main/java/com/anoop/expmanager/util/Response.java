package com.anoop.expmanager.util;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/15/17
 * Time: 9:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class Response<T> {

    private T data;


    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}