package com.library_management.librarymanagement.DTOs.Author;

/**
 * DTO for creating/saving new Author entries
 */
public class AuthorSaveDTO {
    private String name;
    private String wikiUrl;
    private String imageUrl;

    /**
     * Constructs AuthorSaveDTO with all fields
     * @param name Author's name
     * @param wikiUrl URL to author's wiki page
     * @param imageUrl URL to author's image
     */
    public AuthorSaveDTO(String name, String wikiUrl, String imageUrl) {
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imageUrl = imageUrl;
    }

    /**
     * Default constructor
     */
    public AuthorSaveDTO() {
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