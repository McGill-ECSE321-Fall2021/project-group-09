package ca.mcgill.ecse321.projectgroup09.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.BookDto;
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
	 * @return
	 */
	@GetMapping(value = {BASE_URL, BASE_URL + "/"})
	public List<BookDto> getAllBooks() {
		// Create list of all books, converted to Dto's
		return bookService.getAllBooks().stream().map(book -> BookDto.convertToDto(book)).collect(Collectors.toList());
	}


	
}
