package ca.mcgill.ecse321.projectgroup09.dto;

import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse321.projectgroup09.models.Booking;

public class BookingDto {

	// Booking Attributes
	private Time bookingStartTime;
	private Time bookingEndTime;
	private Long bookingID;
	private Date bookingDate;
	private MemberDto member;
	private LibrarianDto librarian;

	public BookingDto() {}

	public BookingDto(Time bookingStartTime, Time bookingEndTime, Long bookingID, Date bookingDate) { 
		this.bookingDate = bookingDate; 
		this.bookingEndTime = bookingEndTime; 
		this.bookingStartTime = bookingStartTime;
		this.bookingID = bookingID;
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

	public void setMember(MemberDto member) {
		this.member = member;
	}

	public MemberDto getMember() {
		return member; 
	}

	public void setLibrarian(LibrarianDto librarian) {
		this.librarian = librarian;
	}

	public LibrarianDto getLibrarian() {
		return librarian;
	}

	public static BookingDto convertToDto(Booking booking) {
		return new BookingDto();
	}
}
