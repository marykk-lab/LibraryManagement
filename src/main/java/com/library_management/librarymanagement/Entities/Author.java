package com.library_management.librarymanagement.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity class representing an author in the library management system.
 * Maps to the "author" table in the database.
 */
@Entity
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorID;

    @Column(name = "author_name", length = 40)
    private String name;

    private String wikiUrl;
    private String imageUrl;

    @OneToMany(mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    /**
     * Constructs a new Author with specified ID and details.
     *
     * @param authorID The unique identifier for the author
     * @param name The name of the author
     * @param wikiUrl The URL to the author's Wikipedia page
     * @param imageUrl The URL to the author's image
     */
    public Author(Long authorID, String name, String wikiUrl, String imageUrl) {
        this.authorID = authorID;
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imageUrl = imageUrl;
    }

    /**
     * Constructs a new Author with specified details (without ID).
     *
     * @param name The name of the author
     * @param wikiUrl The URL to the author's Wikipedia page
     * @param imageUrl The URL to the author's image
     */
    public Author(String name, String wikiUrl, String imageUrl) {
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imageUrl = imageUrl;
    }

    /**
     * Adds a book to the author's collection of books.
     *
     * @param book The book to be added
     * @return true if the book was successfully added, false if the book was null
     */
    public boolean addBook(Book book){
        if (book != null){
            books.add(book);
            return true;
        }
        return false;
    }

    /**
     * Default constructor required by JPA.
     */
    public Author() {
    }

    /**
     * Gets the author's name.
     *
     * @return The name of the author
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the author's name.
     *
     * @param name The new name for the author
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the author's ID.
     *
     * @return The unique identifier of the author
     */
    public Long getAuthorID() {
        return authorID;
    }

    /**
     * Sets the author's ID.
     *
     * @param authorID The new ID for the author
     */
    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    /**
     * Gets the set of books written by the author.
     *
     * @return A set of Book objects associated with this author
     */
    public Set<Book> getBooks() {
        return books;
    }

    /**
     * Sets the collection of books for this author.
     *
     * @param books The new set of books for this author
     */
    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    /**
     * Gets the URL to the author's Wikipedia page.
     *
     * @return The Wikipedia URL for this author
     */
    public String getWikiUrl() {
        return wikiUrl;
    }

    /**
     * Sets the URL to the author's Wikipedia page.
     *
     * @param wikiUrl The new Wikipedia URL for this author
     */
    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    /**
     * Gets the URL to the author's image.
     *
     * @return The image URL for this author
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the URL to the author's image.
     *
     * @param imageUrl The new image URL for this author
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Returns a string representation of the Author object.
     *
     * @return A string containing the author's name and ID
     */
    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", authorID=" + authorID +
                '}';
    }
}