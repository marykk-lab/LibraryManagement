package com.library_management.librarymanagement.DTOs.Book;

public class BookSaveDTO {
    private String title;
    private Long authorID;

    public BookSaveDTO(String title, Long authorID) {
        this.title = title;
        this.authorID = authorID;
    }

    public BookSaveDTO() {
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


    @Override
    public String toString() {
        return "BookSaveDTO{" +
                "title='" + title + '\'' +
                ", authorID=" + authorID +
                '}';
    }
}
