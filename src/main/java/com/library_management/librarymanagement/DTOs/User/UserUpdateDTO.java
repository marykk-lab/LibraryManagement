package com.library_management.librarymanagement.DTOs.User;

public class  UserUpdateDTO {
    private Long userID;
    private String username;
    private String password;
    private String email;
    private String role;
    private String phone;
    private String city;


    public UserUpdateDTO(Long userID, String username, String password, String email, String role, String phone, String city) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.city = city;
    }

    public UserUpdateDTO(String username, String password, String email, String role, String phone, String city) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.city = city;
    }

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(String password) {
        this.password = password;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
