package com.digitar120.usersapp.mapper;

/**
 * Simple mapping interface that restricts in and out types.
 * @param <I> Input type
 * @param <O> Output type
 * @author Gabriel Pérez (digitar120)
 * @see EditUserDTOToUser
 * @see NewUserDTOToUser
 */
public interface IGenericMapper<I, O> {
    public O map(I in);
}
