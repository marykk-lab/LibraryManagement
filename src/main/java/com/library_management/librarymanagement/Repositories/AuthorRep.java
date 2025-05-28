package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRep extends JpaRepository<Author, Long> {
    List<Author> findByNameIgnoreCaseContaining(String name);
}
