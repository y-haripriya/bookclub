package com.bookclub.impl;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<WishlistItem> list() {
        return mongoTemplate.findAll(WishlistItem.class);
    }

    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void update(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public boolean remove(WishlistItem entity) {
        return mongoTemplate.remove(entity).wasAcknowledged();
    }

    @Override
    public WishlistItem find(String key) {
        return mongoTemplate.findById(key, WishlistItem.class);
    }
}
