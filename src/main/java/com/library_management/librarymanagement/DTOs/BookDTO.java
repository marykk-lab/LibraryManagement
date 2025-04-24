package com.library_management.librarymanagement.DTOs;

import com.library_management.librarymanagement.Entities.Author;

public class BookDTO {
    private Long bookID;
    private String title;
    private Author author;

    public BookDTO(Long bookID, String title, Author author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
