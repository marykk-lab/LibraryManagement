package com.library_management.librarymanagement.DTOs.Author;

public class AuthorDTO {
    private Long authorID;
    private String name;
    private String wikiUrl;
    private String imageUrl;

    public AuthorDTO(Long authorID, String name, String wikiUrl, String imageUrl) {
        this.authorID = authorID;
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imageUrl = imageUrl;
    }


    public AuthorDTO() {
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
