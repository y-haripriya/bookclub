package com.bookclub.web;

import com.bookclub.service.WishlistService;
import com.example.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    // Method to display the wishlist
    @GetMapping
    public String showWishlist(Model model) {
        List<WishlistItem> wishlist = wishlistService.listAll();  // Retrieve the current wishlist from service
        model.addAttribute("wishlist", wishlist);  // Add 'wishlist' attribute to the model
        return "wishlist";  // Return the main wishlist page (wishlist.html)
    }

    // Method to show the form for adding or editing a wishlist item
    @GetMapping("/new")
    public String wishlistForm(
        @RequestParam(required = false) String isbn, 
        @RequestParam(required = false) String title, 
        Model model) {

        WishlistItem wishlistItem = new WishlistItem();
        if (isbn != null && title != null) {
            wishlistItem.setIsbn(isbn);
            wishlistItem.setTitle(title);
        }
        model.addAttribute("wishlistItem", wishlistItem);  // Bind the WishlistItem object for the form
        return "wishlist";  // Reuse the same view (wishlist.html) to display the form
    }

    // Method to add or update a wishlist item
    @PostMapping
    public String saveWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("wishlistItem", wishlistItem);  // In case of validation errors, return form with errors
            List<WishlistItem> wishlist = wishlistService.listAll();  // Get the current wishlist to display alongside the form
            model.addAttribute("wishlist", wishlist);  // Add the current wishlist back to the model
            return "wishlist";  // Return the form view with the error messages
        }

        wishlistService.save(wishlistItem);  // Add or update the item in the service
        return "redirect:/wishlist";  // Redirect back to the main wishlist view after successfully adding or updating the item
    }

    // Method to delete a wishlist item
    @GetMapping("/delete/{isbn}")
    public String deleteWishlistItem(@PathVariable String isbn) {
        wishlistService.deleteByIsbn(isbn);  // Delete the item with the matching ISBN from the service
        return "redirect:/wishlist";  // Redirect back to the main wishlist view after deletion
    }
}
