package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.sql.Time;
import java.util.*;

@Entity
public class Booking {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Booking Attributes
	private Time bookingStartTime;
	private Time bookingEndTime;
	@Id
	private int bookingID;
	private Date bookingDate;

	// Booking Associations
	private Member member;
	private Librarian librarian;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Booking(Time aBookingStartTime, Time aBookingEndTime, int aBookingID, Date aBookingDate, Member aMember) {
		bookingStartTime = aBookingStartTime;
		bookingEndTime = aBookingEndTime;
		bookingID = aBookingID;
		bookingDate = aBookingDate;
		boolean didAddMember = setMember(aMember);
		if (!didAddMember) {
			throw new RuntimeException(
					"Unable to create booking due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setBookingStartTime(Time aBookingStartTime) {
		boolean wasSet = false;
		bookingStartTime = aBookingStartTime;
		wasSet = true;
		return wasSet;
	}

	public boolean setBookingEndTime(Time aBookingEndTime) {
		boolean wasSet = false;
		bookingEndTime = aBookingEndTime;
		wasSet = true;
		return wasSet;
	}

	public boolean setBookingID(int aBookingID) {
		boolean wasSet = false;
		bookingID = aBookingID;
		wasSet = true;
		return wasSet;
	}

	public boolean setBookingDate(Date aBookingDate) {
		boolean wasSet = false;
		bookingDate = aBookingDate;
		wasSet = true;
		return wasSet;
	}

	public Time getBookingStartTime() {
		return bookingStartTime;
	}

	public Time getBookingEndTime() {
		return bookingEndTime;
	}

	public int getBookingID() {
		return bookingID;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	/* Code from template association_GetOne */
	public Member getMember() {
		return member;
	}

	/* Code from template association_GetOne */
	public Librarian getLibrarian() {
		return librarian;
	}

	public boolean hasLibrarian() {
		boolean has = librarian != null;
		return has;
	}

	/* Code from template association_SetOneToMany */
	public boolean setMember(Member aMember) {
		boolean wasSet = false;
		if (aMember == null) {
			return wasSet;
		}

		Member existingMember = member;
		member = aMember;
		if (existingMember != null && !existingMember.equals(aMember)) {
			existingMember.removeBooking(this);
		}
		member.addBooking(this);
		wasSet = true;
		return wasSet;
	}

	/* Code from template association_SetOptionalOneToMany */
	public boolean setLibrarian(Librarian aLibrarian) {
		boolean wasSet = false;
		Librarian existingLibrarian = librarian;
		librarian = aLibrarian;
		if (existingLibrarian != null && !existingLibrarian.equals(aLibrarian)) {
			existingLibrarian.removeBooking(this);
		}
		if (aLibrarian != null) {
			aLibrarian.addBooking(this);
		}
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		Member placeholderMember = member;
		this.member = null;
		if (placeholderMember != null) {
			placeholderMember.removeBooking(this);
		}
		if (librarian != null) {
			Librarian placeholderLibrarian = librarian;
			this.librarian = null;
			placeholderLibrarian.removeBooking(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "bookingID" + ":" + getBookingID() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "bookingStartTime" + "="
				+ (getBookingStartTime() != null ? !getBookingStartTime().equals(this)
						? getBookingStartTime().toString().replaceAll("  ", "    ")
						: "this" : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "bookingEndTime" + "="
				+ (getBookingEndTime() != null
						? !getBookingEndTime().equals(this) ? getBookingEndTime().toString().replaceAll("  ", "    ")
								: "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "bookingDate" + "="
				+ (getBookingDate() != null
						? !getBookingDate().equals(this) ? getBookingDate().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "member = "
				+ (getMember() != null ? Integer.toHexString(System.identityHashCode(getMember())) : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "librarian = "
				+ (getLibrarian() != null ? Integer.toHexString(System.identityHashCode(getLibrarian())) : "null");
	}
}
