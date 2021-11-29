package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceLibrary {


	@Autowired
	private BookingRepository bookingRepository; 
	
	@Autowired
	private LibrarianRepository librarianRepository; 
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	@Autowired
	private LibraryRepository libraryRepository; 



	@AfterEach
	public void clearDatabase() {
		bookingRepository.deleteAll();
		libraryRepository.deleteAll();
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
		hl.setemployeeIDNumber(888L);
		hl.setBookings(emptyBookingsList);
		hl.setFullName("hl full name");
		hl.setLibrarianEmail("hl@read.org");
		hl.setLibrarianPassword("password123");
		hl.setLibrarianUsername("username456");
		//hl.setLibrary();
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
}
