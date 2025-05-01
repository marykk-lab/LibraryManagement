package com.library_management.librarymanagement.DTOs;

public class BorrowerSaveDTO {
    private String title;
    private Long authorID;

    public BorrowerSaveDTO(String title, Long authorID) {
        this.title = title;
        this.authorID = authorID;
    }

    public BorrowerSaveDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }
}
