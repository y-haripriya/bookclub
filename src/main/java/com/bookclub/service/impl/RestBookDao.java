package com.bookclub.service.impl;

import com.bookclub.model.Book;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestBookDao {

    private static final String OPENLIBRARY_API_URL = "https://openlibrary.org/api/books";
    
    public List<Book> getBooksDoc(String isbnString) {
        RestTemplate rest = new RestTemplate();
        List<Book> books = new ArrayList<>();

        try {
            // Split ISBNs into an array
            String[] isbnArray = isbnString.split(",");

            // Create an appropriate query param value (e.g., ISBN:0385472579,ISBN:0451526538)
            StringBuilder isbnParams = new StringBuilder();
            for (String isbn : isbnArray) {
                if (isbnParams.length() > 0) {
                    isbnParams.append(",");
                }
                isbnParams.append("ISBN:").append(isbn);
            }

            // Construct the URL for the OpenLibrary API with multiple ISBNs
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(OPENLIBRARY_API_URL)
                    .queryParam("bibkeys", isbnParams.toString())
                    .queryParam("format", "json")
                    .queryParam("jscmd", "data");

            // Set up headers
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            // Create HttpEntity with headers
            HttpEntity<?> entity = new HttpEntity<>(headers);

            // Make the GET request
            ResponseEntity<String> response = rest.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            // Parse the JSON response
            String jsonBookList = response.getBody();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonBookList);

            // Iterate over the ISBNs and extract book information for each one
            for (String isbn : isbnArray) {
                if (rootNode.has("ISBN:" + isbn)) {
                    JsonNode bookNode = rootNode.path("ISBN:" + isbn);

                    // Map the JSON data to a Book object
                    Book book = new Book(
                        bookNode.path("identifiers").path("isbn_13").get(0).asText("N/A"),
                        bookNode.path("title").asText("N/A"),
                        bookNode.path("notes").asText("N/A"),
                        bookNode.path("url").asText("N/A"),
                        bookNode.path("number_of_pages").asInt(0)
                    );

                    books.add(book);
                }
            }

        } catch (Exception e) {
            // Log the error and load mock data if the API request fails
            System.out.println("Error fetching books from OpenLibrary API: " + e.getMessage());
            books = loadMockBooks();
        }

        return books;
    }

    // Example mock data method
    private List<Book> loadMockBooks() {
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book("0385472579", "The Book Thief", "A story about a girl during World War II", "https://example.com/book1", 584));
        mockBooks.add(new Book("0451526538", "1984", "Dystopian novel", "https://example.com/book2", 328));
        return mockBooks;
    }
}
