package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRep extends JpaRepository<Borrower, Long> {
}
