package com.digitar120.usersapp.exception.globalhandler;

import lombok.Getter;
import lombok.Setter;

/**
 * Transport for exception details.
 * @author Gabriel Pérez (digitar120)
 * @see GlobalExceptionHandler
 */
@Getter
@Setter
public class ErrorDTO {
    public String status;
    public String message;
    public String time;
}
