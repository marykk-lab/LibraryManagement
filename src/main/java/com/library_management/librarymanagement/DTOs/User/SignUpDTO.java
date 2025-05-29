package com.library_management.librarymanagement.DTOs.User;

/**
 * DTO for handling user registration requests and responses
 */
public class SignUpDTO {
    private String username;
    private String password;
    private String email;
    private String message;
    private int statusCode;
    private String error;

    /**
     * Constructs SignUpDTO with user registration data
     * @param username User's username
     * @param password User's password
     * @param email User's email address
     */
    public SignUpDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Constructs SignUpDTO with registration response data
     * @param username User's username
     * @param password User's password
     * @param email User's email address
     * @param message Response message
     * @param statusCode Response status code
     * @param error Error message if any
     */
    public SignUpDTO(String username, String password, String email, String message, int statusCode, String error) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.message = message;
        this.statusCode = statusCode;
        this.error = error;
    }

    /**
     * Default constructor
     */
    public SignUpDTO() {
    }

    /**
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username Username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password Password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message Message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return Response status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode Status code to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return Error message
     */
    public String getError() {
        return error;
    }

    /**
     * @param error Error message to set
     */
    public void setError(String error) {
        this.error = error;
    }
}