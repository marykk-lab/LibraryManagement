package com.library_management.librarymanagement.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="borrow")
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrower_id")
    private Long borrowerID;

    private LocalDate borrowingDate;
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Borrower(Long borrowerID, LocalDate borrowingDate, LocalDate returnDate, Book book, User user) {
        this.borrowerID = borrowerID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public Borrower(LocalDate borrowingDate, LocalDate returnDate, Book book, User user) {
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public Borrower() {
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
        return "Borrower{" +
                "borrowerID=" + borrowerID +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
