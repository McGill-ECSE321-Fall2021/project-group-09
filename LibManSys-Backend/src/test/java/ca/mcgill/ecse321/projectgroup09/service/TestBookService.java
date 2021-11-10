package ca.mcgill.ecse321.projectgroup09.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.projectgroup09.dao.BookRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;


@ExtendWith(MockitoExtension.class)
public class TestBookService {

	@Mock
	private BookRepository bookDao;
	
	//@Mock
	//private LibraryItemRepository libraryItemDao; 
	
	@Mock
	private MemberRepository memberDao;

	//@Autowired
	@InjectMocks
	private BookService bookService;
	
	// Can't figure out how to test a service that uses another service.
	//@Mock
	//private LibraryItemService libraryItemService;
	
	@InjectMocks
	private MemberService memberService;

	/** Invalid member id. */
	private static final Long       NAN_MEMBER_ID = -1L;
	
	private static final Long       MEMBER_ID     = 900L;
	private static final String     MEMBER_NAME   = "Jane Doe";
	
	/** Invalid book id. */
	private static final Long       NAN_BOOK_ID   = -1L;
	
	private static final Long       BOOK_ID       = 800L;
	private static final String     BOOK_TITLE    = "Frankenstein";
	private static final ItemStatus BOOK_STATUS   = ItemStatus.Available;
	
	// Setup mocks
	@BeforeEach
	public void setMockOutput() {
		// Setup mock member
		lenient().when(memberDao.findMemberByLibCardNumber(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(MEMBER_ID)) {
	            Member member = new Member();
	            member.setLibCardNumber(MEMBER_ID);
	            return member;
	        } else {
	            return null;
	        }
	    });
		// Setup mock book for bookDao
		lenient().when(bookDao.findBookBylibraryItemID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(BOOK_ID)) {
				Book book = new Book();
				book.setlibraryItemID(BOOK_ID);
				// make sure book has library item status set for reserve tests
				book.setItemStatus(BOOK_STATUS);
				book.setTitle(BOOK_TITLE);
				return book;
			} else {
				return null;
			}
		});
		// Setup mock book for libraryItemDao, same as bookDao
		/*
		lenient().when(libraryItemDao.findLibraryItemByLibraryItemID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(BOOK_ID)) {
				Book book = new Book();
				book.setlibraryItemID(BOOK_ID);
				// make sure book has library item status set
				book.setItemStatus(LibraryItem.ItemStatus.Available);
				return book;
			} else {
				return null;
			}
		});
		*/
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(memberDao.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(bookDao.save(any(Book.class))).thenAnswer(returnParameterAsAnswer);
		//lenient().when(libraryItemDao.save(any(LibraryItem.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	// CRUD methods
	@Test
	public void testCreateBook() {
		Book book = null;
		String title = "Animal Farm";
		int publishedYear = 2003;
		String publisher = "Berkley";
		String ISBN = "9780452284241";
		int numPages = 128;
		String author = "George Orwell";		
		try {
			book = bookService.createBook(title, publishedYear, author, publisher, ISBN, numPages);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		// check book created succesfully
		assertNotNull(book);
		assertEquals(title, book.getTitle());
		assertEquals(publishedYear, book.getPublishedYear());
		assertEquals(publisher, book.getPublisher());
		assertEquals(ISBN, book.getISBN());
		assertEquals(numPages, book.getNumPages());
		assertEquals(author, book.getAuthor());
	}
	
	// test other cases for crud
	@Test
	public void testGetBook_Existing() {
		Book book = bookService.getBookById(BOOK_ID);
		assertNotNull(book);
		assertEquals(BOOK_ID, book.getlibraryItemID());
		assertEquals(BOOK_TITLE, book.getTitle());
	}
	
	@Test
	public void testGetBook_NotExisting() {
		Book book = bookService.getBookById(NAN_BOOK_ID);
		assertNull(book);
	}
	
	// Not really using update methods for library items. Keep test just in case.
	@Test
	public void testUpdateBook() {
		//Book book = bookDao.findBookBylibraryItemID(BOOK_ID);
		String newTitle = "Updated title";
		int newPubYear = 2031;
		int newLoanPeriod = 1;
		Book updatedBook = null;
		try {
			updatedBook = bookService.updateBook(BOOK_ID, newTitle, newPubYear, newLoanPeriod, null, null, null, null, null, null);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(updatedBook);
		assertEquals(newTitle, updatedBook.getTitle());
		assertEquals(newPubYear, updatedBook.getPublishedYear());
		assertEquals(newLoanPeriod, updatedBook.getLoanablePeriod());
	}
	
	@Test
	public void testDeleteBook() {
		Book book = bookDao.findBookBylibraryItemID(BOOK_ID);
		boolean deleted = bookService.deleteBook(book);
		// book exists, so should have been deleted, ie return true
		assertEquals(true, deleted);
	}
	
	@Test
	public void testDeleteBook_NotExisting() {
		Book book = bookDao.findBookBylibraryItemID(NAN_BOOK_ID);
		boolean deleted = bookService.deleteBook(book);
		// book does not exists, so nothing deleted, ie return false
		assertEquals(false, deleted);
	}
	
	// Business method
	@Test
	public void testReserveBook() {		
		Book book = bookDao.findBookBylibraryItemID(BOOK_ID);
		Member member = memberDao.findMemberByLibCardNumber(MEMBER_ID);
		//Long bookId   = book.getlibraryItemID();
		//Long memberId = member.getLibCardNumber();
		
		try {
			bookService.reserveBook(member, book);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			fail(e.getMessage());
		}
		
		// Check reserve list has been updated
		List<LibraryItem> reserved = member.getReserved();
		
		// Should be 1 if service is working properly
		assertEquals(1, reserved.size()); // one item in member reserve list (new book reservation)
		assertEquals(book.getlibraryItemID(), reserved.get(0).getlibraryItemID()); // The item in members reserved items list is the book
		assertEquals(LibraryItem.ItemStatus.Reserved, reserved.get(0).getItemStatus()); // Library item book has right "Reserved" Library Item status
	}
	
	@Test
	public void testReserveBookNullInputs() {
		String error = null;
		Book book = null;
		Member member = null;
		//Long bookId = null;
		//Long memberId = null;
		
		try {
			bookService.reserveBook(member, book);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertEquals("Arguments must not be null.", error);
	}
}
