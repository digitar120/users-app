package com.digitar120.usersapp.service.dto;

import lombok.*;

/**
 * DTO useful for registering new users.
 * @see com.digitar120.usersapp.service.UserService#newUser(NewUserDTO)
 * @author Gabriel Pérez (digitar120)
 * @see com.digitar120.usersapp.mapper.NewUserDTOToUser
 */
@Getter @Setter @ToString @RequiredArgsConstructor @AllArgsConstructor
public class NewUserDTO {

    /**
     * As a personally identifying number, IDs must be managed manually.
     */
    private Integer id;
    private String name;
    private String lastName;
}
