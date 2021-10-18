package ca.mcgill.ecse321.projectgroup09.dao;
import java.sql.Date;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;

/*
 * Author: Rajaa
 * 
 * */
 
public class TestPersistAndLoadLoan {
	@Autowired
	private LoanRepository loanRepository;  
	@Autowired
	private LibrarianRepository librarianRepository; 
	@Autowired
	private MemberRepository memberRepository; 
	@Autowired
	private LibraryItemRepository libraryItemRepository; 
	
	@AfterEach
	public void clearDatabase() {
		loanRepository.deleteAll();
		librarianRepository.deleteAll();
		memberRepository.deleteAll();
		libraryItemRepository.deleteAll();
	}
	@Test
	public void testPersistAndLoadLoan() {
		
		Long LibCardNumber =  2L;
		Member member = new Member();
		String address = "12 B";
		Boolean isResident = true;
		String phoneNumber = "514";
		Double amountOwed = 0.10;
		Integer activeLoans = 0;
		Boolean verified = true;
		
		member.setLibCardNumber(LibCardNumber);
		member.setAddress(address);
		member.setIsResident(isResident);
		member.setPhoneNumber(phoneNumber);
		member.setAmountOwed(amountOwed);
		member.setActiveLoans(activeLoans);
		member.setIsVerified(verified);
		
		
		//librarianRepository.save(librarian);
		
		Long loanID = 1L;
		Date borrowdDate = java.sql.Date.valueOf("2021-02-01");
		Date returnDate = java.sql.Date.valueOf("2021-02-08");
		Double lateFees= 0.10;
		LoanStatus loanStatus =  LoanStatus.Active;
		
		
		Book libraryItem = new Book();
		String author = "Rajaa";
		String publisher = "Boukhelif";
		String isbn = "111";
		Integer numPages = 2;
		Long libraryItemID = 5L;
		String title = "myBook";
		Integer year = 2000;
		Integer period = 2;
		Double overdue = 0.10;
		
		libraryItem.setAuthor(author);
		libraryItem.setPublisher(publisher);
		libraryItem.setISBN(isbn);
		libraryItem.setNumPages(numPages);
		libraryItem.setlibraryItemID(libraryItemID);
		libraryItem.setDailyOverdueFee(overdue);
		libraryItem.setLoanablePeriod(period);
		libraryItem.setPublishedYear(year);
		libraryItem.setPublisher(publisher);
		libraryItem.setTitle(title);
	//	loan.setLibrarian(librarian);
		
		Loan loan = new Loan();
		loan.setLateFees(lateFees);
		loan.setloanID(loanID);
		loan.setBorrowedDate(borrowdDate);
		loan.setReturnDate(returnDate);
		loan.setLoanStatus(loanStatus);
	//	loan.setLibrarian(librarian);
		loan.setMember(member);
		loan.setLibraryItem(libraryItem);
		
		loanRepository.save(loan);
		
		loan = null;
		
		// the following lines test that the application can read and write objects, attribute values, and references
		loan = loanRepository.findLoanByLoanID(loanID);
		assertNotNull(loan);
		assertEquals(loanStatus, loan.getLoanStatus());
		assertEquals(loanID, loan.getLoanID());
		assertEquals(member, loan.getMember());
		assertEquals(libraryItem, loan.getLibraryItem());
		
	}
}
