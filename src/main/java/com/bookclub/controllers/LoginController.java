package com.bookclub.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, 
                        @RequestParam("password") String password) {
        // Authentication logic
        if (authenticate(username, password)) {
            return "redirect:/dashboard";  // Redirect on success
        } else {
            return "redirect:/login?error";  // Redirect on failure
        }
    }

    private boolean authenticate(String username, String password) {
        // Placeholder authentication logic
        return "admin".equals(username) && "admin".equals(password);
    }
}
