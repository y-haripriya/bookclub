package com.bookclub.impl;

import com.bookclub.dao.BookOfTheMonthDao;
import com.bookclub.model.BookOfTheMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(BookOfTheMonth book) {
        mongoTemplate.save(book);
    }

    @Override
    public List<BookOfTheMonth> list() {
        return mongoTemplate.findAll(BookOfTheMonth.class);
    }

    @Override
    public BookOfTheMonth find(String id) {
        return mongoTemplate.findById(id, BookOfTheMonth.class); 
    }

    @Override
    public void update(BookOfTheMonth book) {
        mongoTemplate.save(book);
    }

    @Override
    public void remove(String id) {
        Query query = new Query(Criteria.where("_id").is(id)); 
        mongoTemplate.remove(query, BookOfTheMonth.class);
    }

    @Override
    public BookOfTheMonth findByMonth(int month) {
        Query query = new Query(Criteria.where("month").is(month));
        return mongoTemplate.findOne(query, BookOfTheMonth.class);
    }

    @Override
    public BookOfTheMonth findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id)); // Ensure consistent usage
        return mongoTemplate.findOne(query, BookOfTheMonth.class);
    }
}
