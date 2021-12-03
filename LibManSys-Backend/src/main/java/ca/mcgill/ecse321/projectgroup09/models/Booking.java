package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.sql.Date;



@Entity
public class Booking {

	
	// Booking Attributes
	private Time bookingStartTime;
	private Time bookingEndTime;
	private Long bookingID;
	private Date bookingDate;

	// Booking Associations
	private Member member;
	private Librarian librarian;
	
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
		return this.bookingStartTime;
	}

	public Time getBookingEndTime() {
		return this.bookingEndTime;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getBookingID() {
		return this.bookingID;
	}

	public Date getBookingDate() {
		return this.bookingDate;
	}

	@ManyToOne
	public Member getMember() {
		return member;
	}

	@ManyToOne
	public Librarian getLibrarian() {
		return librarian;
	}


	public void setMember(Member aMember) {
		this.member = aMember;
	}

	public void setLibrarian(Librarian aLibrarian) {
		this.librarian = aLibrarian;
	}

	
}

