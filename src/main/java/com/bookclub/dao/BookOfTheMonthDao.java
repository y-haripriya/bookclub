package com.bookclub.dao;

import com.bookclub.model.BookOfTheMonth;
import java.util.List;

public interface BookOfTheMonthDao {
    void add(BookOfTheMonth book);
    void remove(String id);
    List<BookOfTheMonth> list();
    BookOfTheMonth find(String id);
    void update(BookOfTheMonth book);
	BookOfTheMonth findByMonth(int month);
	BookOfTheMonth findById(String id);
}
