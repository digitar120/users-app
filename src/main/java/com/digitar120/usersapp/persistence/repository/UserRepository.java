package com.digitar120.usersapp.persistence.repository;

import com.digitar120.usersapp.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
