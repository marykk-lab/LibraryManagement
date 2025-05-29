package com.library_management.librarymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Library Management System.
 * Serves as the entry point for the Spring Boot application.
 * @author Maksym Osetsymskyi
 */
@SpringBootApplication
public class LibraryManagementApplication {

    /**
     * Main method that starts the Spring Boot application.
     *
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }
}