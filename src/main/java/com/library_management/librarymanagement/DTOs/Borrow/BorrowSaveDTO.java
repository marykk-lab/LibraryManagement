package com.library_management.librarymanagement.DTOs.Borrow;

import java.time.LocalDate;

public class BorrowSaveDTO {
    private Long bookID;
    private Long userID;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowSaveDTO(Long bookID, Long userID) {
        this.bookID = bookID;
        this.userID = userID;
    }

    public BorrowSaveDTO(Long bookID, Long userID, LocalDate borrowDate, LocalDate returnDate) {
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowSaveDTO() {
    }


    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long authorID) {
        this.bookID = authorID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
