package ca.mcgill.ecse321.projectgroup09.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Member;

public class BookingDto {

	// Booking Attributes
	private Time bookingStartTime;
	private Time bookingEndTime;
	private Long bookingID;
	private Date bookingDate;
	
	// Associations
	/** Lib card number of associated member. */
	private Long memberLibCardNumber;
	/** Employee ID number of associated librarian */
	private Long librarianEmployeeID;

	public BookingDto() {}

	public BookingDto(Time bookingStartTime, Time bookingEndTime, Long bookingID, Date bookingDate, Member member, Librarian librarian) { 
		this.bookingDate = bookingDate; 
		this.bookingEndTime = bookingEndTime; 
		this.bookingStartTime = bookingStartTime;
		this.bookingID = bookingID;
		this.memberLibCardNumber = member.getLibCardNumber();
		this.librarianEmployeeID = librarian.getemployeeIDNumber();
		
	}

	public void setBookingStartTime(Time aBookingStartTime) {
		this.bookingStartTime = aBookingStartTime;
	}

	public void setBookingEndTime(Time aBookingEndTime) {
		this.bookingEndTime = aBookingEndTime;
	}

	public void setBookingID(Long aBookingID) {
		this.bookingID = aBookingID;
	}

	public void setBookingDate(Date aBookingDate) {
		this.bookingDate = aBookingDate;
	}

	public Time getBookingStartTime() {
		return bookingStartTime;
	}

	public Time getBookingEndTime() {
		return bookingEndTime;
	}

	public Long getBookingID() {
		return bookingID;
	}

	public Date getBookingDate() {
		return bookingDate;
	}
	
	public static List<BookingDto> convertToDto(List<? extends Booking> bookings) {
		return bookings.stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
	}

	public static BookingDto convertToDto(Booking booking) {
		BookingDto convertToDto = new BookingDto(
			booking.getBookingStartTime(),
			booking.getBookingEndTime(),
			booking.getBookingID(),
			booking.getBookingDate(),
			booking.getMember(),
			booking.getLibrarian()
			);
				
		return convertToDto;
	}

	/**
	 * @return the librarian
	 */
	public Long getLibrarianEmployeeID() {
		return librarianEmployeeID;
	}

	/**
	 * @param librarian the librarian to set
	 */
	public void setLibrarianEmployeeID(Long librarian) {
		this.librarianEmployeeID = librarian;
	}

	/**
	 * @return the member
	 */
	public Long getMemberLibCardNumber() {
		return memberLibCardNumber;
	}

	/**
	 * @param member the member to set
	 */
	public void setMemberLibCardNumber(Long member) {
		this.memberLibCardNumber = member;
	} 
	

}
