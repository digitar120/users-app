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
    @Operation(summary = "List all users.", description = "Produce a JSON object with details about all registered users.")
    @ApiResponses(value={
            @ApiResponse(responseCode ="404" , description = "Request completed correctly.")
    })
    @GetMapping
    public List<User> findAll(){return service.listAllUsers();}

    /**
     * Find a user matching their ID.
     * @param id The ID to match the user with.
     * @return A matching user, if there's a match.
     */
    @Operation(summary = "Search user by ID.", description = "Produces a JSON object with details about a matching user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed correctly."),
            @ApiResponse(responseCode = "404", description = "User not found.")
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
    @Operation(summary = "Register a new user.", description = "Register a new user in the database with the provided ID (integer), name and last name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Completed correctly."),
            @ApiResponse(responseCode = "400", description = "An user has already been registered with that ID.")
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
    @Operation(summary = "Edit an user's details.", description = "Edit an user's information, providing their ID in the URL, and the new information in a JSON object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed correctly."),
            @ApiResponse(responseCode = "404", description = "User not found.")
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
    @Operation(summary = "Remove an user from the database.", description = "Remove an entry from the database, citing their ID in the URL.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed correctly."),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.deleteUser(id);
    }
}
