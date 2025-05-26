package com.library_management.librarymanagement.DTOs.Author;

public class AuthorUpdateDTO {
    private Long authorID;
    private String name;

    public AuthorUpdateDTO(Long authorID, String name) {
        this.authorID = authorID;
        this.name = name;
    }

    public AuthorUpdateDTO() {
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "authorID=" + authorID +
                ", name='" + name + '\'' +
                '}';
    }
}
