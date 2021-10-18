package ca.mcgill.ecse321.projectgroup09.dao;

import java.sql.Time;
import java.time.DayOfWeek;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.LibraryManagement;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistAndLoadAccounts {
	
	LibraryManagement lm = new LibraryManagement();
	
	@AfterEach
	public void clearAccountsDatabase() {
		
	}
	
	@Test
	public void testPersistAndLoadHeadLibrarian() {
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
		Integer hlEmployeeId = 123;
		Integer hlManagerId = 101;
		String hlLibraryName = "Library A";
		String hlLibraryAddress = "1234 Reading Rd.";
		String hlLibraryPhoneNumber = "5145556677";
		String hlLibraryEmail = "library@reading.com";
		Integer scheduleId = 111;
		Time openingTime = new Time(1632974400000L);
		Time closingTime = new Time(1632974410000L);
		DayOfWeek day = DayOfWeek.MONDAY;
		//Librarian librarian = new Librarian("Name", lm, "email@email.com", "password", "librarianUserName", 102);
		//Schedule hlSchedule = new Schedule();
	}

}
