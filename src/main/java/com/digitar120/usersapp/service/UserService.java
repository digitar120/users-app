package com.digitar120.usersapp.service;

import com.digitar120.usersapp.mapper.NewUserDTOToUser;
import com.digitar120.usersapp.persistence.entity.User;
import com.digitar120.usersapp.persistence.repository.UserRepository;
import com.digitar120.usersapp.service.dto.EditUserDTO;
import com.digitar120.usersapp.service.dto.NewUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.digitar120.usersapp.util.LocalUtilityMethods.*;

@Service
public class UserService {

    private final UserRepository repository;
    private final NewUserDTOToUser newUserMapper;
    public UserService(UserRepository repository, NewUserDTOToUser newUserMapper){
        this.repository = repository;
        this.newUserMapper = newUserMapper;
    }

    // Listar todos
    public List<User> listAllUsers(){
        return repository.findAll();
    }

    // Buscar por DNI (ID)
    public User findById(Integer id){
        return verifyElementExistsAndReturn(repository, id, "No se encontró al usuario", HttpStatus.NOT_FOUND);
    }

    // Agregar
    public User newUser(NewUserDTO userDTO){
        verifyElementNotExists(repository, userDTO.getId(), "El usuario ya existe.", HttpStatus.BAD_REQUEST);
        return repository.save(
                newUserMapper.map(userDTO));
    }

    // Editar
    @Transactional
    public User editUser(Integer id, EditUserDTO editUserDTO){
        verifyElementExists(repository, id, "No se encontró el usuario.", HttpStatus.NOT_FOUND);
        return repository.save(
                new User(id,
                        editUserDTO.getName(),
                        editUserDTO.getLastName())
        );

    }

    // Eliminar
    @Transactional
    public void deleteUser(Integer id){
        verifyElementExists(repository, id, "No se encontró el usuario.", HttpStatus.NOT_FOUND);
        repository.deleteById(id);
    }
}
