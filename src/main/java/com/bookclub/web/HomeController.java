package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.impl.RestBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private RestBookDao restBookDao;  // Autowiring the new RestBookDao

    // Home method to show list of books
    @RequestMapping("/")
    public ModelAndView showHome() {
        String isbnString = "0385472579,0451526538";  // Hardcoded list of ISBNs for now
        ModelAndView modelAndView = new ModelAndView("index");

        // Fetch books from OpenLibrary API
        List<Book> books = restBookDao.getBooksDoc(isbnString);
        modelAndView.addObject("books", books);
        return modelAndView;
    }
    
    // New method for getting a book by its ID (ISBN)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getMonthlyBook(@PathVariable("id") String isbn) {
        ModelAndView model = new ModelAndView("monthly-books/view");
        List<Book> books = restBookDao.getBooksDoc(isbn);

        if (books != null && !books.isEmpty()) {
            Book book = books.get(0);
            model.addObject("book", book);
        } else {
            model.addObject("error", "No book found with ISBN: " + isbn);
        }

        return model;
    }
}
