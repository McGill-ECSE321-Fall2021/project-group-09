package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Date;
import java.sql.Time;

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
	
	@Transactional
	public Booking createBooking (Time startTime, Time endTime, Long bookingID, Date bookingDate, Member member, Librarian librarian) {
		
		
		//checks for startTime, endTime, and bookingDate
		
		if (startTime.toLocalTime().isAfter(endTime.toLocalTime())) {
			throw new IllegalArgumentException("Start time cannot be later than end time");
		}
		
		if (member == null) {
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
		booking.setMember(member);
		booking.setLibrarian(librarian);
		
		bookingRepository.save(booking);
		return booking;
		
	}
	

	@Transactional
	public Booking getBookingByID(Long ID) {
		Booking booking = bookingRepository.findBookingByBookingID(ID);
		return booking;
	}
	
	public List<Booking> getBookingsByMember(OnlineMember member) {
		return toList(bookingRepository.findByMember(member));
	}
	
	public List<Booking> getBookingsByLibrary(Librarian librarian) {
		return toList(bookingRepository.findByLibrarian(librarian));
	}
	
	public List<Booking> getAllBookings() {
		return toList(bookingRepository.findAll());
		
	}

	@Transactional
	public void deleteBooking(Booking aBooking) {
		//insert 
	}
	
	<T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}



	

	
	
}
