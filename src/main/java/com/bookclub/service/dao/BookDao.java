package com.bookclub.service.dao;

import com.bookclub.model.Book;
import com.bookclub.service.GenericDao;

public interface BookDao extends GenericDao<Book, String> {
    // Additional methods specific to Book can be declared here if needed
}
