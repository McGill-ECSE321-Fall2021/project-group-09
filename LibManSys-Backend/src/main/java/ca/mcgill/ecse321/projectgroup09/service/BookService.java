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

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private LibraryItemRepository libraryItemRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	/**
	 * Create and return a new book object. Saves new object to Book and LibraryItem Crud repositories.
	 * 
	 * @param author {@code String}
	 * @param publisher {@code String}
	 * @param ISBN {@code int}
	 * @param numPages {@code int}
	 * @return {@code Book} object that was created and added to repository if successful. Throws exception otherwise.
	 */
	@Transactional
	public Book createBook(Long libraryItemId, String title, int publishedYear, int loanablePeriod, double dailyOverdueFee, ItemStatus itemStatus, String author, String publisher, String ISBN, int numPages) {
		// Check input params not null
		if (Objects.isNull(author) || Objects.isNull(publisher) || Objects.isNull(ISBN) || Objects.isNull(numPages)) {
			throw new IllegalArgumentException("Parameters to create a new book must not be null.");
		}
		// Extra param checks necessary?
		// Check ISBN is valid ISBN
		// Check numPages is positive
		
		Book newBook = new Book();
		newBook.setlibraryItemID(libraryItemId);
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
	 * @param bookId {@code long} library item id of book.
	 * @return {@code book} matching [@code bookId} if found in repository, {@code null} otherwise.
	 */
	@Transactional
	public Book getBookById(Long bookId) {
		
		return new Book();
	}
	
	
	/**
	 * 
	 * @param bookId
	 * @param title
	 * @param publishedYear
	 * @param loanablePeriod
	 * @param dailyOverdueFee
	 * @param itemStatus
	 * @param author
	 * @param publisher
	 * @param ISBN
	 * @param numPages
	 * @return
	 */
	@Transactional
	public Book updateBookById(Long bookId, String title, int publishedYear, int loanablePeriod, double dailyOverdueFee, ItemStatus itemStatus, String author, String publisher, String ISBN, int numPages) {
		return new Book();
	}
	
	
	/**
	 * Delete the specified book from the book / library item repositories.
	 * @param bookId {@code long} ID of book to delete
	 * @return {@code true} if book was deleted from repository, {@code false} if {@code bookId} was not present in repository.
	 */
	@Transactional
	public boolean deleteBookById(Long bookId) {
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
