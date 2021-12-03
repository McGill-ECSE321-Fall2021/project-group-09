package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import java.util.ArrayList;
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

import ca.mcgill.ecse321.projectgroup09.dto.LibraryItemDto;
import ca.mcgill.ecse321.projectgroup09.dto.LoanDto;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.service.ArchiveService;
import ca.mcgill.ecse321.projectgroup09.service.BookService;
import ca.mcgill.ecse321.projectgroup09.service.LibraryItemService;
import ca.mcgill.ecse321.projectgroup09.service.MovieService;
import ca.mcgill.ecse321.projectgroup09.service.MusicAlbumService;
import ca.mcgill.ecse321.projectgroup09.service.NewspaperService;

// https://stackoverflow.com/questions/53501185/how-to-post-query-parameters-with-axios
@CrossOrigin(origins = "*")
@RestController
public class LibraryItemController {
	
	private static final String BASE_URL = "/library-item";
	
	@Autowired
	private LibraryItemService libraryItemService;
	
	@Autowired
	private ArchiveService archiveService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MovieService movieService;
	 
	@Autowired
	private MusicAlbumService musicAlbumService;
	
	@Autowired
	private NewspaperService newspaperService;
	
	/**
	 * Testing endpoint
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/create-example-library", BASE_URL + "/create-example-library/"})
	public ResponseEntity<?> createExampleLibrary() {
		try {
			List<LibraryItemDto> lis = new ArrayList<LibraryItemDto>();
			lis.add(LibraryItemDto.convertToDto(archiveService.createArchive("McGill Historic Archives", 1885, "McGill University")));
			lis.add(LibraryItemDto.convertToDto(archiveService.createArchive("Majors of Montreal", 1954, "City of Montreal")));
			lis.add(LibraryItemDto.convertToDto(archiveService.createArchive("History of Quebec", 1775, "Museum of Anthropology")));
			lis.add(LibraryItemDto.convertToDto(bookService.createBook("To Kill a Mockingbird", 1960, "Harper Lee", "Penguin Publishers", "122-3344-333", 843)));
			lis.add(LibraryItemDto.convertToDto(bookService.createBook("Nenteen Eighty-Four", 1949, "George Orwell", "pubbli", "234-442-44", 442)));
			lis.add(LibraryItemDto.convertToDto(bookService.createBook("The Catcher in the Rye", 1951, "J. D. Salinger", "random house", "9823942234", 843)));
			lis.add(LibraryItemDto.convertToDto(bookService.createBook("The Great Gatsby", 1925, "F. Scott Fitsgerald", "pubbli", "756634577845", 2009)));
			lis.add(LibraryItemDto.convertToDto(bookService.createBook("Pride and Prejudice", 1813, "Jane Austen", "Penguin Publishers", "9754798586", 24)));
			lis.add(LibraryItemDto.convertToDto(movieService.createMovie("The Lord Of The Rings: The Fellowship Of The Ring", 2001, "Peter Jackson", 120, "Fantasy")));
			lis.add(LibraryItemDto.convertToDto(movieService.createMovie("Star Wars: The Empire Strikes Back", 1980, "George Lucas", 104, "Action")));
			lis.add(LibraryItemDto.convertToDto(movieService.createMovie("The Godfather", 1976, "Stanley Kubrick", 95, "Drama")));
			lis.add(LibraryItemDto.convertToDto(movieService.createMovie("The Dark Knight", 2009, "Christopher Nolan", 112, "Action")));
			lis.add(LibraryItemDto.convertToDto(movieService.createMovie("The Shining", 1980, "Stanley Kubrick", 94, "Horror")));
			lis.add(LibraryItemDto.convertToDto(musicAlbumService.createMusicAlbum("The Eminem Show", 2002, "Rap", "Eminem", 17, 125)));
			lis.add(LibraryItemDto.convertToDto(musicAlbumService.createMusicAlbum("Montero", 2021, "Pop", "Lil Nas X", 13, 75)));
			lis.add(LibraryItemDto.convertToDto(musicAlbumService.createMusicAlbum("30", 2021, "Pop", "Adele", 14, 85)));
			lis.add(LibraryItemDto.convertToDto(musicAlbumService.createMusicAlbum("Astroworld", 2018, "Rap", "Travis Scott", 13, 105)));
			lis.add(LibraryItemDto.convertToDto(newspaperService.createNewspaper("Sunday Paper", 2021, "La Delit", "2nd", "Ada")));
			lis.add(LibraryItemDto.convertToDto(newspaperService.createNewspaper("Monday Paper", 2021, "The McGill Daily", "10th", "Bob")));
			return httpSuccess(lis);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		
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
	@GetMapping(value = {BASE_URL + "/get-by-title/{title}", BASE_URL + "/get-by-title/{title}/"})
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
	@GetMapping(value = {BASE_URL + "/get-by-status/{status}", BASE_URL + "/get-by-status/{status}/"})
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
	 * 
	 * @param memberLibCardNumber
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/get-by-reserving-member/{id}", BASE_URL + "/get-by-reserving-member/{id}/"})
	public ResponseEntity<?> getLibraryItemsByReservingMember(@PathVariable("id") Long memberLibCardNumber) {
		List<LibraryItemDto> lidtos = null;
		try {
			List<LibraryItem> lis = libraryItemService.getLibraryItemsByReservingMember(memberLibCardNumber);
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
			@RequestParam("libraryItemID") Long libraryItemId) {
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
			@RequestParam("libraryItemID") Long libraryItemId) {
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
	public ResponseEntity<?> checkoutLibraryItem(@RequestParam("employeeID") Long employeeId,
			@RequestParam("libCardNumber") Long libCardNumber, 
			@RequestParam("libraryItemID") Long libraryItemId) {
		Loan l = null;
		try {
			l = libraryItemService.checkoutLibraryItem(employeeId, libCardNumber, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LoanDto ldto = LoanDto.convertToDto(l, true);
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
			@RequestParam("libraryItemID") Long libraryItemId) {
		Loan l = null;
		try {
			l = libraryItemService.renewLibraryItem(libCardNumber, libraryItemId);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		LoanDto ldto = LoanDto.convertToDto(l, true);
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
			@RequestParam("libraryItemID") Long libraryItemId) {
		Loan l = null;
		try {
			l = libraryItemService.returnLibraryItem(libCardNumber, libraryItemId);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
		LoanDto ldto = LoanDto.convertToDto(l, true);
		return httpSuccess(ldto);
	}
}
