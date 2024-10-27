package books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {
	@Autowired
	private RestOperations restTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(RestClientApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String serverUrl = "http://localhost:8080/books/";
		
		// Add Books
		restTemplate.postForLocation(serverUrl, new Book("Java", "Browns", "ISBN-1001", "50.00"));
		restTemplate.postForLocation(serverUrl, new Book("C++", "Doe", "ISBN-1002", "35.00"));
		
		// Get Book by ISBN
		Book book = restTemplate.getForObject(serverUrl + "{isbn}", Book.class, "ISBN-1002");
		System.out.println("----------- Get Book -----------------------");
		System.out.println(book);
		
		// Update Book Title
        book.setTitle("Python");
		restTemplate.put(serverUrl + "{isbn}", book, "ISBN-1002");
		System.out.println("----------- Update Book title-----------------------");
		System.out.println(book);
		
		// Get All Books
		Books allbooks= restTemplate.getForObject(serverUrl, Books.class);
		System.out.println("----------- get all books-----------------------");
		System.out.println(allbooks);
		
		// Delete Book
		restTemplate.delete(serverUrl + "{isbn}", "ISBN-1002");
		System.out.println("----------- Delete Book -----------------------");
		System.out.println("Book with ISBN-1002 is deleted");
		
		// Get All Books
		allbooks= restTemplate.getForObject(serverUrl, Books.class);
		System.out.println("----------- all books after deletion-----------------------");
		System.out.println(allbooks);
		
	}
	
	@Bean
	RestOperations restTemplate() {
		return new RestTemplate();
	}
}
