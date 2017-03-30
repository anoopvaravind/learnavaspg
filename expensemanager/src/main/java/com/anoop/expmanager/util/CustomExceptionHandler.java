package com.anoop.expmanager.util;

import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: SONY
 * Date: 3/30/17
 * Time: 8:56 PM
 * To change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Response exception(Exception e) {

        e.printStackTrace();
        return new Response(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleError404(HttpServletRequest request, Exception e)   {

        return "404";
    }

    @ExceptionHandler(HibernateException.class)
    public Response hibernateException(Exception e)
    {
        return new Response(e.getMessage());
    }

}

