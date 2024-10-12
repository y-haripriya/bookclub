package com.bookclub.web;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {

    private WishlistDao wishlistDao;

    @Autowired
    public WishlistRestController(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    // Get all wishlist items
    @GetMapping
    public List<WishlistItem> getAllWishlistItems() {
        return wishlistDao.list();
    }

    // Get a specific wishlist item by ID
    @GetMapping("/{id}")
    public WishlistItem getWishlistItemById(@PathVariable String id) {
        return wishlistDao.find(id);
    }

    // Add a new wishlist item
    @PostMapping
    public void addWishlistItem(@RequestBody WishlistItem wishlistItem) {
        wishlistDao.add(wishlistItem);
    }
}
