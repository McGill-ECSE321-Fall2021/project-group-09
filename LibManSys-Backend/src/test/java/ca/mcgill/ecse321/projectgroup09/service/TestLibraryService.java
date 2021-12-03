package ca.mcgill.ecse321.projectgroup09.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import java.util.*;

import java.time.DayOfWeek;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;


import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.dao.*;

@ExtendWith(MockitoExtension.class)
public class TestLibraryService {

	@Mock 
	private LibraryRepository libraryRepository; 

	@Mock
	private ScheduleRepository scheduleRepository;

	@Mock 
	private HeadLibrarianRepository hlibrarianRepository; 

	@InjectMocks
	private LibraryService libraryService;

	//Initializing Library Attributes for Mock
	private static final String LIBRARY_NAME = "Test Library"; //library ID 
	private static final String LIB_ADDRESS = "123 Lib Street"; 
	private static final String LIB_PHONE = "905-999-9999";
	private static final String LIB_EMAIL = "lib@email.ca";

	//Initializing Schedule Attributes for Mock
	private static final long SCHED1 = 1; //Sunday (beginning of week)
	private static final long SCHED2 = 2;
	private static final long SCHED3 = 3;
	private static final long SCHED4 = 4;
	private static final long SCHED5 = 5;
	private static final long SCHED6 = 6;
	private static final long SCHED7 = 7; //Saturday (end of week)

	private static final String WEEKDAY_OPEN = "07:00:00";
	private static final String WEEKDAY_CLOSE = "22:00:00";

	private static final String WEEKEND_OPEN = "07:30:00";
	private static final String WEEKEND_CLOSE = "19:00:00"; 

	private static final DayOfWeek Monday = DayOfWeek.MONDAY; 
	private static final DayOfWeek Tuesday = DayOfWeek.TUESDAY;
	private static final DayOfWeek Wednesday = DayOfWeek.WEDNESDAY;
	private static final DayOfWeek Thursday = DayOfWeek.THURSDAY;
	private static final DayOfWeek Friday = DayOfWeek.FRIDAY;
	private static final DayOfWeek Saturday = DayOfWeek.SATURDAY;
	private static final DayOfWeek Sunday = DayOfWeek.SUNDAY;


	//Initializing Head Librarian Attributes for Mock 
	private static final long EMPLOYEE_ID = 123456789; //librarian ID
	//private static final String LIBRARIAN_EMAIL = "librarian@email.com";
	//private static final String LIBRARIAN_PASSWORD = "ASDF12343";
	//private static final String LIBRARIAN_USERNAME = "username";
	//private static final String LIBRARIAN_FULLNAME = "Test Librarian";
	private static final long HLIBRARIAN_ID = EMPLOYEE_ID;


	@BeforeEach
	public void setMockOutput() {
				lenient().when(scheduleRepository.findScheduleByScheduleID(anyLong())).thenAnswer((InvocationOnMock invocation) -> { 
					if (invocation.getArgument(0).equals(SCHED2)) {
						Schedule monday = new Schedule();
						monday.setscheduleID(SCHED2);
						monday.setClosingTime(java.sql.Time.valueOf(WEEKDAY_CLOSE));
						monday.setOpeningTime(java.sql.Time.valueOf(WEEKDAY_OPEN));
						monday.setDayofWeek(Monday);
						return monday;
					} 
					else if (invocation.getArgument(0).equals(SCHED3)) {
						Schedule tuesday = new Schedule();
						tuesday.setscheduleID(SCHED3);
						tuesday.setClosingTime(java.sql.Time.valueOf(WEEKDAY_CLOSE));
						tuesday.setOpeningTime(java.sql.Time.valueOf(WEEKDAY_OPEN));
						tuesday.setDayofWeek(Tuesday);
						return tuesday;
					}
					else if (invocation.getArgument(0).equals(SCHED4)) {
						Schedule wednesday = new Schedule();
						wednesday.setscheduleID(SCHED4);
						wednesday.setClosingTime(java.sql.Time.valueOf(WEEKDAY_CLOSE));
						wednesday.setOpeningTime(java.sql.Time.valueOf(WEEKDAY_OPEN));
						wednesday.setDayofWeek(Wednesday);
						return wednesday;
					}
					else if (invocation.getArgument(0).equals(SCHED5)) {
						Schedule thursday = new Schedule();
						thursday.setscheduleID(SCHED5);
						thursday.setClosingTime(java.sql.Time.valueOf(WEEKDAY_CLOSE));
						thursday.setOpeningTime(java.sql.Time.valueOf(WEEKDAY_OPEN));
						thursday.setDayofWeek(Thursday);
						return thursday;
					}
					else if (invocation.getArgument(0).equals(SCHED6)) {
						Schedule friday = new Schedule();
						friday.setscheduleID(SCHED6);
						friday.setClosingTime(java.sql.Time.valueOf(WEEKDAY_CLOSE));
						friday.setOpeningTime(java.sql.Time.valueOf(WEEKDAY_OPEN));
						friday.setDayofWeek(Friday);
						return friday;
					}
					else if (invocation.getArgument(0).equals(SCHED7)) {
						Schedule saturday = new Schedule();
						saturday.setscheduleID(SCHED7);
						saturday.setClosingTime(java.sql.Time.valueOf(WEEKEND_CLOSE));
						saturday.setOpeningTime(java.sql.Time.valueOf(WEEKEND_OPEN));
						saturday.setDayofWeek(Saturday);
						return saturday;
					}
					else if (invocation.getArgument(0).equals(SCHED1)) {
						Schedule sunday = new Schedule();
						sunday.setscheduleID(SCHED1);
						sunday.setClosingTime(java.sql.Time.valueOf(WEEKEND_CLOSE));
						sunday.setOpeningTime(java.sql.Time.valueOf(WEEKEND_OPEN));
						sunday.setDayofWeek(Sunday);
						return sunday;
					}
					else {
						return null;
					}
				});

		lenient().when(libraryRepository.findLibraryByLibraryName(anyString())).thenAnswer((InvocationOnMock invocation) -> { 
			if (invocation.getArgument(0).equals(LIBRARY_NAME)) {
				Library library = new Library();
				library.setLibraryAddress(LIB_ADDRESS);
				library.setLibraryEmail(LIB_EMAIL);
				library.setLibraryName(LIBRARY_NAME);
				library.setLibraryPhone(LIB_PHONE);
				return library;
			}
			else {
				return null;
			}
		});


	}

	private ArrayList<Schedule> getLibrarySchedule() {
		ArrayList<Schedule> libHours = new ArrayList<Schedule>();
		String weekdayOpen = "07:00:00";
		String weekdayClose = "22:00:00";
		String weekendOpen = "07:30:00";
		String weekendClose = "19:00:00";

		//Create 7 schedules (one for each day of week)
		Schedule monday = new Schedule();
		monday.setDayofWeek(Monday);
		libHours.add(monday);

		Schedule tuesday = new Schedule();
		tuesday.setDayofWeek(Tuesday);
		libHours.add(tuesday);

		Schedule wednesday = new Schedule();
		wednesday.setDayofWeek(Wednesday);
		libHours.add(wednesday);

		Schedule thursday = new Schedule();
		thursday.setDayofWeek(Thursday);
		libHours.add(thursday);

		Schedule friday = new Schedule();
		friday.setDayofWeek(Friday);
		libHours.add(friday);

		Schedule saturday = new Schedule();
		saturday.setDayofWeek(Saturday);
		saturday.setClosingTime(java.sql.Time.valueOf(weekendClose));
		saturday.setOpeningTime(java.sql.Time.valueOf(weekendOpen));
		libHours.add(saturday);

		Schedule sunday = new Schedule();
		saturday.setDayofWeek(Sunday);
		sunday.setClosingTime(java.sql.Time.valueOf(weekendClose));
		sunday.setOpeningTime(java.sql.Time.valueOf(weekendOpen));
		libHours.add(sunday);

		for (int i = 0; i < 7; i++) {
			libHours.get(i).setscheduleID((long) i+1);
		}
		for (int i = 1; i < 6; i++) {
			libHours.get(i).setClosingTime(java.sql.Time.valueOf(weekdayClose));
			libHours.get(i).setOpeningTime(java.sql.Time.valueOf(weekdayOpen));
		}
		return libHours;

	}



	@Test
	public void createLibrary() {
		String libName = "Test Library";
		String libPhone = "905-999-9999";
		String libAddress = "123 Lib Street"; 
		String libEmail = "lib@email.ca";


		Library library = null; 

		try {
			library = libraryService.createLibrary(libAddress, libPhone, libEmail, libName );
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
			fail();
		}

		assertNotNull(library);
		assertEquals(libName, library.getLibraryName());
	}

	@Test
	void setLibraryHours() {
		String libName = "Test Library";
		List<Schedule> libHours = getLibrarySchedule();
		Library library = null; 

		try {
			library = libraryService.setLibraryHours(HLIBRARIAN_ID, getLibrarySchedule(), libName);
		}
		catch (IllegalArgumentException e){
			fail();
		}
		
		assertNotNull(library);
		assertEquals(libHours.get(1).getDayofWeek(), library.getSchedules().get(1).getDayofWeek());
		assertEquals(libHours.get(2).getDayofWeek(), library.getSchedules().get(2).getDayofWeek());
		assertEquals(libHours.get(3).getDayofWeek(), library.getSchedules().get(3).getDayofWeek());
		assertEquals(libHours.get(4).getDayofWeek(), library.getSchedules().get(4).getDayofWeek());
		assertEquals(libHours.get(5).getDayofWeek(), library.getSchedules().get(5).getDayofWeek());
		assertEquals(libHours.get(6).getDayofWeek(), library.getSchedules().get(6).getDayofWeek());
		assertEquals(libHours.get(0).getDayofWeek(), library.getSchedules().get(0).getDayofWeek());
		
		library.getSchedules().get(0).getLibrarian();

	}


	@Test 
	public void updateLibraryHours() {
		String libName = "Test Library";
//		List<Schedule> libHours = getNewLibrarySchedule();
		
		Library library = null; 

		try {
			
			library = libraryService.setLibraryHours(HLIBRARIAN_ID, libraryService.getLibrarySchedules(), libName);
		}
		catch (IllegalArgumentException e){
			fail();
		}
		
		assertNotNull(library);
		assertEquals(library.getSchedules().get(0).getClosingTime().toString(), WEEKEND_CLOSE);
		assertEquals(library.getSchedules().get(1).getClosingTime().toString(), WEEKDAY_CLOSE);
		assertEquals(library.getSchedules().get(0).getOpeningTime().toString(), WEEKEND_OPEN);
		assertEquals(library.getSchedules().get(1).getOpeningTime().toString(), WEEKDAY_OPEN);
	}

	
	@Test
	public void updateLibraryEmail() {
		String libName = "Test Library";
		String newEmail = "newemail@gmail.com"; 
		
		Library library = null; 
		
		try {
			library = libraryService.updateLibraryEmail(libName, newEmail);
		}
		catch (IllegalArgumentException e){
			fail();
		}
		
		assertNotNull(library);
		assertEquals(library.getLibraryEmail(), newEmail);
	}
	
	@Test
	public void updateLibraryEmailWithNullParameters() {
		String libName = "Test Library";
		String newEmail = null;
		String error = null;

		
		try {
			libraryService.updateLibraryEmail(libName, newEmail);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(error, "Please enter a new email.");
	}
	
	@Test
	public void updateLibraryPhoneNo() {
		String libName = "Test Library";
		String newPhoneNo = "416-666-6666"; 
		
		Library library = null; 
		
		try {
			library = libraryService.updateLibraryPhoneNo(libName, newPhoneNo);
		}
		catch (IllegalArgumentException e){
			fail();
		}
		
		assertNotNull(library);
		assertEquals(library.getLibraryPhone(), newPhoneNo);
	}
	
	@Test
	public void updateLibraryPhoneNoWithNullParams() {
		String libName = "Test Library";
		String newPhoneNo = null;
		String error = null;
		
		try {
			libraryService.updateLibraryPhoneNo(libName, newPhoneNo);
		}
		catch (IllegalArgumentException e){
			error = e.getMessage();
		}
		
		assertEquals(error, "Please enter a new phone number.");
	}
	
	
	@Test 
	public void getLibraryHours() {
		List<Schedule> libHours = new ArrayList<Schedule>();
		
		Library library = new Library();
		String libName = "Test Library";
		String libPhone = "905-999-9999";
		String libAddress = "123 Lib Street"; 
		String libEmail = "lib@email.ca";

		library.setLibraryName(libName);
		library.setLibraryAddress(libAddress);
		library.setLibraryEmail(libEmail);
		library.setLibraryPhone(libPhone);

		List<Schedule> librarySchedule = getLibrarySchedule();
		library.setSchedules(librarySchedule);
		
		try {
			libHours = libraryService.getLibraryHours(libName);
		}
		catch (IllegalArgumentException e) {
			fail();
		}
	
		assertNotNull(libHours);
	}
	
	@Test 
	public void deleteLibrary() { 
		Library library = new Library();
		String libName = "Test Library";
		String libPhone = "905-999-9999";
		String libAddress = "123 Lib Street"; 
		String libEmail = "lib@email.ca";

		library.setLibraryName(libName);
		library.setLibraryAddress(libAddress);
		library.setLibraryEmail(libEmail);
		library.setLibraryPhone(libPhone);

		List<Schedule> libHours = getLibrarySchedule();
		library.setSchedules(libHours);

		try {
			library = libraryService.deleteLibrary(libName);
		}
		catch (IllegalArgumentException e) {
			System.out.println();
			fail();
		}

		assertNotNull(library);
	}
}
