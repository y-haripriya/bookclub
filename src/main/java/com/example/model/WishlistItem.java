package com.example.model; 

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class WishlistItem {

    // Private properties with validation annotations
    @NotNull(message = "ISBN is a required field.")
    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    @NotNull(message = "Title is a required field.")
    @NotEmpty(message = "Title is a required field.")
    private String title;

    // Default constructor
    public WishlistItem() {
    }

    // Parameterized constructor
    public WishlistItem(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    // Getter and setter methods for isbn
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getter and setter methods for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Override toString method
    @Override
    public String toString() {
        return "WishlistItem{isbn=" + isbn + ", title=" + title + "}";
    }
}
