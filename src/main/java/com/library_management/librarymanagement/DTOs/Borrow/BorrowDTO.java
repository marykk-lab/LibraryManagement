package com.library_management.librarymanagement.DTOs.Borrow;


import java.time.LocalDate;

public class BorrowDTO {
    private Long borrowID;

    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private Long bookID;
    private Long userID;
    private String bookTitle;
    private String username;

    public BorrowDTO(Long borrowID, LocalDate borrowingDate, LocalDate returnDate, Long bookID, Long userID, String bookTitle, String username) {
        this.borrowID = borrowID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.bookID = bookID;
        this.userID = userID;
        this.bookTitle = bookTitle;
        this.username = username;
    }

    public BorrowDTO() {
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

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "BorrowDTO{" +
                "borrowID=" + borrowID +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                ", bookID=" + bookID +
                ", userID=" + userID +
                '}';
    }
}
