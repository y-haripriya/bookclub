package com.bookclub.service;

import java.util.List;

import com.bookclub.model.WishlistItem;

import jakarta.validation.Valid;

public interface GenericCrudDao<E, K> {
    void add(E entity);
    boolean remove(E entity);
    List<E> list();
    E find(K key);
    List<WishlistItem> list(String username);
    void remove(String isbn);  // Updated to use ISBN
    void update(WishlistItem item);
}
