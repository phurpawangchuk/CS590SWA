package books.service;

import books.data.BookRepository;
import books.domain.Book;
import books.integration.JmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    JmsSender jsmSender;
    
    public void saveBook(Book book) {
        System.out.println("Saving book: " + book);
        jsmSender.sendMessage(book);
        bookRepository.saveBook(book);
    }
    
    public void updateBook(Book book, String isbn) {
        System.out.println("Updating book");
        Book bookToUpdate = bookRepository.getBook(isbn);
        if(bookToUpdate == null) {
            return;
        }
        
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setPrice(book.getPrice());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setIsbn(isbn);
        
        jsmSender.sendMessage(bookToUpdate);
        bookRepository.updateBook(bookToUpdate, isbn);
        
    }
    
    public void deleteBook(String isbn) {
        System.out.println("Deleting book");
        jsmSender.sendMessage(bookRepository.getBook(isbn));
        bookRepository.deleteBook(isbn);
    }
    
    public Book getBook(String isbn) {
        System.out.println("Getting book");
        return bookRepository.getBook(isbn);
    }
    
    public Collection<Book> getAllBooks() {
        System.out.println("Getting all books");
        return bookRepository.getAllBooks();
    }
    
}
