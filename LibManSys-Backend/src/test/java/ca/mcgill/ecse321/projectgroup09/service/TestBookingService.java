package ca.mcgill.ecse321.projectgroup09.service;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.dao.*;

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
	private static final String DATE = "2021-11-11";

	private static final String NEW_START_TIME = "10:00:00";
	private static final String NEW_END_TIME = "18:00:00";
	private static final String NEW_DATE = "2021-12-12";

	private static final long LIB_CARD_NO = 999999999; //member ID 
	private static final String MEM_ADDRESS = "123 Test Address";
	private static final boolean IS_RESIDENT = true; 
	private static final double AMOUNT_OWED = 0; 
	private static final int ACTIVE_LOANS = 0; 
	private static final boolean IS_VERIFIED = true; 
	private static final String FULL_NAME = "Test Member";
	private static final String PHONE_NO = "123-456-7890";

	private static final long EMPLOYEE_ID = 123456789; //librarian ID
	private static final String LIBRARIAN_EMAIL = "librarian@email.com";
	private static final String LIBRARIAN_PASSWORD = "ASDF12343";
	private static final String LIBRARIAN_USERNAME = "username";
	private static final String LIBRARIAN_FULLNAME = "Test Librarian";


	private static final String LIBRARY_ID = "Test Library"; //library ID 
	private static 




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
				member.setIsResident(IS_RESIDENT);
				member.setIsVerified(IS_VERIFIED);
				member.setPhoneNumber(PHONE_NO);
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
				booking.setBookingDate(Date.valueOf(LocalDate.parse(DATE)));
				booking.setBookingStartTime(Time.valueOf(LocalTime.parse(START_TIME)));
				booking.setBookingEndTime(Time.valueOf(LocalTime.parse(END_TIME)));
				return booking;
			}
			else {
				return null;
			}

		});
		lenient().when(librarianRepository.findLibrarianByEmployeeIDNum(anyLong())).thenAnswer((InvocationOnMock invocation) -> { 
			if (invocation.getArgument(0).equals(EMPLOYEE_ID)) {
				Librarian librarian = new Librarian(); 
				librarian.setemployeeIDNum(EMPLOYEE_ID);
				librarian.setFullName(LIBRARIAN_FULLNAME); 
				librarian.setLibrarianEmail(LIBRARIAN_EMAIL);
				librarian.setLibrarianPassword(LIBRARIAN_PASSWORD);
				librarian.setLibrarianUsername(LIBRARIAN_USERNAME);
				return librarian;
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
		long bookingID = 12345678;
		String startTime = "8:00:00";
		String endTime = "16:00:00";
		String bookingDate = "2021-11-11";
		
		long librarianID = 12345678;
		long memberID = 999999999;


		//		Member member = new Member();
		//		member.setFullName("Test Member");
		//		member.setAddress("123 Test Address");
		//		member.setActiveLoans(0);
		//		member.setAmountOwed(0);
		//		member.setLibCardNumber((long) 999999999);
		//		member.setIsResident(true);
		//		member.setIsVerified(true);
		//		member.setPhoneNumber("123-456-7890");
		//		
		//		Librarian librarian = new Librarian();
		//		librarian.setFullName("Test Librarian");
		//		librarian.setemployeeIDNum((long) 12345678);
		//		librarian.setLibrarianEmail("librarian@email.com");
		//		librarian.setLibrarianUsername("username");
		//		librarian.setLibrarianPassword("ASDF1234");

		Booking booking = null; 
		
		try { 
			booking = bookingService.createBooking(startTime, endTime, bookingID, bookingDate, memberID, librarianID);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e);
			fail();
			
		}

		assertNotNull(booking);
		assertEquals(bookingID, booking.getBookingID());
		assertEquals(booking.getMember().getFullName(), FULL_NAME);
		
	}

	@Test 
	public void updateBooking() {
		
	}
	
	@Test 
	public void deleteBooking() {
		
	}
}
