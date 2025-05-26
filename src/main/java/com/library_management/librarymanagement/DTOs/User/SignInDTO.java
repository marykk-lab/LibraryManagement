package com.library_management.librarymanagement.DTOs.User;

import com.library_management.librarymanagement.Entities.User;

public class SignInDTO {
    private String username;
    private String password;
    private String token;
    private int statusCode;
    private String error;
    private String message;
    private String refreshToken;
    private String expirationTime;
    private String role;
    private User user;

    public SignInDTO(String username, String password, String token, int statusCode, String error, String message, String refreshToken, String expirationTime, String role) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.refreshToken = refreshToken;
        this.expirationTime = expirationTime;
        this.role = role;
    }

    public SignInDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SignInDTO() {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SignInDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", statusCode=" + statusCode +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expirationTime='" + expirationTime + '\'' +
                ", role='" + role + '\'' +
                ", user=" + user +
                '}';
    }
}
