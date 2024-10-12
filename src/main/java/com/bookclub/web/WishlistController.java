package com.bookclub.web;

import com.bookclub.dao.WishlistDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistDao wishlistDao;

    public WishlistController(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @GetMapping
    public String showWishlist(Model model) {
        return "wishlist/list";
    }
}
