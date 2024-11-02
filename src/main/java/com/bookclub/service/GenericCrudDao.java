package com.bookclub.service;

import java.util.List;

import com.bookclub.model.WishlistItem;

public interface GenericCrudDao<E, K> {
    void add(E entity);
    boolean remove(E entity);
    List<E> list();
    E find(K key);
    void update(E entity);
	List<WishlistItem> list(String username);
	void remove(String isbn);
}
