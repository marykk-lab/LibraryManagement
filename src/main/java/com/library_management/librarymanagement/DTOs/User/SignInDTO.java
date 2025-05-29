package com.library_management.librarymanagement.DTOs.User;

import com.library_management.librarymanagement.Entities.User;

/**
 * DTO for handling user sign-in requests and responses
 */
public class SignInDTO {
    private String username;
    private String password;
    private String token;
    private int statusCode;
    private String error;
    private String message;
    private String role;
    private User user;

    /**
     * Constructs SignInDTO with authentication response data
     * @param username User's username
     * @param password User's password
     * @param token Authentication token
     * @param statusCode Response status code
     * @param error Error message if any
     * @param message Response message
     * @param role User's role
     */
    public SignInDTO(String username, String password, String token, int statusCode, String error, String message, String role) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.role = role;
    }

    /**
     * Constructs SignInDTO with credentials
     * @param username User's username
     * @param password User's password
     */
    public SignInDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Default constructor
     */
    public SignInDTO() {
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
     * @return Authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token Authentication token to set
     */
    public void setToken(String token) {
        this.token = token;
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
     * @return User's role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role Role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return User entity
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user User entity to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return String representation of SignInDTO
     */
    @Override
    public String toString() {
        return "SignInDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", statusCode=" + statusCode +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", role='" + role + '\'' +
                ", user=" + user +
                '}';
    }
}