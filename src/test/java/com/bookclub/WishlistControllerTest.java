package com.bookclub;

import com.bookclub.dao.WishlistDao;
import com.bookclub.model.WishlistItem;
import com.bookclub.web.WishlistController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc // This will enable MockMvc to be auto-configured and injected
public class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistDao wishlistDao;

    @Autowired
    private WishlistController wishlistController;

    // Test for viewing the wishlist as a user
    @Test
    @WithMockUser(username = "user", roles = {"USER"}, password = "password")
    public void testShowWishlistAsUser() throws Exception {
        // Create mock wishlist items for the user
        WishlistItem item1 = new WishlistItem("12345", "Item One");
        WishlistItem item2 = new WishlistItem("67890", "Item Two");
        List<WishlistItem> wishlist = Arrays.asList(item1, item2);

        // Mock the behavior of wishlistDao
        when(wishlistDao.list()).thenReturn(wishlist);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/wishlist"))
                .andExpect(status().isOk())  // Check for HTTP 200 OK status
                .andExpect(view().name("wishlist/list"))  // Ensure the correct view is returned
                .andExpect(model().attributeExists("wishlist"))  // Ensure the "wishlist" attribute is present
                .andExpect(model().attribute("wishlist", wishlist));  // Validate the "wishlist" attribute content
    }

    // Test for viewing the wishlist as an admin
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "admin123")
    public void testShowWishlistAsAdmin() throws Exception {
        // Create mock wishlist items for the admin
        WishlistItem item1 = new WishlistItem("12345", "Item One");
        WishlistItem item2 = new WishlistItem("67890", "Item Two");
        List<WishlistItem> wishlist = Arrays.asList(item1, item2);

        // Mock the behavior of wishlistDao
        when(wishlistDao.list()).thenReturn(wishlist);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/wishlist"))
                .andExpect(status().isOk())  // Check for HTTP 200 OK status
                .andExpect(view().name("wishlist/list"))  // Ensure the correct view is returned
                .andExpect(model().attributeExists("wishlist"))  // Ensure the "wishlist" attribute is present
                .andExpect(model().attribute("wishlist", wishlist));  // Validate the "wishlist" attribute content
    }
}
