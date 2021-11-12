package ca.mcgill.ecse321.projectgroup09.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
	@SuppressWarnings("deprecation")
	private static final Date borrowedDate = new Date( 1999, 1, 2);
	@SuppressWarnings("deprecation")
	private  static final Date returnDate = new Date( 1999, 2, 2);
	private  static final double lateFees = 2;
	private  static final  LoanStatus loanStatus = LoanStatus.Active;
	private static final Long loanID = (long) 9999999;
	private static final Long librarianId = (long) 9999999;
	private static final String librarianName ="rajaa";
	private static final String librarianPass ="rajaa99";
	private static final String librarianEmail ="rajaabk@gmail.com";
	private static final String librarianUser ="rajaabk";




	// Loan Associations
	//private LibraryItem libraryItem;
	//private Member member;
	//private Librarian librarian;

	
	@BeforeEach
	public void setMockOutput() { 
		
		 lenient().when(loanRepository.findLoanByLoanID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
	        	if (invocation.getArgument(0).equals(loanID)) {
		        	Loan loan = new Loan();
		        	Librarian librarian = new Librarian();
		        	Member member = new Member();
		        	Book book = new Book();
		            loan.setBorrowedDate(borrowedDate);
		            loan.setLateFees(lateFees);
		            loan.setLibrarian(librarian);
		            loan.setLibraryItem(book);
		            loan.setloanID(loanID);
		            loan.setLoanStatus(loanStatus);
		            loan.setMember(member);
		            loan.setReturnDate(returnDate);
		            return loan;
	        	}
	        	else {return null;}

		        });

		 
		/* lenient().when(loanRepository.findLoanByLibrarian(any(Librarian.class))).thenAnswer((InvocationOnMock invocation) ->
		 {
			 figure out how
		 });*/
		 
		 //maybe mocks for member, a libraryitem and librarian
		 
			Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
				return invocation.getArgument(0);
			};
	        lenient().when(libraryItemRepository.save(any(LibraryItem.class))).thenAnswer(returnParameterAsAnswer);
			lenient().when(memberRepository.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);
			lenient().when(librarianRepository.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);

	}
	// Test Create Loan //
@Test
public void createLoan() {
	Loan loan1 = null;
	 Date borrowedDate = new Date( 1999, 1, 2);
	@SuppressWarnings("deprecation")
	Date returnDate = new Date( 1999, 2, 2);
	double lateFees = 2;
	LoanStatus loanStatus = LoanStatus.Active;
	Long loanID = (long) 9999999;
	Long memberId = (long) 11111;
	Long librarianId = (long) 22222;
	Long librarianItemId = (long) 333333;
	try {
	 
		loan1 = loanService.createLoan(borrowedDate, returnDate, lateFees, loanStatus, loanID, memberId, librarianId, librarianItemId);
	}
	catch (Exception e) {
		fail(e.getMessage());
	}
}

@Test
public void updateLoan() {
	//Loan loan = loanRepository.findLoanByLoanID(loanID);
	
	 Date borrowedDate = new Date( 1999, 1, 2);
	@SuppressWarnings("deprecation")
	Date returnDate = new Date( 1999, 2, 2);
	double lateFees = 2;
	LoanStatus loanStatus = LoanStatus.Active;
	Long memberId = (long) 11111;
	Long librarianId = (long) 22222;
	Long librarianItemId = (long) 333333;
	Loan newLoan = null;
	try {
		
		newLoan = loanService.updateLoan(borrowedDate, returnDate, lateFees, loanStatus, loanID, memberId, librarianId, librarianItemId);
	}
	catch (Exception e) {
		fail(e.getMessage());
	}
	assertEquals(borrowedDate, newLoan.getBorrowedDate());
	
}
	// Test Delete Loan //
@Test
public void deleteLoan() {
	Loan loan = null;
	boolean delete =false;
	try {
	 	
		 delete = loanService.deleteLoan(loanID);
	}
	catch (Exception e) {
		fail(e.getMessage());
	}
	//assertNotNull(loan);
	assertEquals(true, delete);
}
	// Test Add Late Fees //

	// Test Change Late Fees //

	// Test Change Loan Status //

}
