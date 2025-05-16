package com.digitar120.usersapp.controller;

import com.digitar120.usersapp.mapper.NewUserDTOToUser;
import com.digitar120.usersapp.persistence.entity.User;
import com.digitar120.usersapp.service.UserService;
import com.digitar120.usersapp.service.dto.EditUserDTO;
import com.digitar120.usersapp.service.dto.NewUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User endpoint configuration.
 * @author Gabriel Pérez (digitar120)
 * @see User
 * @see UserService
 * @see com.digitar120.usersapp.persistence.repository.UserRepository
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    public UserController(UserService service) {
        this.service = service;
    }

    // Read operations

    /**
     * List all elements within the repository.
     * @return A List of Users of the contents of the repository.
     */
    @Operation(summary = "Listar todos los usuarios", description = "Devuelve todos los usuarios registrados")
    @ApiResponses(value={
            @ApiResponse(responseCode ="404" , description = "Completado correctamente")
    })
    @GetMapping
    public List<User> findAll(){return service.listAllUsers();}

    /**
     * Find a user matching their ID.
     * @param id The ID to match the user with.
     * @return A matching user, if there's a match.
     */
    @Operation(summary = "Buscar por ID", description = "De encontrarse, devuelve la información de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Completado correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró al usuario")
    })
    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id){
        return service.findById(id);
    }

    // Create operations

    /**
     * Create a new user in the database.
     * @param newUserDTO Object containing the new user's information, read from a JSON file.
     * @return A copy of the new user object created in the database.
     */
    @Operation(summary = "Registrar usuario", description = "Registrar un nuevo usuario, mediante cuerpo JSON, incluyendo todos los datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Completado correctamente"),
            @ApiResponse(responseCode = "400", description = "El usuario ya existe")
    })
    @PostMapping
    public User newUser(@RequestBody NewUserDTO newUserDTO){
        return service.newUser(newUserDTO);
    }

    // Update operations

    /**
     * Update a user's information.
     * @param id The ID to match a user with.
     * @param userDTO Object containing new information.
     * @return A copy of the updated user object.
     */
    @Operation(summary = "Editar usuario", description = "Editar un usuario, ingresando ID mediante URL, y datos mediante JSON.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Completado correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró al usuario")
    })
    @PutMapping("/{id}")
    public User editUser(@PathVariable Integer id, @RequestBody EditUserDTO userDTO){
        return service.editUser(id, userDTO);
    }

    // Delete operations

    /**
     * Delete a user's entry from the database.
     * @param id ID to match the user entry with.
     */
    @Operation(summary = "Remover usuario", description = "Remover un usuario, ingresando ID mediante URL")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Completado correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró al usuario")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.deleteUser(id);
    }
}
