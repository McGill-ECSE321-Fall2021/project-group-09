package ca.mcgill.ecse321.projectgroup09.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.dao.BookRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;

/**
 * Book service.
 * 
 * <ul>Services Provided:
 * <li>Create Read Update Delete methods</li>
 * <li>Reserve a book</li>
 * <li>Checkout a book</li>
 * </ul>
 */
@Service
public class BookService {

	// Services //
	
	@Autowired
	private LibraryItemService libraryItemService;
	
	// Repos //
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private LibraryItemRepository libraryItemRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	// Methods //
	
	/**
	 * Create and return a new book object. Saves new object to Book and LibraryItem Crud repositories.
	 * Automatically allocates a new libraryItemId for object.
	 * 
	 * @param title
	 * @param publishedYear
	 * @param loanablePeriod
	 * @param dailyOverdueFee
	 * @param itemStatus
	 * @param author {@code String}
	 * @param publisher {@code String}
	 * @param ISBN {@code int}
	 * @param numPages {@code int}
	 * @return {@code Book} object that was created and added to repository if successful. Throws exception otherwise.
	 */
	@Transactional
	public Book createBook(String title, int publishedYear, int loanablePeriod, double dailyOverdueFee, ItemStatus itemStatus,
			String author, String publisher, String ISBN, int numPages) {
		// Check input params not null (primitive type parameters can't be null... so don't need to check them?)
		if (title == null || itemStatus == null || author == null 
				|| publisher == null || ISBN == null) {
			throw new IllegalArgumentException("Parameters to create a new book must not be null.");
		}
		// Extra param checks necessary? or should this method just be 'dumb' and create object with whatever input values gives (as long as not null obvs)
		// Check ISBN is valid ISBN
		// Check numPages is positive
		
		Book newBook = new Book();
		Long newId = libraryItemService.getNextLibraryItemId();
		newBook.setlibraryItemID(newId);
		newBook.setTitle(title);
		newBook.setPublishedYear(publishedYear);
		newBook.setLoanablePeriod(loanablePeriod);
		newBook.setDailyOverdueFee(dailyOverdueFee);
		newBook.setItemStatus(itemStatus);
		newBook.setAuthor(author);
		newBook.setPublisher(publisher);
		newBook.setISBN(ISBN);
		newBook.setNumPages(numPages);
		
		//newBook.setMember(null);
		//ArrayList<Loan> loans = new ArrayList<Loan>();
		//newBook.setLoans(null);
		
		// Save new book to repository and return it
		bookRepo.save(newBook);
		
		return newBook;
	}
	
	
	/**
	 * Read - Returns the book object specified by bookId if it is present in the
	 * book / library item repository.
	 * 
	 * @param bookId {@code long} library item id of book. Must not be {@code null}.
	 * @return {@code book} matching [@code bookId} if found in repository, {@code null} otherwise.
	 */
	@Transactional
	public Book getBookById(Long bookId) {
		if (Objects.isNull(bookId)) {
			throw new IllegalArgumentException("Id must not be null.");
		}
		Book book = bookRepo.findBookBylibraryItemID(bookId);
		return book;
	}
	
	
	/**
	 * All params except for bookId can be null.
	 * Use wrapped classes instead of primitive so that null values
	 * can be inputted for all params. This allows caller to specify only
	 * params that they want to update, and the rest will be left unchanged.
	 * 
	 * @param bookId must not be null
	 * @param title
	 * @param publishedYear
	 * @param loanablePeriod
	 * @param dailyOverdueFee
	 * @param itemStatus
	 * @param author
	 * @param publisher
	 * @param ISBN
	 * @param numPages
	 * @return The updated book object.
	 */
	@Transactional
	public Book updateBookById(Long bookId, String title, Integer publishedYear, Integer loanablePeriod, Double dailyOverdueFee, 
			ItemStatus itemStatus, String author, String publisher, String ISBN, Integer numPages) {
		// Check input params not null
		if (Objects.isNull(bookId)) {
			throw new IllegalArgumentException("Failed to update book: library item must not be null.");
		}
		
		Book book = bookRepo.findBookBylibraryItemID(bookId);
		if (Objects.isNull(book)) {
			// throw error or just return null
			throw new IllegalStateException("Could not find a book with the specified id (id: " + bookId + ").");
		}
		
		// Update attributes of <Book> that are not null
		if (title != null) {
			book.setTitle(title);
		} else if (publishedYear != null) {
			book.setPublishedYear(publishedYear);
		} else if (loanablePeriod != null) {
			book.setLoanablePeriod(loanablePeriod);
		} else if (dailyOverdueFee != null) {
			book.setDailyOverdueFee(dailyOverdueFee);
		} else if (itemStatus != null) {
			book.setItemStatus(itemStatus);
		} else if (author != null) {
			book.setAuthor(author);
		} else if (publisher != null) {
			book.setPublisher(publisher);
		} else if (ISBN != null) {
			book.setISBN(ISBN);
		} else if (numPages != null) {
			book.setNumPages(numPages);
		}
		
		// save book and return
		bookRepo.save(book);
		
		return book;
	}
	
	
	/**
	 * Delete the specified book from the book / library item repositories.
	 * @param bookId {@code long} ID of book to delete
	 * @return {@code true} if book was deleted from repository, {@code false} if {@code bookId} was not present in repository.
	 */
	@Transactional
	public boolean deleteBookById(Long bookId) {
		// TODO implement
		return false;
	}
	
	
	/**
	 * This service method reserves a book for a user,
	 * as long as that book is not already being reserved.
	 * 
	 * @param memberId {@code Long} Library card number of member attemping to reserve a Book.
	 * @param bookId {@code Long} Id of book member is attemping to reserve.
	 */
	@Transactional
	public void reserveBook(Long memberId, Long bookId) {
		// Check inputs
		if (Objects.isNull(memberId) || Objects.isNull(bookId)) {
			throw new IllegalArgumentException("Arguments must not be null");
		}
		
		// Reserving Member
		Member rm = memberRepo.findMemberByLibCardNumber(memberId);
		// Book to reserve
		LibraryItem book = libraryItemRepo.findLibraryItemByLibraryItemID(bookId);
		
		if (Objects.isNull(rm)) {
			throw new NullPointerException("Could not find member in Member repository.");
		}
		if (Objects.isNull(book)) {
			throw new NullPointerException("Could not find book in LibraryItem repository.");
		}
		
		// Make sure book is not reserved already
		if (book.getItemStatus().equals(LibraryItem.ItemStatus.Reserved)) {
			throw new IllegalStateException("Cannot reserve book that is already being reserved by another member.");
		}
		
		// Make sure book is able to be reserved
		if (book.getItemStatus().equals(LibraryItem.ItemStatus.LibraryOnly)) {
			throw new IllegalStateException("Cannot reserve item that is for library use only (newspapers and archives).");
		}
		
		// Add item to member's reserved list
		rm.getReserved().add(book);
		// Set book status to reserved
		book.setItemStatus(LibraryItem.ItemStatus.Reserved);
		
		// Save member and book?
		
	}
	
}
