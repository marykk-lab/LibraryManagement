package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRep extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsUserByUsername(String username);
}
