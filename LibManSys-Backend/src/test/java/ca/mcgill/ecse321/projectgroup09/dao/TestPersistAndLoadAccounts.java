package ca.mcgill.ecse321.projectgroup09.dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.LibraryManagement;
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
	
	LibraryManagement lm = new LibraryManagement();
	
	@AfterEach
	public void clearAccountsDatabase() {
		
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
		// create librarian for schedule
		Librarian librarian = new Librarian();
		librarian.setBookings(new ArrayList<Booking>());
		librarian.setemployeeIDNum(null);
		
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
		sampleSchedule.setLibrarian(librarian);
		
		Library library = new Library();
		String hlLibraryName = "Library A";
		String hlLibraryAddress = "1234 Reading Rd.";
		String hlLibraryPhoneNumber = "5145556677";
		String hlLibraryEmail = "library@reading.com";
		library.setLibraryName(hlLibraryName);
		library.setLibraryAddress(hlLibraryAddress);
		library.setLibraryPhone(hlLibraryPhoneNumber);
		library.setLibraryEmail(hlLibraryEmail);
		
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
		//Long hlEmployeeId = 123L;
		Long hlManagerId = 101L;
		
		hl.setFullName(hlName);
		hl.setLibrarianEmail(hlEmail);
		hl.setLibrarianPassword(hlPassword);
		hl.setLibrarianUsername(hlUsername);
		//hl.setemployeeIDNum(hlEmployeeId);
		hl.setmanagerIDNum(hlManagerId);
		hl.setLibrary(library);
		
		// save Head Librarian
		headLibrarianRepository.save(hl);
		
		// load HL
		HeadLibrarian loadedHL = headLibrarianRepository.findById(hlManagerId);
		
		assertNotNull(loadedHL);
		assertEquals(hlName, loadedHL.getFullName());
		assertTrue(loadedHL.getSchedules().contains(sampleSchedule));
	}

}
