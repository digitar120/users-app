package com.digitar120.usersapp.persistence.repository;

import com.digitar120.usersapp.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository interface for the User table.
 * @see JpaRepository
 * @author Gabriel Pérez (digitar120)
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
