package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Borrow entities.
 * Extends JpaRepository to provide CRUD operations for Borrow entities.
 */
public interface BorrowRep extends JpaRepository<Borrow, Long> {
}