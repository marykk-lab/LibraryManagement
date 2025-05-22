package com.library_management.librarymanagement.DTOs;

public class SignInDTO {
    private String username;
    private String password;
    private String token;
    private int statusCode;
    private String error;

    public SignInDTO(String username, String password, String token, int statusCode, String error) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.statusCode = statusCode;
        this.error = error;
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
}
