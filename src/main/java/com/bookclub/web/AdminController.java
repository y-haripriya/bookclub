package com.bookclub.web;

import com.bookclub.dao.BookOfTheMonthDao;
import com.bookclub.model.BookOfTheMonth;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/monthly-books")
public class AdminController {

    @Autowired
    private BookOfTheMonthDao bookOfTheMonthDao;

    @GetMapping
    public String showBookOfTheMonth(Model model) {
        model.addAttribute("books", bookOfTheMonthDao.list());
        return "monthly-books/list";
    }

    @GetMapping("/new")
    public String bookOfTheMonthForm(Model model) {
        model.addAttribute("months", getMonths());
        model.addAttribute("bookOfTheMonth", new BookOfTheMonth());
        return "monthly-books/new";
    }

    @PostMapping("/add")
    public String addBookOfTheMonth(@Valid BookOfTheMonth bookOfTheMonth, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("months", getMonths());
            return "monthly-books/new";
        }
        bookOfTheMonthDao.add(bookOfTheMonth);
        return "redirect:/monthly-books";
    }

    @GetMapping("/remove/{id}")
    public String removeBookOfTheMonth(@PathVariable String id) {
        bookOfTheMonthDao.remove(id);
        return "redirect:/monthly-books";
    }
 // Method to show details of a single book
    @GetMapping("/monthly-books/view/{id}")
    public String getBookDetails(@PathVariable String id, Model model) {
        BookOfTheMonth book = bookOfTheMonthDao.find(id);
        model.addAttribute("book", book);
        return "monthly-books/view"; // This should point to your view.html file
    }

    private Map<Integer, String> getMonths() {
        Map<Integer, String> months = new HashMap<>();
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");
        return months;
    }
}
