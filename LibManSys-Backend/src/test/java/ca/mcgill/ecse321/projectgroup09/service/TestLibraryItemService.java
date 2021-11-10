package ca.mcgill.ecse321.projectgroup09.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LoanRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Movie;

@ExtendWith(MockitoExtension.class)
public class TestLibraryItemService {
	
	@Mock
	private LibraryItemRepository libraryItemRepo;
	
	@Mock
	private MemberRepository memberRepo;
	
	@Mock
	private LibrarianRepository librarianRepo;
	
	@Mock
	private LoanRepository loanRepo;
	
	@InjectMocks
	private LibraryItemService libraryItemService;
	
	// Mock attributes
	// LI1 = LibraryItem 1 (Book)
	private static final Long LI1_ID = 901L;
	private static final ItemStatus LI1_STATUS = ItemStatus.Available;
	private static final String LI1_TITLE = "Of Mice and Men";
	
	// LI2 = LibraryItem 2 (Movie)
	private static final Long LI2_ID = 902L;
	private static final String LI2_TITLE = "Iron Man";
	private static final ItemStatus LI2_STATUS = ItemStatus.Reserved;
	
	// LI3 = LibraryItem 3 (Music Album)
	private static final Long LI3_ID = 903L;
	// TODO
	
	// M1 = Member 1
	private static final Long M1_ID = 800L;
	private static final int M1_ACTIVE_LOANS = 1;
	
	// L1 = Librarian 1
	private static final Long L1_ID = 850L;

	// invalid ID
	private static final Long NAN_ID = -1L;
	// invalid title
	private static final String NAN_TITLE = "";
	
	// Setup mocks
	@BeforeEach
	public void setMockOutput() {
		// setup mock library item to be checked out
		lenient().when(libraryItemRepo.findLibraryItemByLibraryItemID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LI1_ID)) {
	            LibraryItem li = new Book();
	            li.setlibraryItemID(LI1_ID);
	            li.setItemStatus(LI1_STATUS);
	            li.setTitle(LI1_TITLE);
	            //li.setMember(null);
	            return li;
	        } else if (invocation.getArgument(0).equals(LI2_ID)) {
	        	LibraryItem li = new Movie();
	            li.setlibraryItemID(LI2_ID);
	            li.setItemStatus(LI2_STATUS);
	            li.setTitle(LI2_TITLE);
	            //li.setMember(null);
	            return li;
	        } else if (invocation.getArgument(0).equals(LI3_ID)) {
	        	// TODO
	        	return null;
	        } else {
	            return null;
	        }
	    });
		// add response to return all books with title 'LI1_TITLE' when requested
		lenient().when(libraryItemRepo.findLibraryItemByTitle(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LI1_TITLE)) {
				ArrayList<LibraryItem> lis = new ArrayList<LibraryItem>();
				// Same book as above
				Book book = new Book();
				book.setlibraryItemID(LI1_ID);
	            book.setItemStatus(LI1_STATUS);
	            book.setTitle(LI1_TITLE);
				// add all books with title to list and return it
	            lis.add(book);
				return lis;
			} else {
				return new ArrayList<LibraryItem>();
			}
		});
		// Setup mock member
		lenient().when(memberRepo.findMemberByLibCardNumber(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(M1_ID)) {
	            Member member = new Member();
	            member.setLibCardNumber(M1_ID);
	            member.setActiveLoans(M1_ACTIVE_LOANS);
	            return member;
	        } else {
	            return null;
	        }
	    });
		// Setup mock member
		lenient().when(librarianRepo.findLibrarianByEmployeeIDNum(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(L1_ID)) {
	            Librarian l = new Librarian();
	            l.setemployeeIDNum(L1_ID);
	            return l;
	        } else {
	            return null;
	        }
	    });
		
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(memberRepo.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(librarianRepo.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(libraryItemRepo.save(any(LibraryItem.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(loanRepo.save(any(Loan.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@Test
	public void testGetLibraryItemById_Existing() {
		Book book = (Book) libraryItemService.getLibraryItemById(LI1_ID);
		assertNotNull(book);
		assertEquals(LI1_ID, book.getlibraryItemID());
		assertEquals(LI1_TITLE, book.getTitle());
	}
	
	@Test
	public void testGetLibraryItemById_NotExisting() {
		Book book = (Book) libraryItemService.getLibraryItemById(NAN_ID);
		assertNull(book);
	}
	
	@Test
	public void testGetLibraryItemsByTitle() {
		List<LibraryItem> lis = libraryItemService.getLibraryItemsByTitle(LI1_TITLE);
		assertEquals(1, lis.size());
		assertEquals(LI1_TITLE, lis.get(0).getTitle());
	}
	
	@Test
	public void testGetLibraryItemsByTitle_NonExistingTitle() {
		List<LibraryItem> lis = libraryItemService.getLibraryItemsByTitle(NAN_TITLE);
		assertEquals(0, lis.size());
	}
	
	@Test
	public void testDeleteLibraryItem() {
		Book book = (Book) libraryItemRepo.findLibraryItemByLibraryItemID(LI1_ID);
		boolean deleted = libraryItemService.deleteLibraryItem(book);
		assertEquals(true, deleted);
	}
	
	@Test
	public void testDeleteLibraryItem_NotExisting() {
		Book book = (Book) libraryItemRepo.findLibraryItemByLibraryItemID(NAN_ID);
		boolean deleted = libraryItemService.deleteLibraryItem(book);
		assertEquals(false, deleted);
	}
	
	@Test
	public void testReserveLibraryItem_Book() {		
		Book book = (Book) libraryItemRepo.findLibraryItemByLibraryItemID(LI1_ID);
		Member member = memberRepo.findMemberByLibCardNumber(M1_ID);
		
		try {
			libraryItemService.reserveLibraryItem(member, book);
		} catch (Exception e) {
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
	public void testReserveLibraryItem_NullInputs() {
		String error = null;
		LibraryItem li = null;
		Member member = null;
		
		try {
			libraryItemService.reserveLibraryItem(member, li);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertEquals("Arguments must not be null.", error);
	}
	
	@Test
	public void testReserveLibraryItem_AlreadyReserved() {		
		Movie movie = (Movie) libraryItemRepo.findLibraryItemByLibraryItemID(LI2_ID);
		Member member = memberRepo.findMemberByLibCardNumber(M1_ID);
		String error = null;
		
		try {
			libraryItemService.reserveLibraryItem(member, movie);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		// Check reserve list has not been updated
		List<LibraryItem> reserved = member.getReserved();
		
		// Should be 1 if service is working properly
		assertEquals(0, reserved.size()); // one item in member reserve list (new book reservation)
		assertNotNull(error);
		assertEquals("Cannot reserve a library item that is already being reserved by another member.", error);
	}
	
	@Test
	public void testCheckoutLibraryItem() {
		LibraryItem li = libraryItemRepo.findLibraryItemByLibraryItemID(LI1_ID);
		Member m = memberRepo.findMemberByLibCardNumber(M1_ID);
		Librarian l = librarianRepo.findLibrarianByEmployeeIDNum(L1_ID);
		
		// attempt to checkout book
		try {
			libraryItemService.checkoutLibraryItem(l, m, li);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals(M1_ACTIVE_LOANS + 1, m.getActiveLoans());
		assertEquals(1, l.getLoans().size());
		assertEquals(1, m.getLoans().size());
		assertEquals(ItemStatus.CheckedOut, li.getItemStatus());
		assertEquals(l.getLoans().get(0), m.getLoans().get(0));
	}
	
	@Test
	public void testCheckoutLibraryItem_NullInputs() {
		LibraryItem li = null;
		Member m = null;
		Librarian l = null;
		String error = null;
		// attempt to checkout book
		try {
			libraryItemService.checkoutLibraryItem(l, m, li);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertEquals("Arguments must not be null.", error);
	}
}
