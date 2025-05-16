package com.library_management.librarymanagement.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    public Borrow(Long borrowID, LocalDate borrowingDate, LocalDate returnDate, Book book, User user) {
        this.borrowID = borrowID;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public Borrow(LocalDate borrowingDate, LocalDate returnDate, Book book, User user) {
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public Borrow() {
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
        return "Borrow{" +
                "borrowID=" + borrowID +
                ", borrowingDate=" + borrowingDate +
                ", returnDate=" + returnDate +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
