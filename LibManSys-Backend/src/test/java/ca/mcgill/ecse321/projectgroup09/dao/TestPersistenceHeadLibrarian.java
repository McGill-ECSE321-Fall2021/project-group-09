package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.DayOfWeek;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceHeadLibrarian {


	@Autowired
	private HeadLibrarianRepository headLibrarianRepository; 
	
	@Autowired
	private LibrarianRepository librarianRepository; 

	@Autowired 
	private LibraryRepository libraryRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@AfterEach
	public void clearAccountsDatabase() {
		
		
		libraryRepository.deleteAll();
		scheduleRepository.deleteAll();
		headLibrarianRepository.deleteAll();
		librarianRepository.deleteAll();
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
		librarian.setemployeeIDNumber(employeeIDNum);


		// create example schedule for head librarian's library
		Schedule sampleSchedule = new Schedule();
		Long scheduleId = 111L;
		Time openingTime = new Time(1632974400000L);
		Time closingTime = new Time(1632974410000L);
		DayOfWeek day = DayOfWeek.MONDAY;
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
		hl.setemployeeIDNumber(hlEmployeeId);
		hl.setmanagerIDNum(hlManagerId);
		//hl.setLibrary(library);
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
		librarian = librarianRepository.findLibrarianByEmployeeIDNumber(employeeIDNum);

		assertNotNull(hl);
		assertNotNull(librarian);
		assertNotNull(library);
		assertNotNull(sampleSchedule);

		assertEquals(hlName, hl.getFullName());
		assertEquals(hlPassword, sampleSchedule.getLibrarian().getLibrarianPassword());
	}
}
