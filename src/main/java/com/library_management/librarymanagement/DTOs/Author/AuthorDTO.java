package com.library_management.librarymanagement.DTOs.Author;

public class AuthorDTO {
    private Long authorID;
    private String name;

    public AuthorDTO(Long authorID, String name) {
        this.authorID = authorID;
        this.name = name;
    }

    public AuthorDTO() {
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
