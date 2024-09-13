package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

public class MemBookDao implements BookDao {

    // Private variable to store a list of books
    private List<Book> books;

    // Constructor to add five new books
    public MemBookDao() {
        books = new ArrayList<>();
        books.add(new Book("978-0-123456-47-2", "Book One", "Description One", 300, List.of("Author A")));
        books.add(new Book("978-0-123456-48-9", "Book Two", "Description Two", 350, List.of("Author B")));
        books.add(new Book("978-0-123456-49-6", "Book Three", "Description Three", 400, List.of("Author C")));
        books.add(new Book("978-0-123456-50-2", "Book Four", "Description Four", 280, List.of("Author D")));
        books.add(new Book("978-0-123456-51-9", "Book Five", "Description Five", 320, List.of("Author E")));
    }

    // Override list() method to return the array of books
    @Override
    public List<Book> list() {
        return books;
    }

    // Override find() method to return a book matching the ISBN
    @Override
    public Book find(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null; // Return null if no book is found with the matching ISBN
    }
}
