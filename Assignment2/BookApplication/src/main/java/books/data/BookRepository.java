package books.data;


import books.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {
    
    private final Map<String, Book> books =new HashMap<>();
    
    public Book saveBook(Book book) {
        return books.put(book.getIsbn(), book);
    }
    
    public Book updateBook(Book book, String isbn) {
        return books.put(isbn, book);
    }
    
    public void deleteBook(String isbn) {
        books.remove(isbn);
    }
    
    public Book getBook(String isbn) {
        return books.get(isbn);
    }
    
    public Collection<Book> getAllBooks() {
        return books.values();
    }
   
}
