package com.bookclub.dao;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.GenericCrudDao;

import jakarta.validation.Valid;

public interface WishlistDao extends GenericCrudDao<WishlistItem, String> {

	void save(@Valid WishlistItem wishlistItem);
}
