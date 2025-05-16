package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRep extends JpaRepository<Borrow, Long> {
}
