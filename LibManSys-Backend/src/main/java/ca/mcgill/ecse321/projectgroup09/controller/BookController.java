package ca.mcgill.ecse321.projectgroup09.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.BookDto;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
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
	@GetMapping(value = {BASE_URL, BASE_URL + "/", BASE_URL + "/getAll", BASE_URL + "/getAll/"})
	public ResponseEntity<?> getAllBooks() {
		// Create list of all books, converted to Dto's
		List<BookDto> books = bookService.getAllBooks().stream().map(book -> BookDto.convertToDto(book)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(books);
	}
	
	/**
	 * Create a book using post variables instead of path variables
	 * @param title
	 * @param publishedYear
	 * @param author
	 * @param publisher
	 * @param ISBN
	 * @param numPages
	 * @return
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { BASE_URL + "/create", BASE_URL + "/create/"})
	public ResponseEntity<?> createBookPostVars(@RequestParam("title") String title, @RequestParam("publishedYear") String publishedYear, @RequestParam("author") String author,
			@RequestParam("publisher") String publisher, @RequestParam("ISBN") String ISBN, @RequestParam("numPages") String numPages) throws IllegalArgumentException {
		
		int pubYear;
		int nPages;
		try {
			pubYear = Integer.valueOf(publishedYear);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("publishedYear must be valid integer. Error: " + e.getMessage());
		}
		try {
			nPages = Integer.valueOf(numPages);
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("numPages must be a valid integer. Error: " + e.getMessage());
		}
		Book book;
		book = bookService.createBook(title, pubYear, author, publisher, ISBN, nPages);
		try {
			
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(BookDto.convertToDto(book));
	}
	
	/**
	 * Creates a new book. Use json input instead of PathVariable
	 * @param jsonInput
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping(value = { BASE_URL + "/createJson", BASE_URL + "/createJson/"})
	public BookDto createBookJson(@RequestBody Map<String, Object> jsonInput,
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
	
	/**
	 * Get a book by id.
	 * @param bookId
	 * @return BookDto
	 */
	@GetMapping(value = { BASE_URL + "/{id}", BASE_URL + "" })
	public ResponseEntity<?> getBookById(@PathVariable("id") long bookId) {
		try {
			BookDto bookDto = BookDto.convertToDto(bookService.getBookById(bookId));
			return ResponseEntity.status(HttpStatus.OK).body(bookDto);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	/**
	 * Update a book
	 * @param bookId
	 * @param title
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/update/{id}", BASE_URL + "/update/{id}/"})
	public ResponseEntity<?> updateBook(@PathVariable("id") Long bookId, @RequestParam(value = "title", required = false) String title, @RequestParam(value = "publisherYear", required = false) Integer publisherYear,
			@RequestParam(value = "loanablePeriod", required = false) Integer loanablePeriod, @RequestParam(value = "dailyOverdueFee", required = false) Double dailyOverdueFee, @RequestParam(value = "itemStatus", required = false) String itemStatus,
			@RequestParam(value = "author", required = false) String author, @RequestParam(value = "publisher", required = false) String publisher, @RequestParam(value = "ISBN", required = false) String ISBN, @RequestParam(value = "numPages", required = false) Integer numPages) {
		try {
			ItemStatus iS = ItemStatus.valueOf(itemStatus);
			BookDto bookDto = BookDto.convertToDto(bookService.updateBook(bookId, title, publisherYear, loanablePeriod, dailyOverdueFee, iS, author, publisher, ISBN, numPages));
			return ResponseEntity.status(HttpStatus.OK).body(bookDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}
	
	
}
