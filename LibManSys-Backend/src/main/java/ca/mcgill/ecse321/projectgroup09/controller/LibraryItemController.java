package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

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
		List<LibraryItemDto> lidtos = LibraryItemDto.convertToDto(lis);
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
			lidtos = LibraryItemDto.convertToDto(lis);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(lidtos);
	}
	
	/**
	 * Return list of all library items. This is the endpoint for the base url of 
	 * library item controller, aka: www.site.com/library-item
	 * @return list of all library items
	 */
	@GetMapping(value = {BASE_URL + "", BASE_URL + "/", BASE_URL + "/get-all", BASE_URL + "/get-all/"})
	public ResponseEntity<?> getAllLibraryItems() {
		try {
			List<LibraryItem> lis = libraryItemService.getAllLibraryItems();
			List<LibraryItemDto> lidtos = LibraryItemDto.convertToDto(lis);
			return ResponseEntity.status(HttpStatus.OK).body(lidtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}

	/**
	 * Delete library item from repository.
	 * @param libraryItemId
	 * @return Message indicating if library item was successfull deleted or not.
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
			return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted library item with ID = " + libraryItemId + " from repository.");
		}
		// else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to delete library item because no library item with ID " + libraryItemId + " exists.");
	}

	/**
	 * Reserve a library item for a member. Reserving means that no one other than the
	 * member placing the reservation can checkout the reserved item.
	 * Members can only have a max of 10 library items reserved at a time.
	 * 
	 * @param libCardNumber Library card number of member making reservation.
	 * @param libraryItemId ID of library item member is attemping to reserve.
	 * @return Library item that was reserved if successful, error message otherwise.
	 */
	@PostMapping(value = {BASE_URL + "/reserve", BASE_URL + "/reserve/"})
	public ResponseEntity<?> reserveLibraryItemItem(@RequestParam("libCardNumber") Long libCardNumber, 
			@RequestParam("libraryItemId") Long libraryItemId) {
		LibraryItem li = null;
		try {
			li = libraryItemService.reserveLibraryItem(libCardNumber, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LibraryItemDto lidto = LibraryItemDto.convertToDto(li);
		return httpSuccess(lidto);
	}
	
	/**
	 * Cancel reservation.
	 * @param libCardNumber Library card number of member attemping to cancel reservation.
	 * @param libraryItemId ID of library item for which to cancel member's reservation for.
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/cancel-reservation", BASE_URL + "/cancel-reservation/"})
	public ResponseEntity<?> cancelReservation(@RequestParam("libCardNumber") Long libCardNumber, 
			@RequestParam("libraryItemId") Long libraryItemId) {
		LibraryItem li = null;
		try {
			li = libraryItemService.cancelReservation(libCardNumber, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LibraryItemDto lidto = LibraryItemDto.convertToDto(li);
		return ResponseEntity.status(HttpStatus.OK).body(lidto);
	}

	/**
	 * Checkout a library item.
	 * @param employeeId Employee ID number of librarian authorizing checkout.
	 * @param libCardNumber Library card number of member attempting to checkout library item.
	 * @param libraryItemId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/checkout", BASE_URL + "/checkout/"})
	public ResponseEntity<?> checkoutLibraryItem(@RequestParam("employeeId") Long employeeId,
			@RequestParam("libCardNumber") Long libCardNumber, 
			@RequestParam("libraryItemId") Long libraryItemId) {
		Loan l = null;
		try {
			l = libraryItemService.checkoutLibraryItem(employeeId, libCardNumber, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LoanDto ldto = LoanDto.convertToDto(l);
		return ResponseEntity.status(HttpStatus.OK).body(ldto);
	}
	
	/**
	 * Renew library item. Doesn't require librarian approval?
	 * @param libCardNumber Library card number of member attempting renewal.
	 * @param libraryItemId
	 * @return Updated loan object if successful, otherwise error message.
	 */
	@PostMapping(value = {BASE_URL + "/renew", BASE_URL + "/renew/"})
	public ResponseEntity<?> renewLibraryItem(@RequestParam("libCardNumber") Long libCardNumber, 
			@RequestParam("libraryItemId") Long libraryItemId) {
		Loan l = null;
		try {
			l = libraryItemService.renewLibraryItem(libCardNumber, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LoanDto ldto = LoanDto.convertToDto(l);
		return ResponseEntity.status(HttpStatus.OK).body(ldto);
	}
	
	/**
	 * Return library item. Also calculates late fees if applicable and adds any fees
	 * accrued to the member total fees owed.
	 * @param libCardNumber Library card number of member attempting to return library item.
	 * @param libraryItemId
	 * @return Updated loan object if successful, otherwise error message.
	 */
	@PostMapping(value = {BASE_URL + "/return", BASE_URL + "/return/"})
	public ResponseEntity<?> returnLibraryItem(@RequestParam("libCardNumber") Long libCardNumber,
			@RequestParam("libraryItemId") Long libraryItemId) {
		Loan l = null;
		try {
			l = libraryItemService.returnLibraryItem(libCardNumber, libraryItemId);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
		LoanDto ldto = LoanDto.convertToDto(l);
		return httpSuccess(ldto);
	}
}
