package ca.mcgill.ecse321.projectgroup09.dao;

import java.sql.Date;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Schedule.DayofWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
/**
 * 
 * @author snehas
 *
 */

public class TestPersistAndLoadLibraryItems {

	@Autowired
	private ArchiveRepository  archiveRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private MovieRepository movieRepository; 
	
	@Autowired
	private MusicAlbumRepository musicAlbumRepository; 
	
	@Autowired
	private NewspaperRepository newspaperRepository; 
	
	
	@Test
	public void testPersistAndLoadBook() {
		
		Long libraryItemID = (long) 1; 
		String Title = "TestBook";
		int publishedYear = 2021;
		int loanablePeriod = 21; 
		double dailyOverdueFee = 50;
		//ItemStatus itemStatus = ItemStatus Available;
		
//		
//		Librarian librarian = new Librarian();
//		librarian.setLibrarianUsername(librarianUsername);
//		librarian.setemployeeIDNum(employeeIDNum);
//		librarian.setLibrarianPassword(librarianPassword);
//		librarian.setLibrarianEmail(librarianEmail);
//		librarian.setFullName(fullName);
//		
//		librarianRepository.save(librarian);
//		
//		Long scheduleID = 9L;
//		DayofWeek dayOfWeek = DayofWeek.Monday;
//		Time openingTime = java.sql.Time.valueOf(LocalTime.of(11, 35));
//		Time closingTime = java.sql.Time.valueOf(LocalTime.of(13, 25));
//		
//		Schedule schedule = new Schedule();
//		schedule.setDayofWeek(dayOfWeek);
//		schedule.setscheduleID(scheduleID);
//		schedule.setOpeningTime(openingTime);
//		schedule.setClosingTime(closingTime);
//		schedule.setLibrarian(librarian);
//		
//		scheduleRepository.save(schedule);
//		
//		schedule = null;
//		
//		// the following lines test that the application can read and write objects, attribute values, and references
//		schedule = scheduleRepository.findScheduleByScheduleID(scheduleID);
//		assertNotNull(schedule);
//		assertEquals(scheduleID, schedule.getscheduleID());
//		assertEquals(librarian, schedule.getLibrarian());
		
	}
}
