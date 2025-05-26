package com.library_management.librarymanagement.DTOs.Author;

public class AuthorSaveDTO {
    private String name;

    public AuthorSaveDTO(String name) {
        this.name = name;
    }

    public AuthorSaveDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorSaveDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
