package com.library_management.librarymanagement.DTOs.Author;

/**
 * DTO for updating existing Author entries
 */
public class AuthorUpdateDTO {
    private Long authorID;
    private String name;
    private String wikiUrl;
    private String imageUrl;

    /**
     * Constructs AuthorUpdateDTO with all fields
     * @param authorID Author's unique identifier
     * @param name Author's name
     * @param wikiUrl URL to author's wiki page
     * @param imageUrl URL to author's image
     */
    public AuthorUpdateDTO(Long authorID, String name, String wikiUrl, String imageUrl) {
        this.authorID = authorID;
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imageUrl = imageUrl;
    }

    /**
     * Constructs AuthorUpdateDTO without ID
     * @param name Author's name
     * @param wikiUrl URL to author's wiki page
     * @param imageUrl URL to author's image
     */
    public AuthorUpdateDTO(String name, String wikiUrl, String imageUrl) {
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imageUrl = imageUrl;
    }

    /**
     * Constructs AuthorUpdateDTO with name only
     * @param name Author's name
     */
    public AuthorUpdateDTO(String name) {
        this.name = name;
    }

    /**
     * Default constructor
     */
    public AuthorUpdateDTO() {
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
     * @return Author's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Author's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return URL to author's wiki page
     */
    public String getWikiUrl() {
        return wikiUrl;
    }

    /**
     * @param wikiUrl Wiki URL to set
     */
    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    /**
     * @return URL to author's image
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
}