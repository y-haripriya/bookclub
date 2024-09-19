package com.bookclub.service;

import com.example.model.WishlistItem;

import java.util.List;

public interface WishlistService {
    List<WishlistItem> listAll();
    WishlistItem getByIsbn(String isbn);
    void save(WishlistItem wishlistItem);
    void deleteByIsbn(String isbn);
}
