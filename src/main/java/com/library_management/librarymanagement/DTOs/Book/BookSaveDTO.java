package com.library_management.librarymanagement.DTOs.Book;

/**
 * DTO for creating/saving new Book entries
 */
public class BookSaveDTO {
    private String title;
    private Long authorID;
    private String description;
    private Integer quantity;
    private String imageUrl;

    /**
     * Constructs BookSaveDTO with all fields
     * @param title Book's title
     * @param authorID Author's unique identifier
     * @param description Book's description
     * @param quantity Available quantity
     * @param imageUrl URL to book's image
     */
    public BookSaveDTO(String title, Long authorID, String description, Integer quantity, String imageUrl) {
        this.title = title;
        this.authorID = authorID;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    /**
     * Default constructor
     */
    public BookSaveDTO() {
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
     * @return String representation of BookSaveDTO
     */
    @Override
    public String toString() {
        return "BookSaveDTO{" +
                "title='" + title + '\'' +
                ", authorID=" + authorID +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}