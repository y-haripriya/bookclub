package com.bookclub.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView showHome() {
        return new ModelAndView("index");
    }

    @RequestMapping("/about")
    public ModelAndView showAboutUs() {
        return new ModelAndView("about");
    }

    @RequestMapping("/contact")
    public ModelAndView showContactUs() {
        return new ModelAndView("contact");
    }
}
