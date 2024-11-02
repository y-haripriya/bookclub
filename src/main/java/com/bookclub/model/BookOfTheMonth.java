package com.bookclub.model;

import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookOfTheMonth {

    @Id
    private String id;

    @NotNull(message = "Month is a required field.")
    private Integer month;

    @NotNull(message = "ISBN is a required field.")
    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    @NotNull(message = "Title is a required field.")
    @NotEmpty(message = "Title is a required field.")
    private String title;

    private String description;

    @NotNull(message = "Pages is a required field.")
    private Integer pages;

    // Default constructor
    public BookOfTheMonth() {
    }

    // Parameterized constructor
    public BookOfTheMonth(String isbn, Integer month, String title, String description, Integer pages) {
        this.isbn = isbn;
        this.month = month;
        this.title = title;
        this.description = description;
        this.pages = pages;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    // Override toString method
    @Override
    public String toString() {
        return "BookOfTheMonth{" +
                "id='" + id + '\'' +
                ", month=" + month +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", pages=" + pages +
                '}';
    }
}
