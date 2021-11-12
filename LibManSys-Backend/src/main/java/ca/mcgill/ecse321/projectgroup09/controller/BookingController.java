package ca.mcgill.ecse321.projectgroup09.controller;

import java.sql.Date;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.BookingDto;
import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.service.BookingService;

@CrossOrigin(origins = "*")
@RestController
public class BookingController {

	
	@Autowired 
	private BookingService bookingService; 

	@GetMapping(value = {"/bookings", "/bookings/"})
	public List<BookingDto> getAllBookings() {
		return bookingService.getAllBookings().stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
	}
	
	@PostMapping(value = {"/bookings/create/{bookingID}/{startTime}/{endTime}/{bookingDate}/{memberID}/{librarianID}", "bookings/create/{bookingID}/{startTime}/{endTime}/{bookingDate}/{memberID}/{librarianID}/"})
	public BookingDto createBooking (@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime, @PathVariable("bookingID") Long bookingID, @PathVariable("bookingDate") String bookingDate, @PathVariable("memberID") long memberID, @PathVariable("librarianID") long librarianID) throws IllegalArgumentException {
		Booking booking = bookingService.createBooking(startTime, endTime, bookingID, bookingDate, memberID, librarianID);
		return BookingDto.convertToDto(booking);
	}
	

	@PostMapping(value = {"/bookings/update/{bookingID}/{startTime}/{endTime}/{bookingDate}", "bookings/update/{bookingID}/{startTime}/{endTime}/{bookingDate}/"})
	public BookingDto updateBooking (@PathVariable("id") Long id, @PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime, @PathVariable("bookingDate") String bookingDate) {
		Booking booking = bookingService.updateBooking(id, startTime, endTime, bookingDate);
		return BookingDto.convertToDto(booking);
	}

	@GetMapping(value = {"/bookings/{bookingID}", "/bookings/{bookingID}/"})
	public BookingDto getBookingById(@PathVariable("Id") Long Id) {
		return BookingDto.convertToDto(bookingService.getBookingById(Id));
	}

	@GetMapping(value = {"/bookings/member/{memberID}", "/bookings/member/{memberID}/"})
	public List<BookingDto> getBookingsByMember(@PathVariable("memberID") long memberID) {
		return bookingService.getBookingsByMember(memberID).stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
	}

	@GetMapping(value = {"/bookings/librarian/{librarianID}", "/bookings/librarian/{librarianID}/"})
	public List<BookingDto> getBookingsByLibrarian(@PathVariable("employeeID") long employeeID) {
		return bookingService.getBookingsByLibrarian(employeeID).stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
	}

	@GetMapping(value = {"/bookings/{date]", "/bookings/{date}/"})
	public List<BookingDto> getBookingsByDate(@PathVariable("date") Date date) {
		return bookingService.getBookingsByDate(date).stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
	}
	
	@DeleteMapping(value = {"/bookings/{bookingID}", "/bookings/{bookingID}/"})
	public BookingDto deleteBooking(@PathVariable("Id") long Id) {
		Booking booking = bookingService.deleteBooking(Id);
		return BookingDto.convertToDto(booking);
	}

	
}
