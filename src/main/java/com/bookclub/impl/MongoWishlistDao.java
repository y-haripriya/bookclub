package com.bookclub.impl;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
    public WishlistItem find(String isbn) {
        Query query = new Query(Criteria.where("isbn").is(isbn));
        return mongoTemplate.findOne(query, WishlistItem.class);
    }

    @Override
    public List<WishlistItem> list(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.find(query, WishlistItem.class);
    }

    @Override
    public void update(WishlistItem wishlistItem) {
        Query query = new Query(Criteria.where("isbn").is(wishlistItem.getIsbn()));
        Update update = new Update();
        update.set("title", wishlistItem.getTitle());
        update.set("author", wishlistItem.getAuthor());
        update.set("username", wishlistItem.getUsername());
        mongoTemplate.updateFirst(query, update, WishlistItem.class);
    }

    @Override
    public void remove(String isbn) {
        Query query = new Query(Criteria.where("isbn").is(isbn));
        mongoTemplate.remove(query, WishlistItem.class);
    }

    @Override
    public boolean remove(WishlistItem entity) {
        // Use the ISBN from the entity to remove it
        if (entity != null && entity.getIsbn() != null) {
            remove(entity.getIsbn());
            return true;
        }
        return false;
    }

    @Override
    public void save(@Valid WishlistItem wishlistItem) {
        // If the item exists, update it; otherwise, add a new entry
        Query query = new Query(Criteria.where("isbn").is(wishlistItem.getIsbn()));
        if (mongoTemplate.exists(query, WishlistItem.class)) {
            update(wishlistItem);
        } else {
            add(wishlistItem);
        }
    }
}
