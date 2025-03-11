package com.digitar120.usersapp.mapper;

import com.digitar120.usersapp.persistence.entity.User;
import com.digitar120.usersapp.service.dto.EditUserDTO;
import com.digitar120.usersapp.service.dto.NewUserDTO;
import org.springframework.stereotype.Component;

/**
 * Mapping class for user edits.
 * @see com.digitar120.usersapp.service.UserService#editUser(Integer, EditUserDTO)
 * @author Gabriel Pérez (digitar120)
 * @see IGenericMapper
 * @see EditUserDTO
 */
@Component
public class EditUserDTOToUser implements IGenericMapper<EditUserDTO, User> {

    /**
     * Build a new user object with the contents of the DTO.
     * @param in Input DTO containing new information.
     * @return A user object based on the DTO.
     */
    @Override
    public User map(EditUserDTO in){
        return new User(
                in.getName(),
                in.getLastName()
        );
    }
}
