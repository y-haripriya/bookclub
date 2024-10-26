package com.bookclub.web;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistDao wishlistDao;

    @Autowired
    public WishlistController(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    // Method to display the wishlist
    @GetMapping
    public String showWishlist(Model model) {
        List<WishlistItem> wishlist = wishlistDao.list();  // Retrieve wishlist items
        model.addAttribute("wishlist", wishlist);  // Add items to the model
        return "wishlist/list";  // Render wishlist page (list.html)
    }

    // Method to show the form for adding or editing a wishlist item
    @GetMapping("/{isbn}")
    public String editWishlistItem(@PathVariable String isbn, Model model) {
        WishlistItem wishlistItem = wishlistDao.find(isbn);  // Fetch the item by ISBN
        if (wishlistItem == null) {
            // Handle the case where no item is found by ISBN
            return "redirect:/wishlist?error=ItemNotFound";
        }
        model.addAttribute("wishlistItem", wishlistItem);  // Bind the item to the form
        return "wishlist/view";  // Return the view (view.html) for editing
    }

 // Method to add or update a wishlist item
    @PostMapping
    public String saveWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("wishlistItem", wishlistItem);  // In case of validation errors, return form with errors
            List<WishlistItem> wishlist = wishlistDao.list();  // Get the current wishlist to display alongside the form
            model.addAttribute("wishlist", wishlist);  // Add the current wishlist back to the model
            return "wishlist/view";  // Return the form view with the error messages
        }

        wishlistDao.save(wishlistItem);  // Add or update the item in the service
        return "redirect:/wishlist";  // Redirect back to the main wishlist view after successfully adding or updating the item
    }
    

    // Method to delete a wishlist item by ISBN
    @GetMapping("/delete/{isbn}")
    public String deleteWishlistItem(@PathVariable String isbn) {
        wishlistDao.remove(isbn);  // Remove the item by ISBN
        return "redirect:/wishlist";  // Redirect to the main wishlist page
    }
}
