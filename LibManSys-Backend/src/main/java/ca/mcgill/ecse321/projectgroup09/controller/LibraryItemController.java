package ca.mcgill.ecse321.projectgroup09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dao.BookRepository;
import ca.mcgill.ecse321.projectgroup09.dto.LibraryItemDto;
import ca.mcgill.ecse321.projectgroup09.dto.LoanDto;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.service.BookService;
import ca.mcgill.ecse321.projectgroup09.service.LibraryItemService;

// https://stackoverflow.com/questions/53501185/how-to-post-query-parameters-with-axios
@CrossOrigin(origins = "*")
@RestController
public class LibraryItemController {
	
	private static final String BASE_URL = "/library-item";
	
	@Autowired
	private LibraryItemService libraryItemService;
	
	// testing only
	@Autowired
	private BookService bookService;
	// testing only
	@Autowired
	private BookRepository bookRepo;
	
	/**
	 * Testing endpoint
	 * @return
	 */
	@PostMapping(value = {"BASE_URL + /test", BASE_URL + "/test/"})
	public ResponseEntity<?> test() {
		String message = "";
		
		Book book1 = bookService.createBook("titlk", 109, "asdf", "asdf", "asdf", 33);
		
		bookRepo.save(book1);
		book1.setlibraryItemID(1111L);
		
		
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	
	/**
	 * Get a library item by id.
	 * @param libraryItemId
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/{id}", BASE_URL + "/{id}/"})
	public ResponseEntity<?> getLibraryItemById(@PathVariable("id") Long libraryItemId) {
		// if libraryItemId = null
		LibraryItem li;
		try {
			li = libraryItemService.getLibraryItemById(libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LibraryItemDto lidto = LibraryItemDto.convertToDto(li);
		return ResponseEntity.status(HttpStatus.OK).body(lidto);
	}

	/**
	 * Return all library items with the specified title.
	 * @param title
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/{title}", BASE_URL + "/{title}/"})
	public ResponseEntity<?> getLibraryItemsByTitle(@PathVariable("title") String title) {
		List<LibraryItem> lis = null;
		try {
			lis = libraryItemService.getLibraryItemsByTitle(title);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		List<LibraryItemDto> lidtos = LibraryItemDto.convertToDtos(lis);
		return ResponseEntity.status(HttpStatus.OK).body(lidtos);
	}

	/**
	 * Return all library items with the specified item status.
	 * @param statusString String corresponding to one of LibraryItem.ItemStatus enum elements.
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/{status}", BASE_URL + "/{status}/"})
	public ResponseEntity<?> getLibraryItemsByStatus(@PathVariable("status") String statusString) {
		// parse statusString into ItemStatus enum
		ItemStatus itemStatus = null;
		try {
			itemStatus = ItemStatus.valueOf(statusString);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: could not parse ItemStatus enum from input string '" + statusString + "'.");
		}
		List<LibraryItemDto> lidtos = null;
		try {
			List<LibraryItem> lis = libraryItemService.getLibraryItemsByStatus(itemStatus);
			lidtos = LibraryItemDto.convertToDtos(lis);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(lidtos);
	}
	
	/**
	 * Return list of all library items.
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "", BASE_URL + "/", BASE_URL + "/get-all", BASE_URL + "/get-all/"})
	public ResponseEntity<?> getAllLibraryItems() {
		try {
			List<LibraryItem> lis = libraryItemService.getAllLibraryItems();
			List<LibraryItemDto> lidtos = LibraryItemDto.convertToDtos(lis);
			return ResponseEntity.status(HttpStatus.OK).body(lidtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}

	/**
	 * Delete library item from repository.
	 * @param libraryItemId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/"})
	public ResponseEntity<?> deleteLibraryItem(@PathVariable("id") Long libraryItemId) {
		boolean deleted = false;
		try {
			deleted = libraryItemService.deleteLibraryItemById(libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		if (deleted) {
			return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted book " + libraryItemId + " from repository.");
		}
		// else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to delete library item because no library item with id " + libraryItemId + " exists.");
	}

	/**
	 * 
	 * @param memberId
	 * @param libraryItemId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/reserve", BASE_URL + "/reserve/"})
	public ResponseEntity<?> reserveLibraryItemItem(@RequestParam("memberId") Long memberId, 
			@RequestParam("libraryItemId") Long libraryItemId) {
		LibraryItem li = null;
		try {
			li = libraryItemService.reserveLibraryItem(memberId, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LibraryItemDto lidto = LibraryItemDto.convertToDto(li);
		return ResponseEntity.status(HttpStatus.OK).body(lidto);
	}
	
	/**
	 * 
	 * @param memberId
	 * @param libraryItemId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/cancel-reservation", BASE_URL + "/cancel-reservation/"})
	public ResponseEntity<?> cancelReservation(@RequestParam("memberId") Long memberId, 
			@RequestParam("libraryItemId") Long libraryItemId) {
		LibraryItem li = null;
		try {
			li = libraryItemService.cancelReservation(memberId, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LibraryItemDto lidto = LibraryItemDto.convertToDto(li);
		return ResponseEntity.status(HttpStatus.OK).body(lidto);
	}

	/**
	 * Checkout a library item.
	 * @param librarianId
	 * @param memberId
	 * @param libraryItemId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/checkout", BASE_URL + "/checkout/"})
	public ResponseEntity<?> checkoutLibraryItem(@RequestParam("librarianId") Long librarianId,
			@RequestParam("memberId") Long memberId, 
			@RequestParam("libraryItemId") Long libraryItemId) {
		Loan l = null;
		try {
			l = libraryItemService.checkoutLibraryItem(librarianId, memberId, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LoanDto ldto = LoanDto.convertToDto(l);
		return ResponseEntity.status(HttpStatus.OK).body(ldto);
	}
	
	/**
	 * Renew library item.
	 * @param memberId
	 * @param libraryItemId
	 * @return Updated loan object if successful, otherwise error message.
	 */
	@PostMapping(value = {BASE_URL + "/renew", BASE_URL + "/renew/"})
	public ResponseEntity<?> renewLibraryItem(@RequestParam("memberId") Long memberId, 
			@RequestParam("libraryItemId") Long libraryItemId) {
		Loan l = null;
		try {
			l = libraryItemService.renewLibraryItem(memberId, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LoanDto ldto = LoanDto.convertToDto(l);
		return ResponseEntity.status(HttpStatus.OK).body(ldto);
	}
	
	/**
	 * Return library item
	 * @param memberId
	 * @param libraryItemId
	 * @return Updated loan object if successful, otherwise error message.
	 */
	@PostMapping(value = {BASE_URL + "/return", BASE_URL + "/return/"})
	public ResponseEntity<?> returnLibraryItem(@RequestParam("memberId") Long memberId,
			@RequestParam("libraryItemId") Long libraryItemId) {
		Loan l = null;
		try {
			l = libraryItemService.returnLibraryItem(memberId, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LoanDto ldto = LoanDto.convertToDto(l);
		return ResponseEntity.status(HttpStatus.OK).body(ldto);
	}
}
