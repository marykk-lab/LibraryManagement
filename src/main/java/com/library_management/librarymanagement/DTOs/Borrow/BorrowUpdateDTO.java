package com.library_management.librarymanagement.DTOs.Borrow;

import java.time.LocalDate;

public class BorrowUpdateDTO {
    private Long borrowID;
    private Long bookID;
    private String title;
    private Long userID;
    private LocalDate borrowingDate;
    private LocalDate returnDate;

    public BorrowUpdateDTO(Long borrowID, Long bookID, String title, Long userID, LocalDate borrowingDate, LocalDate returnDate) {
        this.borrowID = borrowID;
        this.bookID = bookID;
        this.title = title;
        this.userID = userID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    public BorrowUpdateDTO(Long borrowID, Long bookID, String title, Long userID) {
        this.borrowID = borrowID;
        this.bookID = bookID;
        this.title = title;
        this.userID = userID;
    }

    public BorrowUpdateDTO() {
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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(Long borrowID) {
        this.borrowID = borrowID;
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
}
