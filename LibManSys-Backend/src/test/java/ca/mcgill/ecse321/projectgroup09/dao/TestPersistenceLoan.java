package ca.mcgill.ecse321.projectgroup09.dao;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;

/*
 * Author: Rajaa
 * 
 * */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceLoan {
	@Autowired
	private LoanRepository loanRepository;  
	@Autowired
	private LibrarianRepository librarianRepository; 
	@Autowired
	private MemberRepository memberRepository; 
	@Autowired
	private LibraryItemRepository libraryItemRepository; 
	@Autowired
	private BookRepository bookRepository;
	@Autowired 
	private LibraryRepository libraryRepository; 
	@Autowired 
	private AccountRepository accountRepository;
	
	@AfterEach
	public void clearDatabase() {
		loanRepository.deleteAll();
		librarianRepository.deleteAll();
		memberRepository.deleteAll();
		libraryItemRepository.deleteAll();
		libraryRepository.deleteAll();
		bookRepository.deleteAll();
		accountRepository.deleteAll();
	}
	
	@Test
	@Transactional
	public void testPersistAndLoadLoan() {
		// Account Attributes 
		String fullname = "Testing Name";
		
		// Member Attributes
		Long LibCardNumber =  2L;
		Member member = new Member();
		String address = "12 B";
		String phoneNumber = "514";
		Double amountOwed = 0.10;
		Integer activeLoans = 0;
		Boolean isVerifiedResident = true;
		
		
		member.setFullName(fullname);
		member.setActiveLoans(activeLoans);
		member.setAddress(address);
		member.setAmountOwed(amountOwed);
		member.setIsVerifiedResident(isVerifiedResident);
		member.setPhoneNumber(phoneNumber);
		member.setLibCardNumber(LibCardNumber);
		
		memberRepository.save(member);

		
		//Library Attributes
		String libraryName = "libName"; 
		String libraryAddress = "libAddress";
		String libraryPhone = "123-456-7890";
		String libraryEmail = "library@city.com";
		
		Library library = new Library();
		library.setLibraryName(libraryName);
		library.setLibraryAddress(libraryAddress);
		library.setLibraryPhone(libraryPhone);
		library.setLibraryEmail(libraryEmail);
		
		libraryRepository.save(library); 
	
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
		book.setlibraryItemID(libraryItemID);
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
		// get autoset library item id
		libraryItemID = book.getlibraryItemID();
		
		
		//Loan Attributes
		Long loanID = 1L;
		Date borrowdDate = java.sql.Date.valueOf("2021-02-01");
		Date returnDate = java.sql.Date.valueOf("2021-02-08");
		Double lateFees= 0.10;
		LoanStatus loanStatus =  LoanStatus.Active;
		
		Loan loan = new Loan();
		loan.setLateFees(lateFees);
		//loan.setloanID(loanID); 
		// loans id's are generated when loan object is first saved to repo, 
		// so just get the value after saving it
		loan.setBorrowedDate(borrowdDate);
		loan.setReturnDate(returnDate);
		loan.setLoanStatus(loanStatus);
		
		loan.setMember(member);
		loan.setLibraryItem(book);
		
		loanRepository.save(loan);
		loanID = loan.getLoanID();
		
		
		//Assertions
		member = null; 
		librarian = null;
		library = null; 
		loan = null; 
		book = null; 
		//libraryItem = null;
		
		member = memberRepository.findMemberByLibCardNumber(LibCardNumber);
		loan = loanRepository.findLoanByLoanID(loanID);
		book = bookRepository.findBookBylibraryItemID(libraryItemID);
		assertNotNull(member);
		assertNotNull(loan);
		assertNotNull(book);
		
    	assertEquals(fullname, member.getFullName());
		assertEquals(address, loan.getMember().getAddress());
		
	}
}
	
