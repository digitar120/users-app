package com.digitar120.usersapp.exception.globalhandler;

import org.springframework.http.HttpStatus;

/**
 * Exception class for HTTP 400 responses.
 * @author Gabriel Pérez (digitar120)
 * @see CustomHttpStatusException
 */
public class BadRequestException extends CustomHttpStatusException{
    public BadRequestException(String message){
        super(message);
    }

    public HttpStatus getStatus(){
        return HttpStatus.BAD_REQUEST;
    }
}
