package com.bookclub;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bookclub.dao.BookOfTheMonthDao;
import com.bookclub.model.BookOfTheMonth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookOfTheMonthDaoTest {

    @Autowired
    private BookOfTheMonthDao bookOfTheMonthDao;

    @MockBean
    private BookOfTheMonthDao mockDao;

    @Test
    public void listBooksTest() {
        // Arrange
        BookOfTheMonth book1 = new BookOfTheMonth("12345", 1, "Book One", "Description One", 300);
        BookOfTheMonth book2 = new BookOfTheMonth("67890", 2, "Book Two", "Description Two", 200);
        when(mockDao.list()).thenReturn(Stream.of(book1, book2).collect(Collectors.toList()));
        
        // Act
        List<BookOfTheMonth> books = mockDao.list();
        
        // Assert
        assertEquals(2, books.size());
    }

    @Test
    public void findBookByIdTest() {
        // Arrange
        String id = "1";
        BookOfTheMonth book = new BookOfTheMonth("12345", 1, "Book One", "Description One", 300);
        when(mockDao.find(id)).thenReturn(book);
        
        // Act
        BookOfTheMonth result = mockDao.find(id);
        
        // Assert
        assertEquals("Book One", result.getTitle());
    }

    @Test
    public void addBookTest() {
        // Arrange
        BookOfTheMonth book = new BookOfTheMonth("54321", 3, "Book Three", "Description Three", 400);
        
        // Act
        doNothing().when(mockDao).add(book);  // Setup mock to do nothing on 'add'
        bookOfTheMonthDao.add(book);

        // Assert
        verify(mockDao, times(1)).add(book);
    }


    @Test
    public void deleteBookTest() {
        // Arrange
        String id = "1";
        
        // Act
        bookOfTheMonthDao.remove(id);

        // Assert
        verify(mockDao, times(1)).remove(id);
    }
}
