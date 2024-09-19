package impl; 
import java.util.ArrayList;
import java.util.List;

import com.example.model.WishlistItem;

import dao.WishlistDao;

public class MemWishlistDao implements WishlistDao {

    private List<WishlistItem> wishlist;

    // Constructor that populates the wishlist with some data
    public MemWishlistDao() {
        wishlist = new ArrayList<>();
        wishlist.add(new WishlistItem("1234567890", "Book Title 1"));
        wishlist.add(new WishlistItem("0987654321", "Book Title 2"));
    }

    // Implement the list() method to return the wishlist
    @Override
    public List<WishlistItem> list() {
        return wishlist;
    }

    // Implement the find() method to return a WishlistItem by ISBN
    @Override
    public WishlistItem find(String isbn) {
        for (WishlistItem item : wishlist) {
            if (item.getIsbn().equals(isbn)) {
                return item;
            }
        }
        return null; 
    }
}
