package ca.mcgill.ecse321.projectgroup09.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.BookDto;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.service.BookService;

/**
 * Controller for Book Service.
 */
// Enable the Cross-Origin Resource Sharing for any domain using the @CrossOrigin annotation on the REST controller class.
// https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS
@CrossOrigin(origins = "*")
@RestController
public class BookController {
	
	private static final String BASE_URL = "/books";
	
	@Autowired
	private BookService bookService;
	
	// ==== HTTP ENDPOINTS ==== //
	
	/**
	 * Return list of all books in library management system.
	 * Base endpoint of /books
	 * @return
	 */
	@GetMapping(value = {BASE_URL, BASE_URL + "/"})
	public List<BookDto> getAllBooks() {
		// Create list of all books, converted to Dto's
		return bookService.getAllBooks().stream().map(book -> BookDto.convertToDto(book)).collect(Collectors.toList());
	}
	
	/**
	 * Creates a new book in LibManSys.
	 * @param title
	 * @param publishedYear
	 * @param author
	 * @param publisher
	 * @param ISBN
	 * @param numPages
	 * @return
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { BASE_URL + "/create/{title}/{publishedYear}/{author}/{publisher}/{ISBN}/{numPages}", BASE_URL + "/create/{title}/{publishedYear}/{author}/{publisher}/{ISBN}/{numPages}/"})
	public BookDto createBook(@PathVariable("title") String title, @PathVariable("publishedYear") String publishedYear, @PathVariable("author") String author,
			@PathVariable("publisher") String publisher, @PathVariable("ISBN") String ISBN, @PathVariable("numPages") String numPages) throws IllegalArgumentException {
		int pubYear;
		int nPages;
		try {
			pubYear = Integer.valueOf(publishedYear);
		} catch(Exception e) {
			throw new IllegalArgumentException("publishedYear must be valid integer. Error" + e.getMessage());
		}
		try {
			nPages = Integer.valueOf(numPages);
		} catch (Exception e) {
			throw new IllegalArgumentException("numPages must be a valid integer. Error: " + e.getMessage());
		}
		Book book = bookService.createBook(title, pubYear, author, publisher, ISBN, nPages);
		return BookDto.convertToDto(book);
	}
	
	/**
	 * Creates a new book. Use json input instead of PathVariable
	 * @param jsonInput
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping(value = { BASE_URL + "/create2", BASE_URL + "/create2/"})
	public BookDto createBook2(@RequestBody Map<String, Object> jsonInput,
			HttpServletRequest request, HttpServletResponse response) {
		// neater version?		
		Book book = null;
		if (jsonInput.get("title") != null && jsonInput.get("publishedYear") != null && jsonInput.get("author") != null
				&& jsonInput.get("publisher") != null && jsonInput.get("ISBN") != null && jsonInput.get("numPages") != null) {
			try {
				String title = (String) jsonInput.get("title");
				int publishedYear = Integer.valueOf((String) jsonInput.get("publishedYear"));
				String author = (String) jsonInput.get("author");
				String publisher = (String) jsonInput.get("publisher");
				String ISBN = (String) jsonInput.get("ISBN");
				int numPages = Integer.valueOf((String) jsonInput.get("numPages"));
				book = bookService.createBook(title, publishedYear, author, publisher, ISBN, numPages);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		} else {
			throw new IllegalArgumentException("Provide following parameters to create a new book: title (String), publishedYear (int), author (String), publisher (String), ISBN (String), numPages (int).");
		}
		return BookDto.convertToDto(book);
	}
	
	
}
