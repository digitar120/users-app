package com.digitar120.usersapp.exception.globalhandler;

import org.springframework.http.HttpStatus;

/**
 * Exception class for HTTP 404 responses.
 * @author Gabriel Pérez (digitar120)
 * @see CustomHttpStatusException
 */
public class NotFoundException extends CustomHttpStatusException{
    public NotFoundException(String message){
        super(message);
    }

    public HttpStatus getStatus(){
        return HttpStatus.NOT_FOUND;
    }
}
