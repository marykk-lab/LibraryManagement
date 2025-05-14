package com.library_management.librarymanagement.DTOs;

import com.library_management.librarymanagement.Entities.Author;

public class BookDTO {
    private Long bookID;
    private String title;
    private Long authorID;

    public BookDTO(Long bookID, String title, Long authorID) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
    }

    public BookDTO() {
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
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
        return "BookDTO{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", authorID=" + authorID +
                '}';
    }
}
