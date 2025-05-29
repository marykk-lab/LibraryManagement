package com.library_management.librarymanagement.DTOs.Borrow;

import java.time.LocalDate;

/**
 * DTO for updating existing book borrowing records
 */
public class BorrowUpdateDTO {
    private Long borrowID;
    private Long bookID;
    private String title;
    private Long userID;
    private LocalDate borrowingDate;
    private LocalDate returnDate;

    /**
     * Constructs BorrowUpdateDTO with all fields
     * @param borrowID Borrow record's unique identifier
     * @param bookID Book's unique identifier
     * @param title Book's title
     * @param userID User's unique identifier
     * @param borrowingDate Date when the book was borrowed
     * @param returnDate Expected return date
     */
    public BorrowUpdateDTO(Long borrowID, Long bookID, String title, Long userID, LocalDate borrowingDate, LocalDate returnDate) {
        this.borrowID = borrowID;
        this.bookID = bookID;
        this.title = title;
        this.userID = userID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    /**
     * Constructs BorrowUpdateDTO with essential fields
     * @param borrowID Borrow record's unique identifier
     * @param bookID Book's unique identifier
     * @param title Book's title
     * @param userID User's unique identifier
     */
    public BorrowUpdateDTO(Long borrowID, Long bookID, String title, Long userID) {
        this.borrowID = borrowID;
        this.bookID = bookID;
        this.title = title;
        this.userID = userID;
    }

    /**
     * Default constructor
     */
    public BorrowUpdateDTO() {
    }

    /**
     * @return Book's ID
     */
    public Long getBookID() {
        return bookID;
    }

    /**
     * @param bookID Book's ID to set
     */
    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    /**
     * @return Book's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title Book's title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return Borrow record's ID
     */
    public Long getBorrowID() {
        return borrowID;
    }

    /**
     * @param borrowID Borrow record's ID to set
     */
    public void setBorrowID(Long borrowID) {
        this.borrowID = borrowID;
    }

    /**
     * @return Date when the book was borrowed
     */
    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    /**
     * @param borrowingDate Borrowing date to set
     */
    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
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