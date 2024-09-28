package com.bookclub.model;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class WishlistItem {

    @Id
    private String id;

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

    // Getter and setter methods for id, isbn, and title
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "WishlistItem{id=" + id + ", isbn='" + isbn + "', title='" + title + "'}";
    }
}
