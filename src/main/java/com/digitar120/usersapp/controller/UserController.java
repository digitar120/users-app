package com.digitar120.usersapp.controller;

import com.digitar120.usersapp.mapper.NewUserDTOToUser;
import com.digitar120.usersapp.persistence.entity.User;
import com.digitar120.usersapp.service.UserService;
import com.digitar120.usersapp.service.dto.EditUserDTO;
import com.digitar120.usersapp.service.dto.NewUserDTO;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    // Listar todos
    @Operation(summary = "Listar todos los usuarios", description = "Devuelve todos los usuarios registrados")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Completado correctamente")
    })
    @GetMapping
    public List<User> findAll(){return service.listAllUsers();}

    // Buscar por DNI
    @Operation(summary = "Buscar por ID", description = "De encontrarse, devuelve la información de un usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completado correctamente"),
            @ApiResponse(code = 404, message = "No se encontró al usuario")
    })
    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return service.findById(id);
    }

    // Agregar
    @Operation(summary = "Registrar usuario", description = "Registrar un nuevo usuario, mediante cuerpo JSON, incluyendo todos los datos.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completado correctamente"),
            @ApiResponse(code = 400, message = "El usuario ya existe")
    })
    @PostMapping
    public User newUser(@RequestBody NewUserDTO newUserDTO){
        return service.newUser(newUserDTO);
    }

    // Editar
    @Operation(summary = "Editar usuario", description = "Editar un usuario, ingresando ID mediante URL, y datos mediante JSON.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completado correctamente"),
            @ApiResponse(code = 404, message = "No se encontró al usuario")
    })
    @PutMapping("/{id}")
    public User editUser(@PathVariable Integer id, @RequestBody EditUserDTO userDTO){
        return service.editUser(id, userDTO);
    }

    // Eliminar
    @Operation(summary = "Remover usuario", description = "Remover un usuario, ingresando ID mediante URL")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Completado correctamente"),
            @ApiResponse(code = 404, message = "No se encontró al usuario")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.deleteUser(id);
    }
}
