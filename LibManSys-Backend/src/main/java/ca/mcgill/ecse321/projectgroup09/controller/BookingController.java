package ca.mcgill.ecse321.projectgroup09.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailure;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import ca.mcgill.ecse321.projectgroup09.dto.BookingDto;
import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.service.BookingService;

@CrossOrigin(origins = "*")
@RestController
public class BookingController {


	@Autowired 
	private BookingService bookingService;  

	@PostMapping(value = {"/bookings/new", "/bookings/new/"})
	public ResponseEntity<?> createBooking (@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime, 
			@RequestParam("bookingDate") String bookingDate, @RequestParam("memberID") long memberID, @RequestParam("librarianID") long librarianID) throws IllegalArgumentException {

		// Time parameters
		Time sTime = null;
		try {
		 sTime =java.sql.Time.valueOf(startTime);
		}
		catch (Exception e) {
			httpFailureMessage(e.getMessage());
		}	
		Time eTime = null;
		try {
		 eTime =java.sql.Time.valueOf(endTime);
		}
		catch (Exception e) {
			httpFailureMessage(e.getMessage());
		}
		
		Date date = null;
		try {
			 date = java.sql.Date.valueOf(bookingDate);
			}
			catch (Exception e) {
				httpFailureMessage(e.getMessage());
			}
		
		try {	
			Booking booking = bookingService.createBooking(sTime, eTime, date, memberID, librarianID);
			return httpSuccess(BookingDto.convertToDto(booking));
		} catch (Exception e) {
			httpFailureMessage(e.getMessage());
			return httpFailure("Error: " + e.getMessage());
		}

	}

	@GetMapping(value = {"/bookings/view-all", "/bookings/view-all/"})
	public ResponseEntity<?> getAllBookings() {
		List<BookingDto> bookings = BookingDto.convertToDto(bookingService.getAllBookings());
		return httpSuccess(bookings);
	}


	@PutMapping(value = {"/bookings/update", "bookings/update/"})
	public ResponseEntity<?> updateBooking (@RequestParam(value = "id", required = true) Long id, @RequestParam(value = "startTime", required = false) String startTime, @RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "bookingDate", required = false) String bookingDate) {
		
		Time sTime = null;
		try {
		 sTime =java.sql.Time.valueOf(startTime);
		}
		catch (Exception e) {
			httpFailureMessage(e.getMessage());
		}	
		Time eTime = null;
		try {
		 eTime =java.sql.Time.valueOf(endTime);
		}
		catch (Exception e) {
			httpFailureMessage(e.getMessage());
		}
		
		Date date = null;
		try {
			 date = java.sql.Date.valueOf(bookingDate);
			}
			catch (Exception e) {
				httpFailureMessage(e.getMessage());
			}
		
		try {
			Booking booking = bookingService.updateBooking(id, sTime, eTime, date);
			return httpSuccess(BookingDto.convertToDto(booking));
		}
		catch (Exception e) {
			httpFailureMessage(e.getMessage());
			return httpFailure("Error: " + e.getMessage());
		}
	}

	@GetMapping(value = {"/bookings/getID/{id}", "/bookings/getID/{id}/"})
	public ResponseEntity<?> getBookingById(@PathVariable("id") Long Id) {
		try {
			BookingDto booking = BookingDto.convertToDto(bookingService.getBookingById(Id));
			return httpSuccess(booking);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}

	@GetMapping(value = {"/bookings/member/{memberID}", "/bookings/member/{memberID}/"})
	public ResponseEntity<?> getBookingsByMember(@PathVariable("memberID") long memberID) {
		List<BookingDto> bookings = BookingDto.convertToDto(bookingService.getBookingsByMember(memberID));
		return httpSuccess(bookings);
	}

	@GetMapping(value = {"/bookings/librarian/{librarianID}", "/bookings/librarian/{librarianID}/"})
	public ResponseEntity<?> getBookingsByLibrarian(@PathVariable("employeeID") long employeeID) {
		List<BookingDto> bookings = BookingDto.convertToDto(bookingService.getBookingsByLibrarian(employeeID));
		return httpSuccess(bookings);
	}

	@GetMapping(value = {"/bookings/get/{date}", "/bookings/get/{date}/"})
	public ResponseEntity<?> getBookingsByDate(@PathVariable("date") Date date) {
		List<BookingDto> bookings = BookingDto.convertToDto(bookingService.getBookingsByDate(date));
		return httpSuccess(bookings);
	}

	@DeleteMapping(value = {"/bookings/delete/{Id}", "/bookings/delete/{Id}/"})
	public ResponseEntity<?> deleteBooking(@PathVariable("Id") Long Id) {
		bookingService.deleteBooking(Id);
		return httpSuccess("Booking successfully deleted");
	}


}
