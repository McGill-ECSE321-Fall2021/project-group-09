package ca.mcgill.ecse321.projectgroup09.dao;

import java.sql.Date;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.Schedule.DayofWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibManSysPersistence {


	@Autowired
	private BookingRepository bookingRepository; 
	
	@Autowired
	private LibrarianRepository librarianRepository; 
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	@Autowired
	private LibraryRepository libraryRepository; 
	
	
	@Autowired
	private ScheduleRepository scheduleRepository; 
	
	private LibraryManagement lm;
	
	
	@BeforeEach
	public void setupLibraryManagement() {
		lm = new LibraryManagement();
		lm.setID(1L);
		lm.setAccounts(new ArrayList<Account>());
		lm.setLibraries(new ArrayList<Library>());
		lm.setLibraryItems(new ArrayList<LibraryItem>());
	}
	
	@AfterEach
	public void clearDatabase() {
		bookingRepository.deleteAll();
		libraryRepository.deleteAll();
		scheduleRepository.deleteAll();
		librarianRepository.deleteAll();
		headLibrarianRepository.deleteAll();
	}	
	
	/**
	 * @author Zarif Ashraf
	 */
	@Test
	public void testPersistAndLoadLibrary() {
		Long bookingID = 9L;
		Date bookingDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 18));
		Time bookingStartTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time bookingEndTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		

		List<Booking> emptyBookingsList = new ArrayList<Booking>();
		
		Booking booking = new Booking();
		booking.setBookingDate(bookingDate);
		booking.setBookingStartTime(bookingStartTime);
		booking.setBookingEndTime(bookingEndTime);
		booking.setBookingID(bookingID);
		
		// Dummy head librarian for library schedule
		HeadLibrarian hl = new HeadLibrarian();
		hl.setmanagerIDNum(999L);
		hl.setemployeeIDNum(888L);
		hl.setBookings(emptyBookingsList);
		hl.setFullName("hl full name");
		hl.setLibrarianEmail("hl@read.org");
		hl.setLibrarianPassword("password123");
		hl.setLibrarianUsername("username456");
		//hl.setLibrary();
		hl.setLibraryManagement(lm);
		//hl.setLoans();
		//hl.setSchedules();
		
		
		//bookingRepository.save(booking);
		
		String libraryName = "TestLibraryName";
		String libraryAddress = "TestLibraryAddress";
		String libraryPhone = "TestLibraryPhone";
		String libraryEmail = "TestLibraryEmail";

		Library library = new Library();
		library.setLibraryName(libraryName);
		library.setLibraryAddress(libraryAddress);
		library.setLibraryPhone(libraryPhone);
		library.setLibraryEmail(libraryEmail);
		//library.addBooking(booking);
		
		// save before setting
		bookingRepository.save(booking);
		headLibrarianRepository.save(hl);
		
		library.setHeadLibrarian(hl);
		library.setBookings(emptyBookingsList);
		//library.setLibraryManagement(lm);
		
		libraryRepository.save(library);

		library = null;
		
		// the following lines test that the application can read and write objects, attribute values, and references
		library = libraryRepository.findLibraryByLibraryName(libraryName);
		
		assertNotNull(library); 
		assertEquals(libraryName, library.getLibraryName());

		// 'bookings' attribute of library is lazily loaded, need to initiazlize a Session or make
		// this junit test method @Transactional for this to work.
		//assertTrue(library.getBookings().contains(booking));
	}
	/**
	 * @author Zarif Ashraf
	 */
	
	@Test
	public void testPersistAndLoadSchedule() {
		String fullName = "FullName";
		String librarianUsername = "TestLibrarianUsername";
		Long employeeIDNum = 9L;
		String librarianPassword = "TestLibrarianPassword";
		String librarianEmail = "TestLibraryEmail";
		
		Librarian librarian = new Librarian();
		librarian.setLibrarianUsername(librarianUsername);
		librarian.setemployeeIDNum(employeeIDNum);
		librarian.setLibrarianPassword(librarianPassword);
		librarian.setLibrarianEmail(librarianEmail);
		librarian.setFullName(fullName);
		
		librarianRepository.save(librarian);
		
		Long scheduleID = 9L;
		DayofWeek dayOfWeek = DayofWeek.Monday;
		Time openingTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time closingTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		Schedule schedule = new Schedule();
		schedule.setDayofWeek(dayOfWeek);
		schedule.setscheduleID(scheduleID);
		schedule.setOpeningTime(openingTime);
		schedule.setClosingTime(closingTime);
		schedule.setLibrarian(librarian);
		
		scheduleRepository.save(schedule);
		
		schedule = null;
		
		// the following lines test that the application can read and write objects, attribute values, and references
		schedule = scheduleRepository.findScheduleByScheduleID(scheduleID);
		assertNotNull(schedule);
		assertEquals(scheduleID, schedule.getscheduleID());
		assertEquals(librarian.getemployeeIDNum(), schedule.getLibrarian().getemployeeIDNum());
		
	}
	/**
	 * @author Zarif Ashraf
	 */
	@Test
	public void testPersistAndLoadBooking() {
		String fullName = "FullName";
		String librarianUsername = "TestLibrarianUsername";
		Long employeeIDNum = 9L;
		String librarianPassword = "TestLibrarianPassword";
		String librarianEmail = "TestLibraryEmail";
		
		Librarian librarian = new Librarian();
		librarian.setLibrarianUsername(librarianUsername);
		librarian.setemployeeIDNum(employeeIDNum);
		librarian.setLibrarianPassword(librarianPassword);
		librarian.setLibrarianEmail(librarianEmail);
		librarian.setFullName(fullName);
		
		librarianRepository.save(librarian);
		
		Long bookingID = 9L;
		Date bookingDate = java.sql.Date.valueOf(LocalDate.of(2021, Month.OCTOBER, 18));
		Time bookingStartTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
		Time bookingEndTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
		
		Booking booking = new Booking();
		booking.setBookingDate(bookingDate);
		booking.setBookingStartTime(bookingStartTime);
		booking.setBookingEndTime(bookingEndTime);
		booking.setBookingID(bookingID);
		booking.setLibrarian(librarian);
		
		bookingRepository.save(booking);
		
		booking = null;
		
		// the following lines test that the application can read and write objects, attribute values, and references
		booking = bookingRepository.findBookingByBookingID(bookingID);
		assertNotNull(booking);
		assertEquals(bookingID, booking.getBookingID());
		assertEquals(librarian.getemployeeIDNum(), booking.getLibrarian().getemployeeIDNum());
		
	}
	
}