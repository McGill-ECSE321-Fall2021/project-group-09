package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import ca.mcgill.ecse321.projectgroup09.dao.*;
import ca.mcgill.ecse321.projectgroup09.models.*;



@Service
public class BookingService { // service class for booking out the library for event 

	@Autowired 
	private BookingRepository bookingRepository;
	
	@Autowired 
	private MemberRepository memberRepository; 
	
	@Autowired 
	private LibraryRepository libraryRepository; 
	
	@Autowired 
	private OnlineMemberRepository onlineMemberRepository; 
	
	@Autowired 
	private LibrarianRepository librarianRepository;
	
	/**
	 * Method assumes that user is already logged into their account
	 * @param startTime
	 * @param endTime
	 * @param bookingID
	 * @param bookingDate
	 * @param onlineMember
	 * @return
	 */
	
	
	public Booking createBooking (Time startTime, Time endTime, Long bookingID, Date bookingDate, OnlineMember onlineMember) {
	
		//checks for account go here?
		
		
		//checks for startTime, endTime, and bookingDate
		
		if (startTime.toLocalTime().isAfter(endTime.toLocalTime())) {
			throw new IllegalArgumentException("Start time cannot be later than end time");
		}
		
		if (onlineMember == null) {
			throw new IllegalArgumentException("Error: Member is null"); 
		}
		
		if (bookingDate.toLocalDate().isBefore(java.time.LocalDate.now())) {
			throw new IllegalArgumentException("Cannot pick a date in the past. Please choose another date."); 
		}
		
		//Insert checks to ensure booking is within library hours and not overlapping with other bookings
		
		Booking booking = new Booking(); 
		booking.setBookingStartTime(startTime);
		booking.setBookingDate(bookingDate);
		booking.setBookingEndTime(endTime);
		booking.setBookingID(bookingID);
		booking.setMember(onlineMember);
		
		bookingRepository.save(booking);
		return booking;
		
	}
	
	
	public List<Booking> getBookingsByMember(OnlineMember onlineMember) {
		List<Booking> bookings = new ArrayList<Booking>();
		
		
		return bookings;
	}
	
	
	public void deleteBooking(Booking aBooking) {
		//insert 
	}
	

	
	
}
