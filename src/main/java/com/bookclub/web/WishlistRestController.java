package com.bookclub.web;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {

    private final WishlistDao wishlistDao;

    @Autowired
    public WishlistRestController(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    // Get all wishlist items
    @GetMapping
    public List<WishlistItem> getAllWishlistItems() {
        return wishlistDao.list();
    }

    // Get a specific wishlist item by ISBN
    @GetMapping("/{isbn}")  // Changed from id to isbn
    public WishlistItem getWishlistItemByIsbn(@PathVariable String isbn) {  // Changed from id to isbn
        return wishlistDao.find(isbn);  // Updated to find by ISBN
    }

 // Add a new wishlist item
    @PostMapping
    public void addWishlistItem(@RequestBody WishlistItem wishlistItem) {
        wishlistDao.add(wishlistItem);
    }

    // Get wishlist items for the authenticated user
    @GetMapping("/wishlist")
    public List<WishlistItem> getWishlist(Authentication authentication) {
        String username = authentication.getName(); // Bind username
        return wishlistDao.list(username); // Pass username to list method
    }
    @PutMapping("/{isbn}")
    public void updateWishlistItem(@PathVariable String isbn, @RequestBody WishlistItem wishlistItem) {
        wishlistDao.update(wishlistItem);
    }


    // Remove a wishlist item by ISBN
    @DeleteMapping("/{isbn}")  // New endpoint for deleting an item by ISBN
    public void deleteWishlistItem(@PathVariable String isbn) {
        wishlistDao.remove(isbn);  // Updated to remove by ISBN
    }
}
