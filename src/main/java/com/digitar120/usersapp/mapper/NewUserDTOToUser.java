package com.digitar120.usersapp.mapper;

import com.digitar120.usersapp.persistence.entity.User;
import com.digitar120.usersapp.service.dto.NewUserDTO;
import org.springframework.stereotype.Component;

@Component
public class NewUserDTOToUser implements IGenericMapper<NewUserDTO, User> {

    @Override
    public User map(NewUserDTO in){
        return new User(
                in.getId(),
                in.getName(),
                in.getLastName());
    }
}
