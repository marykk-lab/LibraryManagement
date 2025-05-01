package com.library_management.librarymanagement.DTOs;

import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Entities.User;

import java.time.LocalDate;

public class BorrowerDTO {
    private Long borrowerID;

    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private Book book;
    private User user;

    public BorrowerDTO(Long borrowerID, LocalDate borrowingDate, LocalDate returnDate, Book book, User user) {
        this.borrowerID = borrowerID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public BorrowerDTO() {
    }

    public Long getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(Long borrowerID) {
        this.borrowerID = borrowerID;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BorrowerDTO{" +
                "borrowerID=" + borrowerID +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
