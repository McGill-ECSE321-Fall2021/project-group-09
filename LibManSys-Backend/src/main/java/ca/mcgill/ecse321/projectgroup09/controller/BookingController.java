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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@PostMapping(value = {"/bookings/new", "/bookings/new/"})
	public BookingDto createBooking (@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime, @RequestParam("bookingID") Long bookingID,
			@RequestParam("bookingDate") String bookingDate, @RequestParam("memberID") long memberID, @RequestParam("librarianID") long librarianID) throws IllegalArgumentException {
		Booking booking = bookingService.createBooking(startTime, endTime, bookingID, bookingDate, memberID, librarianID);
		return BookingDto.convertToDto(booking);
	}
	
	@GetMapping(value = {"/bookings/view-all", "/bookings/view-all/"})
	public List<BookingDto> getAllBookings() {
		return bookingService.getAllBookings().stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
	}
	

	@PutMapping(value = {"/bookings/update", "bookings/update/"})
	public BookingDto updateBooking (@RequestParam("id") Long id, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
			@RequestParam("bookingDate") String bookingDate) {
		Booking booking = bookingService.updateBooking(id, startTime, endTime, bookingDate);
		return BookingDto.convertToDto(booking);
	}

	@GetMapping(value = {"/bookings/getID/{bookingID}", "/bookings/getID/{bookingID}/"})
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

	@GetMapping(value = {"/bookings/get/{date}", "/bookings/get/{date}/"})
	public List<BookingDto> getBookingsByDate(@PathVariable("date") Date date) {
		return bookingService.getBookingsByDate(date).stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
	}
	
	@DeleteMapping(value = {"/bookings/delete/{bookingID}/", "/bookings/delete/{bookingID}/"})
	public BookingDto deleteBooking(@PathVariable("Id") long Id) {
		Booking booking = bookingService.deleteBooking(Id);
		return BookingDto.convertToDto(booking);
	}

	
}
