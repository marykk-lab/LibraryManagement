package com.library_management.librarymanagement.DTOs.User;

/**
 * DTO for updating user information
 */
public class UserUpdateDTO {
    private Long userID;
    private String username;
    private String password;
    private String email;
    private String role;
    private String phone;
    private String city;

    /**
     * Constructs UserUpdateDTO with all fields
     * @param userID User's unique identifier
     * @param username User's username
     * @param password User's password
     * @param email User's email address
     * @param role User's role
     * @param phone User's phone number
     * @param city User's city
     */
    public UserUpdateDTO(Long userID, String username, String password, String email, String role, String phone, String city) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.city = city;
    }

    /**
     * Constructs UserUpdateDTO without user ID
     * @param username User's username
     * @param password User's password
     * @param email User's email address
     * @param role User's role
     * @param phone User's phone number
     * @param city User's city
     */
    public UserUpdateDTO(String username, String password, String email, String role, String phone, String city) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.city = city;
    }

    /**
     * Default constructor
     */
    public UserUpdateDTO() {
    }

    /**
     * Constructs UserUpdateDTO with password only
     * @param password User's password
     */
    public UserUpdateDTO(String password) {
        this.password = password;
    }

    /**
     * @return User's ID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * @param userID User's ID to set
     */
    public void setUserID(Long userID) {
        this.userID = userID;
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
     * @return Phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone Phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return City
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city City to set
     */
    public void setCity(String city) {
        this.city = city;
    }
}