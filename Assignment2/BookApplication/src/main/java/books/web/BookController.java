package books.web;

import books.domain.Book;
import books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    BookService bookService;
    
    // Add Book
    @PostMapping("/")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        System.out.println("Adding book:"+book);
        bookService.saveBook(book);
        return new ResponseEntity<Book>(book,HttpStatus.OK);
    }
    
    // Update Book
    @PutMapping("/{isbn}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable String isbn) {
        bookService.updateBook(book,isbn);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // Delete Book
    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Get Book
    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = bookService.getBook(isbn);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    
    // Get All Books
    @GetMapping("/")
    public ResponseEntity<Books> getAllBooks() {
        Books allbooks = new Books(bookService.getAllBooks());
        return new ResponseEntity<Books>(allbooks, HttpStatus.OK);
    }

}
