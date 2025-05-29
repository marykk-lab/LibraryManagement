package com.library_management.librarymanagement.DTOs.Borrow;

import java.time.LocalDate;

/**
 * DTO for creating new book borrowing records
 */
public class BorrowSaveDTO {
    private Long bookID;
    private Long userID;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    /**
     * Constructs BorrowSaveDTO with required fields
     * @param bookID Book's unique identifier
     * @param userID User's unique identifier
     */
    public BorrowSaveDTO(Long bookID, Long userID) {
        this.bookID = bookID;
        this.userID = userID;
    }

    /**
     * Constructs BorrowSaveDTO with all fields
     * @param bookID Book's unique identifier
     * @param userID User's unique identifier
     * @param borrowDate Date when the book is borrowed
     * @param returnDate Expected return date
     */
    public BorrowSaveDTO(Long bookID, Long userID, LocalDate borrowDate, LocalDate returnDate) {
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    /**
     * Default constructor
     */
    public BorrowSaveDTO() {
    }

    /**
     * @return Book's ID
     */
    public Long getBookID() {
        return bookID;
    }

    /**
     * @param authorID Book's ID to set
     */
    public void setBookID(Long authorID) {
        this.bookID = authorID;
    }

    /**
     * @return User's ID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * @param userID User's ID to set
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    /**
     * @return Date when the book is borrowed
     */
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    /**
     * @param borrowDate Borrow date to set
     */
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    /**
     * @return Expected return date
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate Return date to set
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}