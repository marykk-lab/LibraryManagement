package com.library_management.librarymanagement.DTOs.Author;

public class AuthorSaveDTO {
    private String name;
    private String wikiUrl;
    private String imageUrl;

    public AuthorSaveDTO(String name, String wikiUrl, String imageUrl) {
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imageUrl = imageUrl;
    }

    public AuthorSaveDTO() {
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
