package com.library_management.librarymanagement.DTOs.Book;

/**
 * Data Transfer Object for Book information
 */
public class BookDTO {
    private Long bookID;
    private String title;
    private Long authorID;
    private String description;
    private Integer quantity;
    private String imageUrl;

    /**
     * Constructs BookDTO with all fields
     * @param bookID Book's unique identifier
     * @param title Book's title
     * @param authorID Author's unique identifier
     * @param description Book's description
     * @param quantity Available quantity
     * @param imageUrl URL to book's image
     */
    public BookDTO(Long bookID, String title, Long authorID, String description, Integer quantity, String imageUrl) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    /**
     * Constructs BookDTO without ID
     * @param title Book's title
     * @param authorID Author's unique identifier
     * @param description Book's description
     * @param quantity Available quantity
     * @param imageUrl URL to book's image
     */
    public BookDTO(String title, Long authorID, String description, Integer quantity, String imageUrl) {
        this.title = title;
        this.authorID = authorID;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    /**
     * Default constructor
     */
    public BookDTO() {
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
     * @return Author's ID
     */
    public Long getAuthorID() {
        return authorID;
    }

    /**
     * @param authorID Author's ID to set
     */
    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    /**
     * @return Book's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Book's description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Available quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity Quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return URL to book's image
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl Image URL to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return String representation of BookDTO
     */
    @Override
    public String toString() {
        return "BookDTO{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", authorID=" + authorID +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}