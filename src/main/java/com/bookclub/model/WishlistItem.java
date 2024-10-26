package com.bookclub.model; 

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

	private String id;

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
        return "WishlistItem{id=" + id + ", isbn='" + isbn + "', title='" + title + "'}";
    }

	public Object getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setUsername(String name) {
		// TODO Auto-generated method stub
		
	}
}
