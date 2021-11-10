package ca.mcgill.ecse321.projectgroup09.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.dto.BookDto;
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
	 * @param title {@code String}
	 * @param publishedYear {@code int
	 * @param author {@code String}
	 * @param publisher {@code String}
	 * @param ISBN {@code String}
	 * @param numPages {@code int}
	 * @return {@code Book} object that was created and added to repository if successful. Throws exception otherwise.
	 */
	@Transactional
	public Book createBook(String title, int publishedYear, String author, String publisher, String ISBN, int numPages) {
		// Check input params not null (primitive type parameters can't be null... so don't need to check them?)
		if (title == null || author == null || publisher == null || ISBN == null) {
			throw new IllegalArgumentException("Parameters to create a new book must not be null.");
		}
		// Extra param checks necessary? or should this method just be 'dumb' and create object with whatever input values gives (as long as not null obvs)
		// Check ISBN is valid ISBN
		if (false /*ISBN is invalid*/) {
			throw new IllegalArgumentException("Cannot create a book with an invalid ISBN: " + ISBN);
		}
		// Check numPages is positive
		if (numPages < 0) {
			
		}
		
		Book newBook = new Book();
		// LibraryItem constructor sets ID automatically
		//Long newId = libraryItemService.getNextLibraryItemId();
		//newBook.setlibraryItemID(newId);
		// loanablePeriod is set by default in book constructor
		//newBook.setLoanablePeriod(loanablePeriod);
		// dailyOverdueFee is set by default in Book constructor
		//newBook.setDailyOverdueFee(dailyOverdueFee);
		// itemStatus is set by default in Book constructor
		//newBook.setItemStatus(itemStatus);
		System.out.println("Creating book with publisher = " + publisher);
		// Set custom attributes of Book
		newBook.setTitle(title);
		newBook.setPublishedYear(publishedYear);
		newBook.setAuthor(author);
		newBook.setPublisher(publisher);
		newBook.setISBN(ISBN);
		newBook.setNumPages(numPages);
		
		//newBook.setMember(null);
		//ArrayList<Loan> loans = new ArrayList<Loan>();
		//newBook.setLoans(null);
		
		// Save new book to repository and return it (savedBook should be identical to newBook)
		Book savedBook = bookRepo.save(newBook);
		
		return savedBook;
	}
	
	
	/**
	 * Read - Returns the book object specified by libraryItemId if it is present in the
	 * book / library item repository.
	 * 
	 * @param libraryItemId {@code long} library item id of book. Must not be {@code null}.
	 * @return {@code book} matching [@code libraryItemId} if found in repository, {@code null} otherwise.
	 */
	@Transactional
	public Book getBookById(Long libraryItemId) {
		if (libraryItemId == null) {
			throw new IllegalArgumentException("Id must not be null.");
		}
		Book book = bookRepo.findBookBylibraryItemID(libraryItemId);
		return book;
	}
	
	/**
	 * Get all books in book repository.
	 * @return list of all books in library management system.
	 */
	public List<Book> getAllBooks() {
		List<Book> books = bookRepo.findAll();
		return books;
	}
	
	
	/**
	 * NOTE: this method is not really need, because library items should be final.
	 * <p>
	 * Update the book object specified by libraryItemId with the specified parameters as
	 * the new attributes for the book. Some attributes cannot be changed, specifically:
	 * </p>
	 * <p>
	 * All params except for libraryItemId can be null.
	 * Use wrapped classes instead of primitive so that null values
	 * can be inputted for all params. This allows caller to specify only
	 * params that they want to update, and the rest will be left unchanged.
	 * </p>
	 * 
	 * @param libraryItemId must not be null
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
	public Book updateBook(Long libraryItemId, String title, Integer publishedYear, Integer loanablePeriod, Double dailyOverdueFee, 
			ItemStatus itemStatus, String author, String publisher, String ISBN, Integer numPages) {
		// Check input params not null
		if (libraryItemId == null) {
			throw new IllegalArgumentException("Failed to update book: library item must not be null.");
		}
		
		Book book = bookRepo.findBookBylibraryItemID(libraryItemId);
		if (book == null) {
			// throw error or just return null
			throw new IllegalStateException("Could not find a book with the specified id (id: " + libraryItemId + ").");
		}

		// Update attributes of <Book> that are not null
		if (title != null) {
			book.setTitle(title);
		} 
		if (publishedYear != null) {
			book.setPublishedYear(publishedYear);
		}
		if (loanablePeriod != null) {
			book.setLoanablePeriod(loanablePeriod);
		}
		if (dailyOverdueFee != null) {
			book.setDailyOverdueFee(dailyOverdueFee);
		}
		if (itemStatus != null) {
			book.setItemStatus(itemStatus);
		}
		if (author != null) {
			book.setAuthor(author);
		}
		if (publisher != null) {
			book.setPublisher(publisher);
		}
		if (ISBN != null) {
			book.setISBN(ISBN);
		}
		if (numPages != null) {
			book.setNumPages(numPages);
		}
		
		// save book and return
		bookRepo.save(book);
		
		return book;
	}
	
	/**
	 * Delete the specified book from the book / library item repositories.
	 * @param bookToDelete {@code book} Book to delete from repository.
	 * @return {@code true} if book was deleted from repository, {@code false} if {@code bookToDelete} was not present in repository.
	 */
	@Transactional
	public boolean deleteBook(Book bookToDelete) {
		if (bookToDelete == null) {
			return false;
		} else {
			bookRepo.delete(bookToDelete);
			return true;
		}
	}
	
	
	/**
	 * Delete the specified book from the book / library item repositories using library item ID.
	 * @param libraryItemId {@code long} ID of book to delete
	 * @return {@code true} if book was deleted from repository, {@code false} if {@code libraryItemId} was not present in repository.
	 */
	@Transactional
	public boolean deleteBookById(Long libraryItemId) {
		if (libraryItemId == null) {
			throw new IllegalArgumentException("libraryItemId parameter cannot be null.");
		}
		Book book = bookRepo.findBookBylibraryItemID(libraryItemId);
		return deleteBook(book);
		
	}
	
	
	/**
	 * Business method.
	 * 
	 * This service method reserves a book for a user,
	 * as long as that book is not already being reserved.
	 * 
	 * @param memberId {@code Long} Library card number of member attemping to reserve a Book.
	 * @param libraryItemId {@code Long} Id of book member is attemping to reserve.
	 */
	@Transactional
	public void reserveBook(Member rm, Book book) {
		// Check inputs not null
		if (rm == null || book == null) {
			throw new IllegalArgumentException("Arguments must not be null");
		}
		
		/*
		// Reserving Member
		Member rm = memberRepo.findMemberByLibCardNumber(memberId);
		// Book to reserve
		LibraryItem book = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		
		// Make sure member and library item exists
		if (rm == null) {
			throw new NullPointerException("Could not find member in Member repository.");
		}
		if (book == null) {
			throw new NullPointerException("Could not find book in LibraryItem repository.");
		}
		*/
		
		// Make book library item status is set
		if (book.getItemStatus() == null) {
			throw new IllegalStateException("Book.getItemStatus() == null");
		}
		
		// Make sure book is not reserved already
		if (book.getItemStatus().equals(LibraryItem.ItemStatus.Reserved)) {
			throw new IllegalStateException("Cannot reserve book that is already being reserved by another member.");
		}
		
		// Make sure book is able to be reserved
		if (book.getItemStatus().equals(LibraryItem.ItemStatus.LibraryOnly)) {
			throw new IllegalStateException("Cannot reserve item that is for library use only (newspapers and archives).");
			// return false instead of throwing exception
			
		}
		
		// Make sure member reserved list is intialized
		if (rm.getReserved() == null) {
			throw new IllegalStateException("Member reserve list is not initialized.");
		}
		
		// Add item to member's reserved list
		rm.getReserved().add(book);
		// Set book status to reserved
		book.setItemStatus(LibraryItem.ItemStatus.Reserved);
		
		// Save member and book to update information
		bookRepo.save((Book)book);
		libraryItemRepo.save(book);
		memberRepo.save(rm);
	}

	
}
