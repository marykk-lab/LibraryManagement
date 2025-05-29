package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Author entities.
 * Extends JpaRepository to provide CRUD operations for Author entities.
 */
public interface AuthorRep extends JpaRepository<Author, Long> {
    /**
     * Finds authors whose names contain the specified string (case-insensitive).
     *
     * @param name The string to search for in author names
     * @return A list of Author entities whose names contain the specified string
     */
    List<Author> findByNameIgnoreCaseContaining(String name);
}