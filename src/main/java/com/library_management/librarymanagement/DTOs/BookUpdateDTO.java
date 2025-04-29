package com.library_management.librarymanagement.DTOs;

public class BookUpdateDTO {
    private Long bookID;
    private String title;
    private Long authorID;

    public BookUpdateDTO(Long bookID, String title, Long authorID) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
    }

    public BookUpdateDTO() {
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
        return "BookUpdateDTO{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", authorID=" + authorID +
                '}';
    }
}
