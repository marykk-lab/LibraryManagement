package com.library_management.librarymanagement.Entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing a book in the library management system.
 * Maps to the "book" table in the database.
 */
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

    /**
     * Constructs a new Book with all attributes specified.
     *
     * @param bookID The unique identifier for the book
     * @param title The title of the book
     * @param author The author of the book
     * @param description The book's description
     * @param quantity The number of copies available
     * @param imageUrl The URL to the book's cover image
     */
    public Book(Long bookID, String title, Author author, String description, int quantity, String imageUrl) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    /**
     * Constructs a new Book without specifying an ID.
     *
     * @param title The title of the book
     * @param author The author of the book
     * @param description The book's description
     * @param quantity The number of copies available
     * @param imageUrl The URL to the book's cover image
     */
    public Book(String title, Author author, String description, Integer quantity, String imageUrl) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    /**
     * Default constructor required by JPA.
     */
    public Book() {
    }

    /**
     * Adds a borrow record to this book's borrow history.
     *
     * @param borrow The borrow record to add
     * @return true if the borrow was successfully added, false if the borrow was null
     */
    public boolean addBorrow(Borrow borrow){
        if (borrow!=null) {
            borrows.add(borrow);
            return true;
        }
        return false;
    }

    /**
     * Decrements the quantity of available books by one.
     *
     * @return true after successful decrement
     */
    public boolean minusBook(){
        quantity--;
        return true;
    }

    /**
     * Increments the quantity of available books by one.
     *
     * @return true after successful increment
     */
    public boolean plusBook(){
        quantity++;
        return true;
    }

    /**
     * Gets the book's ID.
     *
     * @return The unique identifier of the book
     */
    public Long getBookID() {
        return bookID;
    }

    /**
     * Sets the book's ID.
     *
     * @param bookID The new ID for the book
     */
    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    /**
     * Gets the book's title.
     *
     * @return The title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the book's title.
     *
     * @param title The new title for the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the book's author.
     *
     * @return The Author entity associated with this book
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Sets the book's author.
     *
     * @param author The new Author entity for this book
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Gets the book's description.
     *
     * @return The description of the book
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the book's description.
     *
     * @param description The new description for the book
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the quantity of available copies.
     *
     * @return The number of available copies
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of available copies.
     *
     * @param quantity The new quantity of books
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the URL of the book's cover image.
     *
     * @return The image URL for this book
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the URL of the book's cover image.
     *
     * @param imageUrl The new image URL for this book
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Gets the set of borrow records for this book.
     *
     * @return A set of Borrow entities associated with this book
     */
    public Set<Borrow> getBorrows() {
        return borrows;
    }

    /**
     * Returns a string representation of the Book object.
     *
     * @return A string containing the book's ID, title, author, and borrow records
     */
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