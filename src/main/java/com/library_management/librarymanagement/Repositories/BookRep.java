package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Book entities.
 * Extends JpaRepository to provide CRUD operations for Book entities.
 */
public interface BookRep extends JpaRepository<Book, Long> {
    /**
     * Finds books whose titles contain the specified string (case-insensitive).
     *
     * @param title The string to search for in book titles
     * @return A list of Book entities whose titles contain the specified string
     */
    List<Book> findByTitleIgnoreCaseContaining(String title);

}
