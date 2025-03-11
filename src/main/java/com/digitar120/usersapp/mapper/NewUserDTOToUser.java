package com.digitar120.usersapp.mapper;

import com.digitar120.usersapp.persistence.entity.User;
import com.digitar120.usersapp.service.dto.NewUserDTO;
import org.springframework.stereotype.Component;

/**
 * Mapping class useful for creating new users.
 * @see com.digitar120.usersapp.service.UserService#newUser(NewUserDTO)
 * @author Gabriel Pérez (digitar120)
 * @see NewUserDTO
 * @see IGenericMapper
 */
@Component
public class NewUserDTOToUser implements IGenericMapper<NewUserDTO, User> {

    /**
     * Build a new User object copying the information of the DTO.
     * @param in Input {@code NewUserDTO} object with relevant information.
     * @return A newly made User object.
     */
    @Override
    public User map(NewUserDTO in){
        return new User(
                in.getId(),
                in.getName(),
                in.getLastName());
    }
}
