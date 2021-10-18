package ca.mcgill.ecse321.projectgroup09.dao;

import java.sql.Date;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.Schedule.DayofWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestLibManSysPersistence {

	/*@Autowired
	private AccountRepository accountRepository; 
	
	@Autowired
	private ArchiveRepository  archiveRepository;*/
	
	@Autowired
	private BookingRepository bookingRepository; 
	
	/*@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;*/
	
	@Autowired
	private LibrarianRepository librarianRepository; 
	
	/*@Autowired
	private LibraryItemRepository libraryItemRepository;*/
	
	@Autowired
	private LibraryRepository libraryRepository; 
	
	/*@Autowired
	private LoanRepository loanRepository;  
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MovieRepository movieRepository; 
	
	@Autowired
	private MusicAlbumRepository musicAlbumRepository; 
	
	@Autowired
	private NewspaperRepository newspaperRepository; 
	
	@Autowired
	private OnlineMemberRepository onlineMemberRepository;*/
	
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
		//accountRepository.deleteAll();
		//archiveRepository.deleteAll();
		bookingRepository.deleteAll();
		//bookRepository.deleteAll();
		//headLibrarianRepository.deleteAll();
		librarianRepository.deleteAll();
		//libraryItemRepository.deleteAll();
		libraryRepository.deleteAll();
		//loanRepository.deleteAll();
		//memberRepository.deleteAll();
		//movieRepository.deleteAll();
		//musicAlbumRepository.deleteAll();
		//newspaperRepository.deleteAll();
		//onlineMemberRepository.deleteAll();
		scheduleRepository.deleteAll();
		
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
		
		Booking booking = new Booking();
		booking.setBookingDate(bookingDate);
		booking.setBookingStartTime(bookingStartTime);
		booking.setBookingEndTime(bookingEndTime);
		booking.setBookingID(bookingID);
		
		// Dummy head librarian for library schedule
		HeadLibrarian hl = new HeadLibrarian();
		hl.setmanagerIDNum(999L);
		
		ArrayList<Schedule> librarySchedule = new ArrayList<Schedule>();
		for (long i = 0; i < 7; i++) {
			Schedule s = new Schedule();
			s.setscheduleID(1000L + i);
			s.setDayofWeek(DayofWeek.values()[(int) i]);
			s.setOpeningTime(java.sql.Time.valueOf(LocalTime.of(9, 30)));
			s.setClosingTime(java.sql.Time.valueOf(LocalTime.of(17, 00)));
			s.setLibrarian(hl);
		}
		
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
		
		library.setHeadLibrarian(hl);
		library.setBookings(List.of(booking));
		library.setLibraryManagement(lm);
		
		libraryRepository.save(library);

		library = null;
		
		// the following lines test that the application can read and write objects, attribute values, and references
		library = libraryRepository.findLibraryByLibraryName(libraryName);
		assertNotNull(library); 
		assertEquals(libraryName, library.getLibraryName());
		assertTrue(library.getBookings().contains(booking));
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
		assertEquals(librarian, schedule.getLibrarian());
		
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
		assertEquals(librarian, booking.getLibrarian());
		
	}
	
}