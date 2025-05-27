package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRep extends JpaRepository<Book, Long> {
    List<Book> findByTitleIgnoreCaseContaining(String title);

}
