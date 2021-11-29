package ca.mcgill.ecse321.projectgroup09.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;

import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LoanRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Archive;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Movie;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;

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
	private static final String LI3_TITLE = "MONTERO";
	private static final ItemStatus LI3_STATUS = ItemStatus.CheckedOut;
	
	// LI4 = LibraryItem 4 (Archive)
	private static final Long LI4_ID = 904L;
	private static final String LI4_TITLE = "ARchive title?? wtf are archives called";
	private static final ItemStatus LI4_STATUS = ItemStatus.LibraryOnly;
	
	/** LI5 = Library Item 5 (Book) for renew test */
	private static final Long LI5_ID = 905L;
	private static final String LI5_TITLE = "Stewart Calculus";
	private static final ItemStatus LI5_STATUS = ItemStatus.CheckedOut;
	
	/** LI6 - library item 6 (MusicAlbum) for renew with overdue loan */
	private static final Long LI6_ID = 906L;
	private static final String LI6_TITLE = "The Life of Pablo";
	private static final ItemStatus LI6_STATUS = ItemStatus.CheckedOut;
	
	/** LI7 - library item 7 (Book) (corresponds to M7 and L1 and LOAN7) for return test */
	private static final Long LI7_ID = 907L;
	private static final String LI7_TITLE = "Lord of the Flies";
	private static final ItemStatus LI7_STATUS = ItemStatus.CheckedOut;
	
	/** LI8 - library item 8 (Movie) (return overdue fee test) */
	private static final Long LI8_ID = 908L;
	private static final String LI8_TITLE = "Jurassic Park";
	private static final ItemStatus LI8_STATUS = ItemStatus.CheckedOut;
	// Movie: loanable period: 7, daily over due fee: 0.5
	
	
	/** Loan 5 - for test renew  - corresponds with member 5 and li5 */
	//private static final Long LOAN5_ID = 705L;
	private static final LoanStatus LOAN5_STATUS = LoanStatus.Active;
	
	/** Loan 6 - for test renew with overdue loan */
	//private static final Long LOAN6_ID = 706L;
	private static final LoanStatus LOAN6_STATUS = LoanStatus.Overdue;
	private static final Date LOAN6_BORROWED_DATE = Date.valueOf("2021-10-1");
	
	/** Loan 7 - see LI7 */
	//private static final Long LOAN7_ID = 707L;
	private static final LoanStatus LOAN7_STATUS = LoanStatus.Active;
	
	/** Loan 8 - see LI8 */
	//private static final Long LOAN8_ID = 708L;
	private static final LoanStatus LOAN8_STATUS = LoanStatus.Overdue; // active would work too I think
	private static final Date LOAN8_BORROWED_DATE = Date.valueOf("2021-11-1");
	
	
	// M1 = Member 1
	private static final Long M1_ID = 801L;
	private static final int M1_ACTIVE_LOANS = 0;
	
	// M2 = Member 2
	private static final Long M2_ID = 802L;
	private static final int M2_ACTIVE_LOANS = 10;
	
	/** M5 = Member 5 (renew test) */
	private static final Long M5_ID = 805L;
	private static final int M5_ACTIVE_LOANS = 1;
	
	/** M6 = Member 6 (renew overdue loan) */
	private static final Long M6_ID = 806L;
	private static final int M6_ACTIVE_LOANS = 1;
	
	/** M7 - see LI7 */
	private static final Long M7_ID = 807L;
	private static final int M7_ACTIVE_LOANS = 1;
	
	/** M8 - see LI8 */
	private static final Long M8_ID = 808L;
	private static final int M8_ACTIVE_LOANS = 1;
	
	
	// L1 = Librarian 1
	private static final Long L1_ID = 851L;
	
	
	// invalid ID
	private static final Long NAN_ID = -1L;
	// invalid title
	private static final String NAN_TITLE = "";
	
	private static final Date TODAY = Date.valueOf(LocalDate.now());
	private static final Date YESTERDAY = new Date(TODAY.getTime() - TimeUnit.DAYS.toMillis(1)); 
	
	// Setup mocks
	@BeforeEach
	public void setMockOutput() {
		// renew test
		Member m5 = new Member();
		ReflectionTestUtils.setField(m5, "accountID", M5_ID);
		m5.setLibCardNumber(M5_ID);
		m5.setActiveLoans(M5_ACTIVE_LOANS);
		LibraryItem li5 = new Book();
		ReflectionTestUtils.setField(li5, "libraryItemID", LI5_ID);
    	li5.setTitle(LI5_TITLE);
    	li5.setItemStatus(LI5_STATUS);
    	//li.setMember(null);
    	Loan loan5 = new Loan();
    	//loan5.setloanID(LOAN5_ID);
    	loan5.setLoanStatus(LOAN5_STATUS);
    	loan5.setBorrowedDate(YESTERDAY);
    	loan5.setLibraryItem(li5);
    	loan5.setMember(m5);
    	List<Loan> loanList = Arrays.asList(loan5);
    	li5.setLoans(loanList);
    	m5.setLoans(loanList);
    	
    	// renew overdue loan test
    	Member m6 = new Member();
		ReflectionTestUtils.setField(m6, "accountID", M6_ID);
    	m6.setLibCardNumber(M6_ID);
    	m6.setActiveLoans(M6_ACTIVE_LOANS);
    	LibraryItem li6 = new MusicAlbum();
    	ReflectionTestUtils.setField(li6, "libraryItemID", LI6_ID);
    	li6.setTitle(LI6_TITLE);
    	li6.setItemStatus(LI6_STATUS);
    	Loan loan6 = new Loan();
    	//loan6.setloanID(LOAN6_ID);
    	loan6.setLoanStatus(LOAN6_STATUS);
    	loan6.setBorrowedDate(LOAN6_BORROWED_DATE);
    	loan6.setLibraryItem(li6);
    	loan6.setMember(m6);
    	loanList = Arrays.asList(loan6);
    	li6.setLoans(loanList);
    	m6.setLoans(loanList);
    	
    	// return test
    	// library item 7 (corresponds to M7 and L1 and LOAN5) 
    	Member m7 = new Member();
		ReflectionTestUtils.setField(m7, "accountID", M7_ID);
    	m7.setLibCardNumber(M7_ID);
    	m7.setActiveLoans(M7_ACTIVE_LOANS);
    	LibraryItem li7 = new Book();
    	ReflectionTestUtils.setField(li7, "libraryItemID",LI7_ID);
    	li7.setTitle(LI7_TITLE);
    	li7.setItemStatus(LI7_STATUS);
    	Loan loan7 = new Loan();
    	//loan7.setloanID(LOAN7_ID);
    	loan7.setLoanStatus(LOAN7_STATUS);
    	loan7.setBorrowedDate(YESTERDAY);
    	loan7.setLibraryItem(li7);
    	loan7.setMember(m7);
    	loanList = Arrays.asList(loan7);
    	li7.setLoans(loanList);
    	m7.setLoans(loanList);
    	
    	// return test for overdue fee
    	Member m8 = new Member();
		ReflectionTestUtils.setField(m8, "accountID", M8_ID);
		m8.setLibCardNumber(M8_ID);
    	m8.setActiveLoans(M8_ACTIVE_LOANS);
    	LibraryItem li8 = new Movie();
    	ReflectionTestUtils.setField(li8, "libraryItemID", LI8_ID);
    	li8.setTitle(LI8_TITLE);
    	li8.setItemStatus(LI8_STATUS);
    	Loan loan8 = new Loan();
    	//loan8.setloanID(LOAN8_ID);
    	loan8.setLoanStatus(LOAN8_STATUS);
    	loan8.setBorrowedDate(LOAN8_BORROWED_DATE);
    	loan8.setLibraryItem(li8);
    	loan8.setMember(m8);
    	loanList = Arrays.asList(loan8);
    	li8.setLoans(loanList);
    	m8.setLoans(loanList);
		
		// setup mock library item to be checked out
		lenient().when(libraryItemRepo.findLibraryItemByLibraryItemID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(LI1_ID)) {
	            LibraryItem li = new Book();
	            ReflectionTestUtils.setField(li, "libraryItemID", LI1_ID);
	            li.setItemStatus(LI1_STATUS);
	            li.setTitle(LI1_TITLE);
	            //li.setMember(null);
	            return li;
	        } else if (invocation.getArgument(0).equals(LI2_ID)) {
	        	LibraryItem li = new Movie();
	            ReflectionTestUtils.setField(li, "libraryItemID", LI2_ID);
	            li.setItemStatus(LI2_STATUS);
	            li.setTitle(LI2_TITLE);
	            //li.setMember(null);
	            return li;
	        } else if (invocation.getArgument(0).equals(LI3_ID)) {
	        	LibraryItem li = new MusicAlbum();
	            ReflectionTestUtils.setField(li, "libraryItemID", LI3_ID);
	        	li.setTitle(LI3_TITLE);
	        	li.setItemStatus(LI3_STATUS);
	        	//li.setMember(null);
	        	return li;
	        } else if (invocation.getArgument(0).equals(LI4_ID)) {
	        	LibraryItem li = new Archive();
	            ReflectionTestUtils.setField(li, "libraryItemID", LI4_ID);
	            li.setTitle(LI4_TITLE);
	        	li.setItemStatus(LI4_STATUS);
	        	//li.setMember(null);
	        	return li;
	        } else if (invocation.getArgument(0).equals(LI5_ID)) {
	        	// created above
	        	return li5;
	        } else if (invocation.getArgument(0).equals(LI6_ID)) {
	        	// created above
	        	return li6;
	        } else if (invocation.getArgument(0).equals(LI7_ID)) {
	        	// created above
	        	return li7;
	        } else if (invocation.getArgument(0).equals(LI8_ID)) {
	        	// created above
	        	return li8;
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
				//LI1_ID = book.setlibraryItemID();
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
	        } else if(invocation.getArgument(0).equals(M2_ID)) {
	            Member member = new Member();
	            member.setLibCardNumber(M2_ID);
	            member.setActiveLoans(M2_ACTIVE_LOANS);
	            return member;
	        } else if(invocation.getArgument(0).equals(M5_ID)) {
	            return m5;
	        } else if(invocation.getArgument(0).equals(M6_ID)) {
	            return m6;
	        } else if(invocation.getArgument(0).equals(M7_ID)) {
	            return m7;
	        } else if(invocation.getArgument(0).equals(M8_ID)) {
	            return m8;
	        } else {
	            return null;
	        }
	    });
		// Setup mock member
		lenient().when(librarianRepo.findLibrarianByEmployeeIDNumber(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
	        if(invocation.getArgument(0).equals(L1_ID)) {
	            Librarian l = new Librarian();
	            l.setemployeeIDNumber(L1_ID);
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
		String error = null;
		Book book = null;
		try {
			book = (Book) libraryItemService.getLibraryItemById(NAN_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(book);
		assertNotNull(error);
		assertEquals(error,"Library item with id " + NAN_ID + " does not exist.");
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
			book = (Book) libraryItemService.reserveLibraryItem(M1_ID, LI1_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		// get updated info
		member = book.getMember();
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
		try {
			libraryItemService.reserveLibraryItem(null, null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertEquals("Arguments must not be null.", error);
	}
	
	@Test
	public void testReserveLibraryItem_AlreadyReserved() {		
		//Movie movie = (Movie) libraryItemRepo.findLibraryItemByLibraryItemID(LI2_ID);
		Member member = memberRepo.findMemberByLibCardNumber(M1_ID);
		String error = null;
		
		try {
			libraryItemService.reserveLibraryItem(M1_ID, LI2_ID);
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
		Librarian l = librarianRepo.findLibrarianByEmployeeIDNumber(L1_ID);
		
		// attempt to checkout book
		Loan loan = null;
		try {
			loan = libraryItemService.checkoutLibraryItem(L1_ID, M1_ID, LI1_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		// reset li,l,m with references from loan bcause not actually saving in test
		li = loan.getLibraryItem();
		m = loan.getMember();
		l = loan.getLibrarian();
		assertNotNull(loan);
		assertEquals(M1_ACTIVE_LOANS + 1, m.getActiveLoans());
		assertEquals(1, l.getLoans().size());
		assertEquals(1, m.getLoans().size());
		assertEquals(ItemStatus.CheckedOut, li.getItemStatus());
		assertEquals(l.getLoans().get(0), m.getLoans().get(0));
		// return date is set when library item is returned
		assertEquals(null, loan.getReturnDate());
		assertEquals(TODAY , loan.getBorrowedDate());
	}
	
	@Test
	public void testCheckoutLibraryItem_NullInputs() {
		String error = null;
		// attempt to checkout book
		try {
			libraryItemService.checkoutLibraryItem(null, null, null);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertEquals("Arguments must not be null.", error);
	}
	
	@Test
	public void testCheckoutLibraryItem_AlreadyCheckedOut() {
		String error = null;
		try {
			libraryItemService.checkoutLibraryItem(L1_ID, M1_ID, LI3_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertEquals("Can not checkout a library item that is already checked out.", error);
	}
	
	@Test
	public void testCheckoutLibraryItem_LibraryOnly() {
		// try to checkout a archive (not allowed because library only item)
		String error = null;
		try {
			libraryItemService.checkoutLibraryItem(L1_ID, M1_ID, LI4_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertEquals("Cannot checkout library item because it is for library use only.", error);
	}
	
	@Test
	public void testCheckoutLibraryItem_MaxCheckedOut() {
		// try to checkout a library item, but member has already checked out 10 library items
		String error = null;
		try {
			libraryItemService.checkoutLibraryItem(L1_ID, M2_ID, LI1_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNotNull(error);
		assertEquals("Cannot checkout library item, member has reached maximum number of library items allowed to be borrowed at a time (10).", error);
	}
	
	@Test
	public void testRenewLibraryItem() {
		Loan l = null;
		try {
			l = libraryItemService.renewLibraryItem(M5_ID, LI5_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(l);
		assertEquals(LoanStatus.Renewed, l.getLoanStatus());
		assertEquals(TODAY, l.getBorrowedDate());
	}
	
	@Test
	public void testRenewLibraryItem_Overdue() {
		// try to renew overdue loan (not allowed)
		String error = null;
		Loan l = null;
		try {
			l = libraryItemService.renewLibraryItem(M6_ID, LI6_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(l);
		assertNotNull(error);
		assertEquals("Cannot renew library item that is already overdue.", error);
	}
	
	@Test
	public void testRenewLibraryItem_NotCheckedOut() {
		// try to renew a library item that user does not have checked out
		String error = null;
		Loan loan = null;
		try {
			loan = libraryItemService.renewLibraryItem(M1_ID, LI1_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(loan);
		assertNotNull(error);
		assertEquals("Cannot renew library item that is not currently checked out.", error);
	}
	
	@Test
	public void testRenewLibraryItem_CheckedOutOtherMember() {
		// try to renew a library item that user does not have checked out
		String error = null;
		Loan l = null;
		try {
			// try to renew with not matching member and library item
			l = libraryItemService.renewLibraryItem(M5_ID, LI6_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(l);
		assertNotNull(error);
		assertEquals("Could not renew library item, not currently checked out by this member.", error);
	}

	@Test
	public void testReturnLibraryItem() {
		Loan loan = null;
		try {
			loan = libraryItemService.returnLibraryItem(M7_ID, LI7_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		LibraryItem li = loan.getLibraryItem();
		Member m = loan.getMember();
		assertNotNull(loan);
		assertEquals(LoanStatus.Closed, loan.getLoanStatus());
		assertEquals(TODAY, loan.getReturnDate());
		assertEquals(ItemStatus.Available, li.getItemStatus());
		assertEquals(0, m.getActiveLoans());
	}
	
	@Test
	public void testReturnLibraryItem_NotCheckedOut() {
		// try to renew a library item that member does not currently have checked out
		String error = null;
		Loan loan = null;
		try {
			// LI1 is ItemStatus.Available, so can't be returned
			loan = libraryItemService.returnLibraryItem(M1_ID, LI1_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(loan);
		assertNotNull(error);
		assertEquals("Cannot return library item that is not currently checked out.", error);
	}
	
	@Test
	public void testReturnLibraryItem_CheckedOutOtherMember() {
		// try to renew a library item that is currently checked out by another member
		String error = null;
		Loan loan = null;
		try {
			loan = libraryItemService.returnLibraryItem(M6_ID, LI7_ID);
		} catch (Exception e) {
			error = e.getMessage();
		}
		assertNull(loan);
		assertNotNull(error);
		assertEquals("Cannot return library item, not currently checked out by this member.", error);
	}
	
	@Test
	public void testReturnLibraryItem_OverdueFees() {
		// test the overdue fees are calculated correctly
		// calculate what fees should be, for Movie, loanable period = 7, daily fee = 0.5
		// Should be $2
		Date bd = LOAN8_BORROWED_DATE;
		Date dueDate = new Date(bd.getTime() + TimeUnit.DAYS.toMillis(7)); 
		//Duration d = Duration.between(dueDate.toLocalDate(), TODAY.toLocalDate());
		long daysLateInMillies = TODAY.getTime() - dueDate.getTime();
		long daysLate = TimeUnit.DAYS.convert(daysLateInMillies, TimeUnit.MILLISECONDS);
		double expectedFeesOwed = ( (double) daysLate) * 0.5;
		System.out.println("exfees: " + expectedFeesOwed);
		Loan loan = null;
		try {
			loan = libraryItemService.returnLibraryItem(M8_ID, LI8_ID);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(loan);
		// assert overdue fee is calculate properly
		assertEquals(expectedFeesOwed, loan.getLateFees());
		// not sure if returnLibraryItem should update member amout owed or not
		//assertEquals(expectedFeesOwed, loan.getMember().getAmountOwed());
		assertEquals(0, loan.getMember().getActiveLoans());
	}
}
