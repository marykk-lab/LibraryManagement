package com.library_management.librarymanagement.DTOs.Borrow;

import java.time.LocalDate;

/**
 * Data Transfer Object for book borrowing information
 */
public class BorrowDTO {
    private Long borrowID;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private Long bookID;
    private Long userID;
    private String bookTitle;
    private String username;

    /**
     * Constructs BorrowDTO with all fields
     * @param borrowID Unique identifier for the borrow record
     * @param borrowingDate Date when the book was borrowed
     * @param returnDate Expected return date
     * @param bookID Book's unique identifier
     * @param userID User's unique identifier
     * @param bookTitle Title of the borrowed book
     * @param username Name of the user who borrowed
     */
    public BorrowDTO(Long borrowID, LocalDate borrowingDate, LocalDate returnDate, Long bookID, Long userID, String bookTitle, String username) {
        this.borrowID = borrowID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.bookID = bookID;
        this.userID = userID;
        this.bookTitle = bookTitle;
        this.username = username;
    }

    /**
     * Default constructor
     */
    public BorrowDTO() {
    }

    /**
     * @return Borrow record ID
     */
    public Long getBorrowID() {
        return borrowID;
    }

    /**
     * @param borrowID Borrow record ID to set
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
     * @return Title of the borrowed book
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * @param bookTitle Book title to set
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     * @return Username of the borrower
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username Username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String representation of BorrowDTO
     */
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