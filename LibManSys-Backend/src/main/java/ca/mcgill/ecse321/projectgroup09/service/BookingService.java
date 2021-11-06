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
	
	/**
	 * Creates new booking object for a member and assigns a timeslot and date 
	 * @param startTime
	 * @param endTime
	 * @param bookingID
	 * @param bookingDate
	 * @param onlineMember
	 * @return
	 */
	
	@Transactional
	public Booking createBooking (Time startTime, Time endTime, Long bookingID, Date bookingDate, Member member, Librarian librarian) throws IllegalArgumentException {
		
		
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
	public Booking updateBooking (Long id, Time startTime, Time endTime, Date bookingDate) {
		Time st = startTime;
		Time et = endTime; 
		Date date = bookingDate;
		
		Booking booking = bookingRepository.findBookingByBookingID(id);
		
		if (startTime.toLocalTime().isAfter(endTime.toLocalTime())) {
			throw new IllegalArgumentException("Start time cannot be later than end time");
		}
		
		if (bookingDate.toLocalDate().isBefore(java.time.LocalDate.now())) {
			throw new IllegalArgumentException("Cannot pick a date in the past. Please choose another date."); 
		}
		
		if (st == null) { 
			startTime = booking.getBookingStartTime();
		}
		
		if (et == null ) { 
			endTime = booking.getBookingEndTime();
		}
		
		if (date == null) {
			bookingDate = booking.getBookingDate();
		}
		
		//Insert checks to ensure booking is within library hours and not overlapping with other bookings
		//Using schedule service class
		
		

		booking.setBookingStartTime(startTime);
		booking.setBookingDate(bookingDate);
		booking.setBookingEndTime(endTime);

		bookingRepository.save(booking);
		return booking;
	}

	@Transactional
	public Booking getBookingById(Long Id) {
		Booking booking = bookingRepository.findBookingByBookingID(Id);
		return booking;
	}
	
	@Transactional
	public List<Booking> getBookingsByMember(OnlineMember member) {
		return toList(bookingRepository.findByMember(member));
	}
	
	@Transactional
	public List<Booking> getBookingsByLibrarian(Librarian librarian) {
		return toList(bookingRepository.findByLibrarian(librarian));
	}
	
	@Transactional 
	public List<Booking> getBookingsByDate(Date date) {
		return toList(bookingRepository.findByBookingDate(date));
	}

	
	@Transactional 
	public List<Booking> getAllBookings() {
		return toList(bookingRepository.findAll());
	}

	@Transactional
	public void deleteBooking(long Id) {
		Booking booking = bookingRepository.findBookingByBookingID(Id);
		bookingRepository.delete(booking);
	}
	
	<T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}



	

	
	
}
