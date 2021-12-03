package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
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
	private LibrarianRepository librarianRepository;

	@SuppressWarnings("unused")
	@Autowired
	private ScheduleRepository scheduleRepository;


	/**
	 * Associaties librarian and member with new booking.
	 * @param startTime
	 * @param endTime
	 * @param bookingID
	 * @param bookingDate
	 * @param memberID
	 * @param librarianID
	 * @return booking
	 * @throws IllegalArgumentException 
	 */
	@Transactional
	public Booking createBooking (Time startTime, Time endTime, Date bookingDate, Long memberID, Long librarianID) throws IllegalArgumentException {

		Member member = memberRepository.findMemberByLibCardNumber(memberID);
		Librarian librarian = librarianRepository.findLibrarianByEmployeeIDNumber(librarianID);
		if (member == null) {
			throw new IllegalArgumentException("Member does not exist.");
		}
		if (librarian == null) {
			throw new IllegalArgumentException("Librarian does not exist.");
		}
		
		if (startTime == null) {
			throw new IllegalArgumentException("Please enter a start time.");
		}
		
		if (endTime == null) {
			throw new IllegalArgumentException("Please enter an end time.");
		}
		
		if (bookingDate == null) {
			throw new IllegalArgumentException("Please enter a date.");
		}
//
//		Time sTime = java.sql.Time.valueOf(startTime);
//		Time eTime = java.sql.Time.valueOf(endTime);
//		Date date = java.sql.Date.valueOf(bookingDate);

		Time sTime = startTime;
		Time eTime = endTime;
		Date date = bookingDate;

		if (sTime.toLocalTime().isAfter(eTime.toLocalTime())) {
			throw new IllegalArgumentException("Start time cannot be later than end time");
		}

		if (eTime.toLocalTime().isBefore(sTime.toLocalTime())) {
			throw new IllegalArgumentException("End time cannot be earlier than start time");
		}

		if (date.toLocalDate().isBefore(java.time.LocalDate.now())) {
			throw new IllegalArgumentException("Cannot pick a date in the past. Please choose another date."); 
		}

//		//Check to ensure that booking is within library opening hours 
//		Schedule libHours = scheduleRepository.findScheduleByScheduleID(getDayOfWeekForScheduleID(date));
//		if (libHours == null) {
//			throw new IllegalArgumentException("Please add library hours");
//		}
//		
//		if (sTime.toLocalTime().isBefore(libHours.getOpeningTime().toLocalTime())) {
//			throw new IllegalArgumentException("The library opens at " + libHours.getOpeningTime().toString() + ", please choose a later time.");
//		}
//
//		if (eTime.toLocalTime().isAfter(libHours.getClosingTime().toLocalTime())) {
//			throw new IllegalArgumentException("The library closes at " + libHours.getClosingTime().toString() + ", please choose an earlier time.");
//		}

		Booking booking = new Booking(); 
		booking.setBookingDate(date);
		booking.setBookingStartTime(sTime);
		booking.setBookingEndTime(eTime);
		booking.setMember(member);
		booking.setLibrarian(librarian);

		booking = bookingRepository.save(booking);
		
		member.getBookings().add(booking);
		librarian.getBookings().add(booking);
		
		// save associations
		librarianRepository.save(librarian);
		memberRepository.save(member);
		
		return booking;

	}

	@SuppressWarnings("unused")
	private long getDayOfWeekForScheduleID(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		long dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek;
	}

	@Transactional
	public Booking updateBooking (Long id, Time startTime, Time endTime, Date bookingDate) {

		Time sTime = null;
		Time eTime = null;
		Date date = null;


		Booking booking = bookingRepository.findBookingByBookingID(id);

		//If any inputs are null, it means they are not being updated --> take the original booking's parameters 
		if (startTime == null) { 
			sTime = booking.getBookingStartTime();
		}
		else {
			sTime = startTime;
		}

		if (endTime == null ) { 
			eTime = booking.getBookingEndTime();
		}
		else {
			eTime = endTime;
		}

		if (bookingDate == null) {
			date = booking.getBookingDate();
		}
		else {
			date = bookingDate;
		}

		//Errors 
		if (sTime.toLocalTime().isAfter(eTime.toLocalTime())) {
			throw new IllegalArgumentException("Start time cannot be later than end time");
		}

		if (eTime.toLocalTime().isBefore(sTime.toLocalTime())) {
			throw new IllegalArgumentException("End time cannot be earlier than start time");
		}

		if (date.toLocalDate().isBefore(java.time.LocalDate.now())) {
			throw new IllegalArgumentException("Cannot pick a date in the past. Please choose another date."); 
		}

		//Check to ensure that new booking hours are within library opening hours 
//		Schedule libHours = scheduleRepository.findScheduleByScheduleID(getDayOfWeekForScheduleID(date));
//
//		if (sTime.toLocalTime().isBefore(libHours.getOpeningTime().toLocalTime())) {
//			throw new IllegalArgumentException("The library opens at " + libHours.getOpeningTime().toString() + ", please choose a later time..");
//		}
//
//		if (eTime.toLocalTime().isAfter(libHours.getClosingTime().toLocalTime())) {
//			throw new IllegalArgumentException("The library closes at " + libHours.getClosingTime().toString() + ", please choose an earlier time.");
//		}

		booking.setBookingStartTime(sTime);
		booking.setBookingDate(date);
		booking.setBookingEndTime(eTime);

		bookingRepository.save(booking);
		return booking;
	}

	@Transactional
	public Booking getBookingById(Long Id) {
		Booking booking = bookingRepository.findBookingByBookingID(Id);
		return booking;
	}

	@Transactional
	public List<Booking> getBookingsByMember(long libCardNo) {
		return toList(bookingRepository.findByMember(memberRepository.findMemberByLibCardNumber(libCardNo)));
	}

	@Transactional
	public List<Booking> getBookingsByLibrarian(long employeeID) {
		return toList(bookingRepository.findByLibrarian(librarianRepository.findLibrarianByEmployeeIDNumber(employeeID)));
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
	public Booking deleteBooking(long Id) {
		Booking booking = bookingRepository.findBookingByBookingID(Id);
		bookingRepository.delete(booking);
		return booking;
	}

	<T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}







}
