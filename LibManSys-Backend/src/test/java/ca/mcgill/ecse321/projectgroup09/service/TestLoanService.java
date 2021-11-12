package ca.mcgill.ecse321.projectgroup09.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup09.dao.LoanRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.dao.BookRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Movie;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;

/**
 * author: Rajaa
 */
@ExtendWith(MockitoExtension.class)
public class TestLoanService {

	@Mock
	private LoanRepository loanRepository;

	@Mock
	private LibraryItemRepository libraryItemRepository;
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private MemberRepository memberRepository;

	@Mock
	private LibrarianRepository librarianRepository;

	
	@InjectMocks
	private LoanService loanService;


	 private static final Date BORROWEDDATE = Date.valueOf("2020-02-02");
	// new Date( 2020, 1, 2);
	 private static final Date RETURNEDDATE = Date.valueOf("2020-03-03");
	 private static final Double LATEFEES = 0.10;
	 private static final Double OVERDUEFEE = 0.20;

	 private static final Long LOANID = (long) 9999999;
	 private static final Long LIBRARYITEMID = (long) 9999999;

	 private static final Long MEMBERID = (long) 11111;
	 private static final Long LIBRARIANID = (long) 22222;
	 private static final Long LIBRARIANITEMID = (long) 333333;
	 private static final	LoanStatus LOANSTATUS = LoanStatus.Overdue;
	 
		private static final long EMPLOYEE_ID = 123456789; //librarian ID
		private static final String LIBRARIAN_EMAIL = "librarian@email.com";
		private static final String LIBRARIAN_PASSWORD = "ASDF12343";
		private static final String LIBRARIAN_USERNAME = "username";
		private static final String LIBRARIAN_FULLNAME = "Test Librarian";

		private static final long LIB_CARD_NO = 999999999; //member ID 
		private static final String MEM_ADDRESS = "123 Test Address";
		private static final double AMOUNT_OWED = 0; 
		private static final int ACTIVE_LOANS = 0; 
		private static final boolean IS_VERIFIED_RESIDENT = true; 
		private static final String FULL_NAME = "Test Member";
		private static final String PHONE_NO = "123-456-7890";



	// Loan Associations
	//private LibraryItem libraryItem;
	//private Member member;
	//private Librarian librarian;

	
	@BeforeEach
	public void setMockOutput() { 
		
		 lenient().when(loanRepository.findLoanByLoanID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
	        	if (invocation.getArgument(0).equals(LOANID)) {
		        	Loan loan = new Loan();
		        	Librarian librarian = new Librarian();
		        	Member member = new Member();
		        	Book book = new Book();
		            loan.setBorrowedDate(BORROWEDDATE);
		            loan.setLibrarian(librarian);
		            loan.setLibraryItem(book);
		            loan.setloanID(LOANID);
		            loan.setMember(member);
		            return loan;
	        	}
	        	else {return null;
	        	}
	        	});
	        	
	    	/*	lenient().when(librarianRepository.findLibrarianByEmployeeIDNum(anyLong())).thenAnswer((InvocationOnMock invocation) -> { 
	    			if (invocation.getArgument(0).equals(EMPLOYEE_ID)) {
	    				Librarian librarian = new Librarian(); 
	    				librarian.setemployeeIDNum(EMPLOYEE_ID);
	    				librarian.setFullName(LIBRARIAN_FULLNAME); 
	    				librarian.setLibrarianEmail(LIBRARIAN_EMAIL);
	    				librarian.setLibrarianPassword(LIBRARIAN_PASSWORD);
	    				librarian.setLibrarianUsername(LIBRARIAN_USERNAME);
	    				return librarian;
	    			}
	    			else {
	    				return null;
	    			}

	    		});
	    		lenient().when(memberRepository.findMemberByLibCardNumber(anyLong())).thenAnswer((InvocationOnMock invocation) -> { 
	    			if (invocation.getArgument(0).equals(LIB_CARD_NO)) {
	    				Member member = new Member();
	    				member.setLibCardNumber(LIB_CARD_NO);
	    				member.setAddress(MEM_ADDRESS);
	    				member.setActiveLoans(ACTIVE_LOANS);
	    				member.setAmountOwed(AMOUNT_OWED);
	    				member.setFullName(FULL_NAME);
	    				member.setIsVerifiedResident(IS_VERIFIED_RESIDENT);
	    				member.setPhoneNumber(PHONE_NO);
	    				return member;
	    			}
	    			else {
	    				return null; 
	    			}

	    		});*/

		      
		 
			Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
				return invocation.getArgument(0);
			};
	        lenient().when(libraryItemRepository.save(any(LibraryItem.class))).thenAnswer(returnParameterAsAnswer);
			lenient().when(memberRepository.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);
			lenient().when(librarianRepository.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);

	}
	
@Test
public void testCreateLoan() {
	Loan loan1 = null;

	try {
	 
		loan1 = loanService.createLoan(BORROWEDDATE, LOANID, MEMBERID, LIBRARIANID, LIBRARIANITEMID);
	}
	catch (Exception e) {
		fail(e.getMessage());
	}
	assertNotNull(loan1);
	assertEquals(BORROWEDDATE, loan1.getBorrowedDate());
	assertEquals(LOANID, loan1.getLoanID());
}

@Test
public void testCreateLoanWithNoBorrowedDate() {
	String error = null;
	Loan loan1 = null;

	Date borrowedDate = null;
	try {
	 
		loan1 = loanService.createLoan(borrowedDate, LOANID, MEMBERID, LIBRARIANID, LIBRARIANITEMID);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(loan1);
	assertNotNull(error);

}

@Test
public void testCreateLoanWithNoMember() {
	String error = null;
	Loan loan1 = null;
	Long memberId = null;
	try {
	 
		loan1 = loanService.createLoan(BORROWEDDATE, LOANID, memberId, LIBRARIANID, LIBRARIANITEMID);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(loan1);
	assertNotNull(error);

}

@Test
public void testCreateLoanWithNoLibrarian() {
	String error = null;
	Loan loan1 = null;
	Long Librarian = null;
	try {
	 
		loan1 = loanService.createLoan(BORROWEDDATE, LOANID, MEMBERID, Librarian, LIBRARIANITEMID);}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(loan1);
	assertNotNull(error);

}

@Test
public void testCreateLoanWithNoID() {
	String error = null;
	Loan loan1 = null;
	Long loanId = null;
	try {
	 
		loan1 = loanService.createLoan(BORROWEDDATE, loanId, MEMBERID, LIBRARIANID, LIBRARIANITEMID);}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(loan1);
	assertNotNull(error);

}

@Test
public void testCreateLoanWithNoLibraryItemID() {
	String error = null;
	Loan loan1 = null;
	Long libraryItemId = null;
	try {
	 
		loan1 = loanService.createLoan(BORROWEDDATE, LOANID, MEMBERID, LIBRARIANID, libraryItemId);}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(loan1);
	assertNotNull(error);

}

@Test
public void testGetLoan() {
	Loan newLoan = loanService.getLoanbyId(LOANID);
	assertNotNull(newLoan);
	
	assertEquals(LOANID, newLoan.getLoanID());
	assertEquals(BORROWEDDATE, newLoan.getBorrowedDate());

}

@Test
public void testGetLoanFail() {
	Long loanId = (long) -1;
	String error =null;
	Loan newLoan = null;
	try {
	 newLoan = loanService.getLoanbyId(loanId);}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(newLoan);
	

}


@Test
public void testUpdateLoan() {
	//Loan loan = loanRepository.findLoanByLoanID(loanID);
	
	
	Loan newLoan = null;
	try {
		
		newLoan = loanService.updateLoan(BORROWEDDATE, RETURNEDDATE, LATEFEES, LOANSTATUS, LOANID);}
	catch (Exception e) {
		fail(e.getMessage());
	}
	assertNotNull(newLoan);
	assertEquals(BORROWEDDATE, newLoan.getBorrowedDate());
	assertEquals(RETURNEDDATE, newLoan.getReturnDate());
	assertEquals(LATEFEES, newLoan.getLateFees());
	assertEquals(LOANSTATUS, newLoan.getLoanStatus());
	assertEquals(LOANID, newLoan.getLoanID());
}


@Test
public void testUpdateLoanNoBorrowedDate() {
	//Loan loan = loanRepository.findLoanByLoanID(loanID);
	
	String error = null;
	Loan newLoan = null;
	Date borrowedDate = null;
	try {
		
		newLoan = loanService.updateLoan(borrowedDate, RETURNEDDATE, LATEFEES, LOANSTATUS, LOANID);}
	catch (Exception e) {
		error = e.getMessage();
	}
	assertNull(newLoan);
	assertNotNull(error);
	
}


@Test
public void testUpdateLoanReturnDatebeforeBorrowedDate() {
	//Loan loan = loanRepository.findLoanByLoanID(loanID);
	
	String error = null;
	Loan newLoan = null;
	Date borrowedDate = new Date (2021-01-01);
	Date returnedDate = new Date (2020-01-01);
	try {
		newLoan = loanService.updateLoan(borrowedDate, returnedDate, LATEFEES, LOANSTATUS, LOANID);}
	catch (Exception e) {
		error = e.getMessage();
	}
	assertNull(newLoan);
	assertNotNull(error); 
	
}


@Test
public void testUpdateLoanNoId() {
	//Loan loan = loanRepository.findLoanByLoanID(loanID);
	
	String error = null;
	Loan newLoan = null;
	Long id = null;
	try {
		newLoan = loanService.updateLoan(BORROWEDDATE, RETURNEDDATE, LATEFEES, LOANSTATUS, id);}
	catch (Exception e) {
		error = e.getMessage();
	}
	assertNull(newLoan);
	assertNotNull(error);
	
}


@Test
public void testUpdateNegativeFees() {
	//Loan loan = loanRepository.findLoanByLoanID(loanID);
	
	String error = null;
	Loan newLoan = null;
	Double fees = -0.1;
	try {
		
		newLoan = loanService.updateLoan(BORROWEDDATE, RETURNEDDATE, fees, LOANSTATUS, LOANID);}
	catch (Exception e) {
		error = e.getMessage();
	}
	assertNull(newLoan);
	assertNotNull(error);
	
}
@Test
public void testDeleteLoan() {
	Loan loan = null;
	boolean delete =false;
	try {
	 	
		 delete = loanService.deleteLoan(LOANID);
	}
	catch (Exception e) {
		fail(e.getMessage());
	}
	//assertNotNull(loan);
	assertEquals(true, delete);
}

@Test
public void testDeleteLoanFail() {
	Loan loan = null;
	boolean delete =false;
	Long loanId = null;
	String error = null;
	try {
	 	
		 delete = loanService.deleteLoan(loanId);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	//assertNotNull(loan);
	assertEquals(false, delete);
	assertNotNull(error);

}
@Test
public void testAddLateFee() {
	Loan newLoan = null;
	String error = null;
	double overdueFee = 0.1;
	try {
	 	
		newLoan = loanService.addLateFee(LIBRARYITEMID,LOANID, OVERDUEFEE, LOANSTATUS);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	//assertNotNull(loan);
	//assertEquals(false, delete);
	assertNotNull(newLoan);

}

@Test
public void testAddLateFeeNotActive() {
	Loan newLoan = null;
	String error = null;
	LoanStatus loanStatus = LoanStatus.Closed;
	try {
	 	
		newLoan = loanService.addLateFee(LIBRARYITEMID,LOANID, OVERDUEFEE, loanStatus);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	//assertNotNull(loan);
	//assertEquals(false, delete);
	assertNull(newLoan);

}

@Test
public void testChangeLateFee() {
	Loan newLoan = null;
	String error = null;
	try {
	 	
		newLoan = loanService.changeLateFee(LOANID, LATEFEES);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNotNull(newLoan);
	//assertEquals(false, delete);
//	assertNotNull(error);



}

@Test
public void testChangeLoanStatus() {
	Loan newLoan = null;
	String error = null;
	try {
	 	
		newLoan = loanService.changeLoanStatus(LOANID, LIBRARYITEMID, LOANSTATUS);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNotNull(newLoan);
	//assertEquals(false, delete);
//	assertNotNull(error);



}

@Test
public void testChangeLoanStatusNoLoanId() {
	Loan newLoan = null;
	String error = null;
	Long id = null;
	try {
	 	
		newLoan = loanService.changeLoanStatus(id, LIBRARYITEMID, LOANSTATUS);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(newLoan);
	assertNotNull(error);
;
	//assertEquals(false, delete);
//	assertNotNull(error);

}

@Test
public void testChangeLoanStatusNoLibraryItemId() {
	Loan newLoan = null;
	String error = null;
	Long id = null;
	try {
	 	
		newLoan = loanService.changeLoanStatus(LOANID, id, LOANSTATUS);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(newLoan);
	assertNotNull(error);

}


@Test
public void testGetLoanFeesbyMember() {
	Member member = new Member();
	//Long memberId = member.getLibCardNumber();
	double newLoan = 0 ;
	String error = null;
	try {
	 	
		newLoan = loanService.getLoanFeesbyMember(MEMBERID);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNotNull(newLoan);

}

@Test
public void testGetLoanFeesbyNonMember() {
	Double newLoan = null;
	String error = null;
	Long id = null;
	try {
	 	
		newLoan = loanService.getLoanFeesbyMember(id);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(newLoan);
	assertNotNull(error);


}

@Test
public void testGetLoansbyMember() {
	List<Loan> newLoan = null;
	String error = null;
	Long id = null;
	try {
	 	
		newLoan = loanService.getLoansbyMember(MEMBERID);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNotNull(newLoan);


}

@Test
public void testGetLoansbyNonMember() {
	List<Loan> newLoan = null;
	String error = null;
	Long id = null;
	try {
	 	
		newLoan = loanService.getLoansbyMember(id);
	}
	catch (Exception e) {
		error = (e.getMessage());
	}
	assertNull(newLoan);
	assertNotNull(error);


}


}
