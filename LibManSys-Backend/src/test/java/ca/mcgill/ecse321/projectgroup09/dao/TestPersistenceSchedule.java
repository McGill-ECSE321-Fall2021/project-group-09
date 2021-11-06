package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.Account;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryManagement;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
import ca.mcgill.ecse321.projectgroup09.models.Schedule.DayofWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceSchedule {

	@Autowired
	private ScheduleRepository scheduleRepository; 

	@Autowired
	private LibrarianRepository librarianRepository; 
	
	@AfterEach
	public void clearDatabase() {
		
		scheduleRepository.deleteAll();
		librarianRepository.deleteAll();
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
}
