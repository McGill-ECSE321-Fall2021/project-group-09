package ca.mcgill.ecse321.projectgroup09.dao;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.LibraryManagement;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
import ca.mcgill.ecse321.projectgroup09.models.Schedule.DayofWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadAccounts {
	
	@Autowired
	private AccountRepository accountRepository; 
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository; 
	
	@Autowired
	private LibrarianRepository librarianRepository; 

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private OnlineMemberRepository onlineMemberRepository; 
	
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired 
	private LibraryRepository libraryRepository;
	
	
	LibraryManagement lm = new LibraryManagement();
	
	@AfterEach
	public void clearAccountsDatabase() {
		loanRepository.deleteAll();
		scheduleRepository.deleteAll();
		headLibrarianRepository.deleteAll();
		librarianRepository.deleteAll();
		memberRepository.deleteAll();
		onlineMemberRepository.deleteAll();
		accountRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		// create librarian for schedule
		//Librarian librarian = new Librarian();
		//librarian.setBookings(new ArrayList<Booking>());
		//librarian.setemployeeIDNum(null);
		
		//Library
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
		librarian.setemployeeIDNum(employeeIDNum);
	
		
		// create example schedule for head librarian's library
		Schedule sampleSchedule = new Schedule();
		Long scheduleId = 111L;
		Time openingTime = new Time(1632974400000L);
		Time closingTime = new Time(1632974410000L);
		DayofWeek day = DayofWeek.Monday;
		sampleSchedule.setscheduleID(scheduleId);
		sampleSchedule.setOpeningTime(openingTime);
		sampleSchedule.setClosingTime(closingTime);
		sampleSchedule.setDayofWeek(day);
		
		librarianRepository.save(librarian);
		libraryRepository.save(library); 
//		
//		Library library = new Library();
//		String hlLibraryName = "Library A";
//		String hlLibraryAddress = "1234 Reading Rd.";
//		String hlLibraryPhoneNumber = "5145556677";
//		String hlLibraryEmail = "library@reading.com";
//		library.setLibraryName(hlLibraryName);
//		library.setLibraryAddress(hlLibraryAddress);
//		library.setLibraryPhone(hlLibraryPhoneNumber);
//		library.setLibraryEmail(hlLibraryEmail);
//		
//		libraryRepository.save(library)
//		
		HeadLibrarian hl = new HeadLibrarian();
		/*
		String aFullName, LibraryManagement aLibraryManagement, String aLibrarianEmail,
		String aLibrarianPassword, String aLibrarianUsername, Integer aEmployeeIdNum, Integer amanagerIDNum,
		String aLibraryNameForLibrary, String aLibraryAddressForLibrary, String aLibraryPhoneForLibrary,
		String aLibrarymailForLibrary, LibraryManagement aLibraryManagementForLibrary,
		Schedule... allSchedulesForLibrary
		*/
		String hlName = "Fake Name";
		String hlEmail = "fakeemail@lib.com";
		String hlPassword = "asdf1234";
		String hlUsername = "fakeusername";
		
		Long hlManagerId = 101L;
		Long hlEmployeeId = hlManagerId;
		
		hl.setFullName(hlName);
		hl.setLibrarianEmail(hlEmail);
		hl.setLibrarianPassword(hlPassword);
		hl.setLibrarianUsername(hlUsername);
		hl.setemployeeIDNum(hlEmployeeId);
		hl.setmanagerIDNum(hlManagerId);
		//hl.setLibrary(library);
		library.setHeadLibrarian(hl);
		sampleSchedule.setLibrarian(hl);
		
		// save Head Librarian
		headLibrarianRepository.save(hl);
		scheduleRepository.save(sampleSchedule);
		
		
		// load HL
		hl = null;
		library = null;
		sampleSchedule = null; 
		librarian = null;
		
		hl = headLibrarianRepository.findHeadLibrarianByManagerIDNum(hlManagerId);
		library = libraryRepository.findLibraryByLibraryName(libraryName);
		sampleSchedule = scheduleRepository.findScheduleByScheduleID(scheduleId);
		librarian = librarianRepository.findLibrarianByEmployeeIDNum(employeeIDNum);
		
		assertNotNull(hl);
		assertNotNull(librarian);
		assertNotNull(library);
		assertNotNull(sampleSchedule);
		
		assertEquals(hlName, hl.getFullName());
		assertEquals(hlPassword, sampleSchedule.getLibrarian().getLibrarianPassword());
	}
	
	@Test
	public void testPersistAndLoadMember() {

		// Account Attributes 
		String fullname = "Testing Name";

		// Member Attributes
		Long LibCardNumber =  2L;
		Member member = new Member();
		String address = "12 B";
		Boolean isResident = true;
		String phoneNumber = "514";
		Double amountOwed = 0.10;
		Integer activeLoans = 0;
		Boolean isVerified = true;


		member.setFullName(fullname);
		member.setActiveLoans(activeLoans);
		member.setAddress(address);
		member.setAmountOwed(amountOwed);
		member.setIsResident(isResident);
		member.setIsVerified(isVerified);
		member.setPhoneNumber(phoneNumber);
		member.setLibCardNumber(LibCardNumber);

		memberRepository.save(member);
		
		//Loan Attributes
		Long loanID = 1L;
		Date borrowdDate = java.sql.Date.valueOf("2021-02-01");
		Date returnDate = java.sql.Date.valueOf("2021-02-08");
		Double lateFees= 0.10;
		LoanStatus loanStatus =  LoanStatus.Active;

		Loan loan = new Loan();
		loan.setLateFees(lateFees);
		loan.setloanID(loanID);
		loan.setBorrowedDate(borrowdDate);
		loan.setReturnDate(returnDate);
		loan.setLoanStatus(loanStatus);

		loan.setMember(member);

		loanRepository.save(loan);
		
		member = null;
		member = memberRepository.findMemberByLibCardNumber(LibCardNumber);
		
		loan = null; 
		loan = loanRepository.findLoanByLoanID(loanID);
		
		assertNotNull(member);
		assertEquals(fullname, member.getFullName());
		assertEquals(fullname, loan.getMember().getFullName());
	}
	

	@Test
	public void testPersistAndLoadOnlineMember() {
		
		OnlineMember om = new OnlineMember();
		
		om.setActiveLoans(1);
		om.setAddress("address");
		om.setAmountOwed(100);
		om.setBookings(new ArrayList<Booking>());
		om.setFullName("name name");
		om.setIsResident(true);
		om.setIsVerified(true);
		om.setLibCardNumber((long) 1000101002L);
		om.setPhoneNumber("1234567890");
		//om.setLibraryManagement(lm);
	
		om.setMemberEmail("member email");
		om.setMemberPassword("member password");
		om.setMemberUsername("member username");
		
	
		Loan loan = new Loan();
		loan.setMember(om);
		loan.setLateFees(10);
		loan.setLibrarian(new Librarian());
		loan.setLibraryItem(new Book());
		loan.setloanID(12312L);
		loan.setLoanStatus(LoanStatus.Active);
		loan.setBorrowedDate(Date.valueOf(LocalDate.of(2021, 10, 11)));
		loan.setReturnDate(Date.valueOf(LocalDate.of(2021, 10, 11)));
		om.setLoans(List.of(loan));
		om.setPhoneNumber("5141234567");
		
		onlineMemberRepository.save(om);
		loanRepository.save(loan);
		om = null;
		om = onlineMemberRepository.findOnlineMemberByLibCardNumber(1000101002L);
		
		assertNotNull(om);
		assertEquals("name name", om.getFullName());
		assertTrue(om.getLoans().contains(loan));
	}
}
	


