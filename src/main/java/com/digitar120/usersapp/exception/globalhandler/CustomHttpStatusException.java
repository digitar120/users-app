package com.digitar120.usersapp.exception.globalhandler;

import org.springframework.http.HttpStatus;

/**
 * Exception superclass for HTTP error responses.
 * @author Gabriel Pérez (digitar120)
 * @see BadRequestException
 * @see NotFoundException
 */
public class CustomHttpStatusException extends RuntimeException{
    public CustomHttpStatusException(String message){ super(message);}

    public HttpStatus getStatus(){
        return HttpStatus.NO_CONTENT;
    }
}
