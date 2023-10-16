package com.digitar120.usersapp.mapper;

import com.digitar120.usersapp.persistence.entity.User;
import com.digitar120.usersapp.service.dto.EditUserDTO;
import org.springframework.stereotype.Component;

@Component
public class EditUserDTOToUser implements IGenericMapper<EditUserDTO, User> {

    @Override
    public User map(EditUserDTO in){
        return new User(
                in.getName(),
                in.getLastName()
        );
    }
}
