package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceOnlineMember {

	@Autowired 
	private BookRepository bookRepository;
	
	@Autowired
	private LibrarianRepository librarianRepository; 
	
	@Autowired 
	private LibraryItemRepository libraryItemRepository;

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private OnlineMemberRepository onlineMemberRepository; 

	@AfterEach
	public void clearAccountsDatabase() {
		loanRepository.deleteAll();
		librarianRepository.deleteAll();
		bookRepository.deleteAll();
		libraryItemRepository.deleteAll();
		onlineMemberRepository.deleteAll();
	}
	
	
	@Test
	public void testPersistAndLoadOnlineMember() {
		// Account Attributes 

		//Librarian Attributes
		String librarianFullName = "Test Librarian";
		String librarianEmail = "librarian@library.com";
		String librarianPassword = "testPassword";
		String librarianUsername = "aLibrarian";
		Long employeeIDNum = (long) 12345; 

		Librarian librarian = new Librarian();
		librarian.setFullName(librarianFullName);
		librarian.setLibrarianEmail(librarianEmail);
		librarian.setLibrarianPassword(librarianPassword);
		librarian.setLibrarianUsername(librarianUsername);
		librarian.setemployeeIDNumber(employeeIDNum);

		librarianRepository.save(librarian);

		OnlineMember om = new OnlineMember();

		om.setFullName("name name");
		om.setActiveLoans(1);
		om.setAddress("address");
		om.setAmountOwed(100);
		om.setBookings(new ArrayList<Booking>());

		om.setIsVerifiedResident(true);
		om.setLibCardNumber((long) 1000101002L);
		om.setPhoneNumber("1234567890");

		om.setMemberEmail("member email");
		om.setMemberPassword("member password");
		om.setMemberUsername("member username");
		onlineMemberRepository.save(om);
		
		//Book and Library Item Attributes
		Long libraryItemID = (long) 1; 
		String Title = "TestBook";
		int publishedYear = 2021;
		int loanablePeriod = 21; 
		double dailyOverdueFee = 50;
		ItemStatus itemStatus = ItemStatus.Available;

		String author = "Test Author";
		String publisher = "Test Publisher"; 
		String ISBN = "1234-5678-9101";
		int numPages = 200;

		Book book = new Book();
        ReflectionTestUtils.setField(book, "libraryItemID", libraryItemID);
		book.setTitle(Title);
		book.setPublishedYear(publishedYear);
		book.setLoanablePeriod(loanablePeriod);
		book.setDailyOverdueFee(dailyOverdueFee);
		book.setItemStatus(itemStatus);

		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setISBN(ISBN);
		book.setNumPages(numPages);

		// double save
		//bookRepository.save(book);
		book = libraryItemRepository.save(book);
		// get auto-set library id
		libraryItemID = book.getlibraryItemID();
		
		Loan loan = new Loan();
		loan.setMember(om);
		loan.setLateFees(10);
		loan.setLibrarian(librarian);
		loan.setLibraryItem(book);
		//loan.setloanID(12312L);
		// loans id's are generated when loan object is first saved to repo, 
		// so just get the value after saving it
		loan.setLoanStatus(LoanStatus.Active);
		loan.setBorrowedDate(Date.valueOf(LocalDate.of(2021, 10, 11)));
		loan.setReturnDate(Date.valueOf(LocalDate.of(2021, 10, 11)));
		om.setLoans(List.of(loan));
		om.setPhoneNumber("5141234567");
		loanRepository.save(loan);
		Long loanId = loan.getLoanID();
		
		om = null; 
		om = onlineMemberRepository.findOnlineMemberByLibCardNumber(1000101002L);

		book = null;
		book = bookRepository.findBookBylibraryItemID(libraryItemID);

		librarian = null; 
		librarian = librarianRepository.findLibrarianByEmployeeIDNumber(employeeIDNum);

		loan = null;
		loan = loanRepository.findLoanByLoanID(loanId);

		assertNotNull(om);
		assertNotNull(book); 
		assertNotNull(librarian);
		assertNotNull(loan); 
	
		assertEquals(om.getFullName(), loan.getMember().getFullName());
		assertEquals(loan.getLibraryItem().getTitle(), book.getTitle());
	}
}
