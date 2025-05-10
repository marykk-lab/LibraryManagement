package com.library_management.librarymanagement.DTOs;

import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Entities.User;

import java.time.LocalDate;

public class BorrowerDTO {
    private Long borrowerID;

    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private Long bookID;
    private Long userID;

    public BorrowerDTO(Long borrowerID, LocalDate borrowingDate, LocalDate returnDate, Long bookID, Long userID) {
        this.borrowerID = borrowerID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.bookID = bookID;
        this.userID = userID;
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

    public Book getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "BorrowerDTO{" +
                "borrowerID=" + borrowerID +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                ", bookID=" + bookID +
                ", userID=" + userID +
                '}';
    }
}
