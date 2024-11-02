package com.bookclub.web;

import com.bookclub.dao.BookOfTheMonthDao;
import com.bookclub.model.BookOfTheMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BookOfTheMonthDao bookOfTheMonthDao;

    // Home method to show a list of books from BookOfTheMonthDao for the homepage
    @GetMapping("/")
    public String showHome(Model model) {
        // Retrieve the list of books from BookOfTheMonthDao
        List<BookOfTheMonth> books = bookOfTheMonthDao.list();
        
        // Add the list of books to the model to display on the homepage
        model.addAttribute("books", books);
        return "index";  // Return the view name (index.html) for the homepage
    }

    // Method to show details of a single book
    @GetMapping("/monthly-books/view/{id}")
    public String getBookDetails(@PathVariable String id, Model model) {
        BookOfTheMonth book = bookOfTheMonthDao.find(id);
        if (book == null) {
            model.addAttribute("error", "Book not found");
            return "redirect:/"; // Redirect to homepage if book not found
        }
        model.addAttribute("book", book);
        return "monthly-books/view"; // Ensure this points to your view.html file
    }
}
