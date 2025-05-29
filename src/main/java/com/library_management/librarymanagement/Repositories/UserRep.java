package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for User entity CRUD operations extending JpaRepository.
 */
public interface UserRep extends JpaRepository<User, Long> {
    
    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsername(String username);
    
    /**
     * Checks if a user with the given username exists.
     *
     * @param username the username to check
     * @return true if user exists, false otherwise
     */
    Boolean existsUserByUsername(String username);
}