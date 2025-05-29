
package com.library_management.librarymanagement.DTOs.Book;

/**
 * DTO for transferring book update data.
 */
public class BookUpdateDTO {
    private Long bookID;
    private String title;
    private Long authorID;
    private String description;
    private Integer quantity;
    private String imageUrl;

    /**
     * @param bookID book's ID
     * @param title book's title
     * @param authorID author's ID
     * @param description book's description
     * @param quantity available quantity
     * @param imageUrl URL of book's image
     */
    public BookUpdateDTO(Long bookID, String title, Long authorID, String description, Integer quantity, String imageUrl) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    /**
     * @param title book's title
     * @param authorID author's ID
     * @param description book's description
     * @param quantity available quantity
     * @param imageUrl URL of book's image
     */
    public BookUpdateDTO(String title, Long authorID, String description, Integer quantity, String imageUrl) {
        this.title = title;
        this.authorID = authorID;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    /**
     * Default constructor
     */
    public BookUpdateDTO() {
    }

    /**
     * @return book ID
     */
    public Long getBookID() {
        return bookID;
    }

    /**
     * @param bookID book ID to set
     */
    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    /**
     * @return book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title book title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return author ID
     */
    public Long getAuthorID() {
        return authorID;
    }

    /**
     * @param authorID author ID to set
     */
    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    /**
     * @return book description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description book description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return book quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity book quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl image URL to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "BookUpdateDTO{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", authorID=" + authorID +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}