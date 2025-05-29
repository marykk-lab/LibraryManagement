package com.library_management.librarymanagement.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity class representing a book borrowing record in the library management system.
 * Maps to the "borrow" table in the database.
 */
@Entity
@Table(name="borrow")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private Long borrowID;

    private LocalDate borrowingDate;
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Constructs a new Borrow record with all attributes specified.
     *
     * @param borrowID The unique identifier for the borrow record
     * @param borrowingDate The date when the book was borrowed
     * @param returnDate The expected return date for the book
     * @param book The book that was borrowed
     * @param user The user who borrowed the book
     */
    public Borrow(Long borrowID, LocalDate borrowingDate, LocalDate returnDate, Book book, User user) {
        this.borrowID = borrowID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    /**
     * Constructs a new Borrow record without specifying an ID.
     *
     * @param borrowingDate The date when the book was borrowed
     * @param returnDate The expected return date for the book
     * @param book The book that was borrowed
     * @param user The user who borrowed the book
     */
    public Borrow(LocalDate borrowingDate, LocalDate returnDate, Book book, User user) {
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    /**
     * Default constructor required by JPA.
     */
    public Borrow() {
    }

    /**
     * Gets the borrow record's ID.
     *
     * @return The unique identifier of the borrow record
     */
    public Long getBorrowID() {
        return borrowID;
    }

    /**
     * Sets the borrow record's ID.
     *
     * @param borrowID The new ID for the borrow record
     */
    public void setBorrowID(Long borrowID) {
        this.borrowID = borrowID;
    }

    /**
     * Gets the date when the book was borrowed.
     *
     * @return The borrowing date
     */
    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    /**
     * Sets the date when the book was borrowed.
     *
     * @param borrowingDate The new borrowing date
     */
    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    /**
     * Gets the expected return date for the book.
     *
     * @return The return date
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets the expected return date for the book.
     *
     * @param returnDate The new return date
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Gets the borrowed book.
     *
     * @return The Book entity that was borrowed
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the borrowed book.
     *
     * @param book The Book entity to be associated with this borrow record
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Gets the user who borrowed the book.
     *
     * @return The User entity who borrowed the book
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who borrowed the book.
     *
     * @param user The User entity to be associated with this borrow record
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns a string representation of the Borrow object.
     *
     * @return A string containing the borrow record's ID, dates, book, and user information
     */
    @Override
    public String toString() {
        return "Borrow{" +
                "borrowID=" + borrowID +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}