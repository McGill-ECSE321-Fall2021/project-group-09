package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import java.util.List;
import java.util.Map;

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
import ca.mcgill.ecse321.projectgroup09.dto.LibraryItemDto;
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
	
	private static final String BASE_URL = "/book";
	
	@Autowired
	private BookService bookService;
	
	
	/**
	 * Return list of all books in library management system.
	 * Base endpoint of /books
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "", BASE_URL + "/"})
	public ResponseEntity<?> getAllBooks() {
		// Create list of all books, converted to Dto's
		List<LibraryItemDto> books = LibraryItemDto.convertToDto(bookService.getAllBooks());
		return httpSuccess(books);
	}
	
	@GetMapping(value = {BASE_URL + "/by-title/{title}", BASE_URL + "/by-title/{title}/"})
	public ResponseEntity<?> getBooksByTitle(@PathVariable String title) {
		try {
			List<LibraryItemDto> books = LibraryItemDto.convertToDto(bookService.getBooksByTitle(title));
			return httpSuccess(books);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		
	}
	
	@GetMapping(value = {BASE_URL + "/by-published-year/{pubYear}", BASE_URL + "/by-published-year/{pubYear}/"})
	public ResponseEntity<?> getBooksByPubYear(@PathVariable int pubYear) {
		try {
			List<LibraryItemDto> books = LibraryItemDto.convertToDto(bookService.getBooksByPublishedYear(pubYear));
			return httpSuccess(books);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		
	}
	
	@GetMapping(value = {BASE_URL + "/by-author/{author}", BASE_URL + "/by-author/{author}/"})
	public ResponseEntity<?> getBooksByAuthor(@PathVariable String author) {
		try {
			List<LibraryItemDto> books = LibraryItemDto.convertToDto(bookService.getBooksByAuthor(author));
			return httpSuccess(books);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	/**
	 * publisher
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/by-publisher/{publisher}", BASE_URL + "/by-publisher/{publisher}/"})
	public ResponseEntity<?> getBooksByPublisher(@PathVariable String publisher) {
		try {
			List<LibraryItemDto> books = LibraryItemDto.convertToDto(bookService.getBooksByPublisher(publisher));
			return httpSuccess(books);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	/**
	 * @param isbn
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/by-isbn/{isbn}", BASE_URL + "/by-isbn/{isbn}/"})
	public ResponseEntity<?> getBooksByISBN(@PathVariable String isbn) {
		try {
			List<LibraryItemDto> books = LibraryItemDto.convertToDto(bookService.getBooksByISBN(isbn));
			return httpSuccess(books);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param numPages
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/by-num-pages/{numPages}", BASE_URL + "/by-num-pages/{numPages}/"})
	public ResponseEntity<?> getBooksByNumPages(@PathVariable int numPages) {
		try {
			List<LibraryItemDto> books = LibraryItemDto.convertToDto(bookService.getBooksByNumPages(numPages));
			return httpSuccess(books);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
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
	public ResponseEntity<?> createBook(@RequestParam("title") String title, 
			@RequestParam("publishedYear") String publishedYear,
			@RequestParam("author") String author,
			@RequestParam("publisher") String publisher,
			@RequestParam("ISBN") String ISBN,
			@RequestParam("numPages") String numPages) throws IllegalArgumentException {
		
		int pubYear;
		int nPages;
		try {
			pubYear = Integer.valueOf(publishedYear);
		} catch(Exception e) {
			return httpFailureMessage("publishedYear must be valid integer. Error: " + e.getMessage());
		}
		try {
			nPages = Integer.valueOf(numPages);
		} catch (Exception e) {
			return httpFailureMessage("numPages must be a valid integer. Error: " + e.getMessage());
		}
		Book book;
		try {
			book = bookService.createBook(title, pubYear, author, publisher, ISBN, nPages);
		} catch(Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		return httpSuccess(BookDto.convertToDto(book));
	}
	
	/**
	 * Test
	 * Creates a new book. Use json input instead of PathVariable / request param
	 * @deprecated
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
	public ResponseEntity<?> updateBook(@PathVariable("id") Long bookId, 
			@RequestParam(value = "title", required = false) String title, 
			@RequestParam(value = "publisherYear", required = false) Integer publisherYear,
			@RequestParam(value = "loanablePeriod", required = false) Integer loanablePeriod, 
			@RequestParam(value = "dailyOverdueFee", required = false) Double dailyOverdueFee, 
			@RequestParam(value = "itemStatus", required = false) String itemStatus,
			@RequestParam(value = "author", required = false) String author, 
			@RequestParam(value = "publisher", required = false) String publisher,
			@RequestParam(value = "ISBN", required = false) String ISBN, 
			@RequestParam(value = "numPages", required = false) Integer numPages) {
		try {
			ItemStatus iS = ItemStatus.valueOf(itemStatus);
			BookDto bookDto = BookDto.convertToDto(bookService.updateBook(bookId, title, publisherYear, loanablePeriod, dailyOverdueFee, iS, author, publisher, ISBN, numPages));
			return ResponseEntity.status(HttpStatus.OK).body(bookDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Delete book from repository.
	 * Should it return the deleted book or just success message?
	 * 
	 * @param bookId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/"})
	public ResponseEntity<?> deleteBook(@PathVariable("id") Long bookId) {
		if (bookId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: ID cannot be null.");
		}
		boolean deleted = false;
		try {
			deleted = bookService.deleteBookById(bookId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		if (deleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Deleted book " + bookId + " from repository succesfully.");
		}
		// else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete book: Book with id " + bookId + " does not exist.");
	}
}
