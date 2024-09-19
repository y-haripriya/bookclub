package com.bookclub.service.impl;

import com.bookclub.service.WishlistService;
import com.example.model.WishlistItem;
import impl.MemWishlistDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final MemWishlistDao wishlistDao = new MemWishlistDao(); // DAO initialization

    @Override
    public List<WishlistItem> listAll() {
        return wishlistDao.list();
    }

    @Override
    public WishlistItem getByIsbn(String isbn) {
        return wishlistDao.list().stream()
                .filter(item -> item.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(WishlistItem wishlistItem) {
        List<WishlistItem> wishlist = wishlistDao.list();
        if (wishlist.contains(wishlistItem)) {
            wishlist.remove(wishlistItem);
        }
        wishlist.add(wishlistItem);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        List<WishlistItem> wishlist = wishlistDao.list();
        wishlist.removeIf(item -> item.getIsbn().equals(isbn));
    }
}
