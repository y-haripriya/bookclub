package com.bookclub.service;

import java.util.List;

import com.bookclub.model.WishlistItem;

public interface WishlistService {
    List<WishlistItem> listAll();
    WishlistItem getByIsbn(String isbn);
    void save(WishlistItem wishlistItem);
    void deleteByIsbn(String isbn);
}
