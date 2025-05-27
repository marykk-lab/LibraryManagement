package com.library_management.librarymanagement.Entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookID;

    @Column(name = "book_title", length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private String description;
    private Integer quantity;
    private String imageUrl;

    @OneToMany(mappedBy = "book")
    private Set<Borrow> borrows = new HashSet<>();


    public Book(Long bookID, String title, Author author, String description, int quantity, String imageUrl) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public Book(String title, Author author, String description, Integer quantity, String imageUrl) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public Book() {
    }

    public boolean addBorrow(Borrow borrow){
        if (borrow!=null) {
            borrows.add(borrow);
            return true;
        }
        return false;
    }

    public boolean minusBook(){
        quantity--;
        return true;
    }

    public boolean plusBook(){
        quantity++;
        return true;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Borrow> getBorrows() {
        return borrows;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", borrows=" + borrows +
                '}';
    }
}
