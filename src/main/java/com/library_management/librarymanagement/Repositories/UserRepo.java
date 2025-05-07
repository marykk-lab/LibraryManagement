package com.library_management.librarymanagement.Repositories;

import com.library_management.librarymanagement.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
