package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceBooking {


	@Autowired
	private LibrarianRepository librarianRepository; 

	@Autowired
	private BookingRepository bookingRepository; 
	

	@AfterEach
	public void clearDatabase() {
		bookingRepository.deleteAll();
		librarianRepository.deleteAll();
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
		librarian.setemployeeIDNumber(employeeIDNum);
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
		
		booking = bookingRepository.save(booking);
		bookingID = booking.getBookingID();
		
		booking = null;
		
		// the following lines test that the application can read and write objects, attribute values, and references
		booking = bookingRepository.findBookingByBookingID(bookingID);
		assertNotNull(booking);
		assertEquals(bookingID, booking.getBookingID());
		assertEquals(librarian.getemployeeIDNumber(), booking.getLibrarian().getemployeeIDNumber());
		
	}
}
