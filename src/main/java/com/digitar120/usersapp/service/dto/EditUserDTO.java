package com.digitar120.usersapp.service.dto;

import lombok.*;

/**
 * DTO useful for user edits.
 * @see com.digitar120.usersapp.service.UserService#editUser(Integer, EditUserDTO)
 * @author Gabriel Pérez (digitar120)
 * @see com.digitar120.usersapp.mapper.EditUserDTOToUser
 */
@Getter @Setter @ToString @AllArgsConstructor @RequiredArgsConstructor
public class EditUserDTO {
    private String name;
    private String lastName;
}
