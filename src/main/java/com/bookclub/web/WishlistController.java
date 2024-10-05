package com.bookclub.web;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    private WishlistDao wishlistDao;

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    // Display wishlist
    @GetMapping
    public String showWishlist(Model model) {
        List<WishlistItem> wishlist = wishlistDao.list();
        model.addAttribute("wishlist", wishlist);
        return "wishlist";
    }

    // Add new wishlist item
    @PostMapping
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "wishlist";
        }
        wishlistDao.add(wishlistItem);
        return "redirect:/wishlist";
    }
    @GetMapping("/new")
    public String newWishlistItem(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist";
    }
    @GetMapping("/error")
    public String Error(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist";
    }

}
