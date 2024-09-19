package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.impl.MemBookDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    // Existing methods

    @RequestMapping("/")
    public ModelAndView showHome() {
        MemBookDao booksDao = new MemBookDao();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("books", booksDao.list());
        return modelAndView;
    }

    @RequestMapping("/about")
    public ModelAndView showAboutUs() {
        return new ModelAndView("about");
    }

    @RequestMapping("/contact")
    public ModelAndView showContactUs() {
        return new ModelAndView("contact");
    }

    @RequestMapping("/wishlist")
    public ModelAndView showWishlist() {
        return new ModelAndView("wishlist");
    }

    // New method for getting a book by its ID (isbn)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getMonthlyBook(@PathVariable("id") String isbn, ModelAndView model) {
        // Create a new instance of MemBookDao
        MemBookDao booksDao = new MemBookDao();

        // Call the find() method to get the book by isbn
        Book book = booksDao.find(isbn);

        // Assign the book to the model attribute with a key of "book"
        model.addObject("book", book);

        // Return the "monthly-books/view" HTML page
        model.setViewName("monthly-books/view");

        return model;
    }
}
