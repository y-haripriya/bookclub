package com.bookclub.service;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl {

    private final WishlistDao wishlistDao;

    public WishlistServiceImpl(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao; 
    }

    public List<WishlistItem> getWishlist() {
        return wishlistDao.list();
    }

    public void addWishlistItem(WishlistItem wishlistItem) {
        wishlistDao.add(wishlistItem);
    }

    public void updateWishlistItem(WishlistItem wishlistItem) {
        wishlistDao.update(wishlistItem);
    }

    public boolean removeWishlistItem(WishlistItem wishlistItem) {
        return wishlistDao.remove(wishlistItem);
    }

    public WishlistItem findWishlistItem(String id) {
        return wishlistDao.find(id);
    }
}
