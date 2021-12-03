package ca.mcgill.ecse321.projectgroup09.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup09.dao.BookingRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.dao.ScheduleRepository;
import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

@ExtendWith(MockitoExtension.class)
public class TestBookingService {

	@Mock
	private BookingRepository bookingRepository;

	@Mock 
	private MemberRepository memberRepository; 

	@Mock 
	private LibraryRepository libraryRepository; 

	@Mock
	private LibrarianRepository librarianRepository; 

	@Mock 
	private ScheduleRepository scheduleRepository; 

	@InjectMocks
	private BookingService bookingService; 


	private static final long BOOKING_ID = 12345678; //booking ID
	private static final String START_TIME = "8:00:00"; 
	private static final String END_TIME = "16:00:00"; 
	private static final String DATE = "2023-11-11";

//	private static final String NEW_START_TIME = "10:00:00";
//	private static final String NEW_END_TIME = "18:00:00";
//	private static final String NEW_DATE = "2024-12-12";

	private static final long LIB_CARD_NO = 999999999; //member ID 
	private static final String MEM_ADDRESS = "123 Test Address";
	private static final double AMOUNT_OWED = 0; 
	private static final int ACTIVE_LOANS = 0; 
	private static final boolean IS_VERIFIED_RESIDENT = true; 
	private static final String FULL_NAME = "Test Member";
	private static final String PHONE_NO = "123-456-7890";

	private static final long EMPLOYEE_ID = 123456789; //librarian ID
	private static final String LIBRARIAN_EMAIL = "librarian@email.com";
	private static final String LIBRARIAN_PASSWORD = "ASDF12343";
	private static final String LIBRARIAN_USERNAME = "username";
	private static final String LIBRARIAN_FULLNAME = "Test Librarian";


	//private static final String LIBRARY_NAME = "Test Library"; //library ID 
	//private static final String LIB_ADDRESS = "123 Lib Street"; 
	//private static final String LIB_PHONE = "905-999-9999";
	//private static final String LIB_EMAIL = "lib@email.ca";
	
	
	private static final long SCHED1 = 1; //Sunday (beginning of week)
	private static final long SCHED2 = 2;
	private static final long SCHED3 = 3;
	private static final long SCHED4 = 4;
	private static final long SCHED5 = 5;
	private static final long SCHED6 = 6;
	private static final long SCHED7 = 7; //Saturday (end of week)
	
	private static final String WEEKDAY_OPEN = "7:00:00";
	private static final String WEEKDAY_CLOSE = "22:00:00";
	
	private static final String WEEKEND_OPEN = "7:30:00";
	private static final String WEEKEND_CLOSE = "19:00:00"; 
//	
	private static final DayOfWeek Monday = DayOfWeek.MONDAY; 
	private static final DayOfWeek Tuesday = DayOfWeek.TUESDAY;
	private static final DayOfWeek Wednesday = DayOfWeek.WEDNESDAY;
	private static final DayOfWeek Thursday = DayOfWeek.THURSDAY;
	private static final DayOfWeek Friday = DayOfWeek.FRIDAY;
	private static final DayOfWeek Saturday = DayOfWeek.SATURDAY;
	private static final DayOfWeek Sunday = DayOfWeek.SUNDAY;




	@BeforeEach
	public void setMockOutput() {
		lenient().when(memberRepository.findMemberByLibCardNumber(anyLong())).thenAnswer((InvocationOnMock invocation) -> { 
			if (invocation.getArgument(0).equals(LIB_CARD_NO)) {
				Member member = new Member();
				member.setLibCardNumber(LIB_CARD_NO);
				member.setAddress(MEM_ADDRESS);
				member.setActiveLoans(ACTIVE_LOANS);
				member.setAmountOwed(AMOUNT_OWED);
				member.setFullName(FULL_NAME);
				member.setIsVerifiedResident(IS_VERIFIED_RESIDENT);
				member.setPhoneNumber(PHONE_NO);
				member.setBookings(new ArrayList<Booking>());
				return member;
			}
			else {
				return null; 
			}

		});

		lenient().when(bookingRepository.findBookingByBookingID(anyLong())).thenAnswer((InvocationOnMock invocation) -> { 
			if (invocation.getArgument(0).equals(BOOKING_ID)) {
				Booking booking = new Booking();  
				booking.setBookingID(BOOKING_ID);
				
				booking.setBookingDate(java.sql.Date.valueOf(DATE));
				booking.setBookingStartTime(java.sql.Time.valueOf(START_TIME));
				booking.setBookingEndTime(java.sql.Time.valueOf(END_TIME));
				return booking;
			}
			else {
				return null;
			}

		});
		lenient().when(librarianRepository.findLibrarianByEmployeeIDNumber(anyLong())).thenAnswer((InvocationOnMock invocation) -> { 
			if (invocation.getArgument(0).equals(EMPLOYEE_ID)) {
				Librarian librarian = new Librarian(); 
				librarian.setemployeeIDNumber(EMPLOYEE_ID);
				librarian.setFullName(LIBRARIAN_FULLNAME); 
				librarian.setLibrarianEmail(LIBRARIAN_EMAIL);
				librarian.setLibrarianPassword(LIBRARIAN_PASSWORD);
				librarian.setLibrarianUsername(LIBRARIAN_USERNAME);
				librarian.setBookings(new ArrayList<Booking>());
				return librarian;
			}
			else {
				return null;
			}

		});
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
			 if (invocation.getArgument(0).equals(SCHED5)) {
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
		

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(bookingRepository.save(any(Booking.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(memberRepository.save(any(Member.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(librarianRepository.save(any(Librarian.class))).thenAnswer(returnParameterAsAnswer);
	}
 
	
	@Test 
	public void createBooking() { 
	
		Time startTime = java.sql.Time.valueOf("8:00:00");
		Time endTime = java.sql.Time.valueOf("16:00:00");
		Date bookingDate = java.sql.Date.valueOf("2023-11-30");
		
		long librarianID = EMPLOYEE_ID;
		long memberID = LIB_CARD_NO;

		Booking booking = null; 
		
		try { 
			booking = bookingService.createBooking(startTime, endTime, bookingDate, memberID, librarianID);
		}
		catch (IllegalArgumentException e) {
			fail(e.getMessage());		
		}

		assertNotNull(booking);
		assertEquals(booking.getMember().getFullName(), FULL_NAME);
	}
	

	@Test 
	public void createBookingWithNullParameters() { 
		String error = null;
		Time endTime = java.sql.Time.valueOf("16:00:00");
		Date bookingDate = java.sql.Date.valueOf("2023-11-30");
		
		long librarianID = EMPLOYEE_ID;
		long memberID = LIB_CARD_NO;

		Booking booking = null; 
		
		try { 
			booking = bookingService.createBooking(null, endTime, bookingDate, memberID, librarianID);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();

		}

		assertNotNull(error);
		assertNull(booking);
		assertEquals(error, "Please enter a start time.");
	}
	
	
	
	@Test 
	public void updateBooking() {
		long BOOKING_ID = 12345678;
		Time START_TIME = java.sql.Time.valueOf("09:00:00");
		Time END_TIME = java.sql.Time.valueOf("13:00:00");
		Date DATE = java.sql.Date.valueOf("2023-11-12");
	
		
		Booking booking = null;
	
	try { 
		booking = bookingService.updateBooking(BOOKING_ID, START_TIME, END_TIME, DATE);
	}
	catch (IllegalArgumentException e) {
		fail(e.getMessage());
		
	}
	assertNotNull(booking);
	assertEquals(START_TIME, booking.getBookingStartTime());
	assertEquals(END_TIME, booking.getBookingEndTime());
	}
	
	@Test 
	public void updateBookingWithAcceptableNullParameters() {
		long BOOKING_ID = 12345678;
		Time START_TIME = java.sql.Time.valueOf("09:00:00");
	
		
		Booking booking = null;
	
	try { 
		booking = bookingService.updateBooking(BOOKING_ID, START_TIME, null, null);
	}
	catch (IllegalArgumentException e) { 
		fail(e.getMessage());
		
	}
	assertNotNull(booking);
	assertEquals(START_TIME, booking.getBookingStartTime());
	assertEquals(END_TIME, booking.getBookingEndTime().toString());
	assertEquals(DATE, booking.getBookingDate().toString());
	}
	
	
	@Test 
	public void getBookingByBookingID() {

		Booking booking = null;
	
		
		try {
			booking = bookingService.getBookingById(BOOKING_ID);
		}
		catch (IllegalArgumentException e) { 
			fail();
			
		} 
		
		assertNotNull(booking);
		assertEquals(booking.getBookingDate().toString(), DATE);
			
	}
	
	@Test 
	public void getBookingByInvalidBookingID() {

		Booking booking = null;
	
		
		try {
			booking = bookingService.getBookingById((long) 2);
		}
		catch (IllegalArgumentException e) { 
			fail();
			
		} 
		
		assertNull(booking);
		//assertEquals(booking.getBookingDate().toString(), DATE);
			
	}
	
	@Test 
	public void getBookingsByMember() {

		List<Booking> bookings = null;
	
		
		try {
			bookings = bookingService.getBookingsByMember(LIB_CARD_NO);
		}
		catch (IllegalArgumentException e) { 
			fail();
			
		} 
		
		assertNotNull(bookings);
			
	}
	
	
	@Test 
	public void getBookingsByLibrarian() {

		List<Booking> bookings = null;
		
		try {
			bookings = bookingService.getBookingsByLibrarian(EMPLOYEE_ID);
		}
		catch (IllegalArgumentException e) { 
			fail();
			
		} 
		
		assertNotNull(bookings);
			
	}
	
	@Test 
	public void getBookingsByDate() {

		List<Booking> bookings = null;
		
		try {
			bookings = bookingService.getBookingsByDate(java.sql.Date.valueOf(DATE));
		}
		catch (IllegalArgumentException e) { 
			fail();
			
		} 
		
		assertNotNull(bookings);
			
	}
	
	@Test 
	public void getAllBookings() {

		List<Booking> bookings = null;
		
		try {
			bookings = bookingService.getAllBookings();
		}
		catch (IllegalArgumentException e) { 
			fail();
			
		} 
		
		assertNotNull(bookings);
			
	}
	
	
	@Test 
	public void deleteBooking() {
		Member member = new Member();
		member.setFullName(FULL_NAME);
		member.setAddress(MEM_ADDRESS);
		member.setActiveLoans(ACTIVE_LOANS);
		member.setAmountOwed(AMOUNT_OWED);
		member.setLibCardNumber(LIB_CARD_NO);
		member.setIsVerifiedResident(IS_VERIFIED_RESIDENT);
		member.setPhoneNumber(PHONE_NO);

		Librarian librarian = new Librarian();
		librarian.setFullName(LIBRARIAN_FULLNAME);
		librarian.setemployeeIDNumber(EMPLOYEE_ID);
		librarian.setLibrarianEmail(LIBRARIAN_EMAIL);
		librarian.setLibrarianUsername(LIBRARIAN_USERNAME);
		librarian.setLibrarianPassword(LIBRARIAN_PASSWORD);

		Booking booking = new Booking();
		booking.setBookingDate(java.sql.Date.valueOf(DATE));
		booking.setBookingEndTime(java.sql.Time.valueOf(START_TIME));
		booking.setBookingStartTime(java.sql.Time.valueOf(END_TIME));
		booking.setBookingID(BOOKING_ID);
		booking.setMember(member);
		booking.setLibrarian(librarian);
		

		try {
			booking = bookingService.deleteBooking(BOOKING_ID);
		}
		catch (IllegalArgumentException e) {
			fail();
		}
		
		assertNotNull(booking);
	}
}
