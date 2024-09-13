package com.bookclub.service;

import java.util.List;

public interface GenericDao<E, K> {
    
    // Method to list all entities
    List<E> list();
    
    // Method to find an entity by key
    E find(K key);
}
