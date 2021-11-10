package ca.mcgill.ecse321.projectgroup09.models;

import java.sql.Time;
import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Schedule {
	
	/* Use java.time.DayOfWeek instead, same thing
	public enum DayofWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}
	*/

	// Schedule Attributes

	private Long scheduleID;
	private Time openingTime;
	private Time closingTime;
	private DayOfWeek dayOfWeek;

	// Schedule Associations
	private Librarian librarian;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	/**public Schedule(Integer ascheduleID, Time aOpeningTime, Time aClosingTime, DayOfWeek aDayOfWeek,
			Librarian aLibrarian) {
		scheduleID = ascheduleID;
		openingTime = aOpeningTime;
		closingTime = aClosingTime;
		dayOfWeek = aDayOfWeek;
		boolean didAddLibrarian = setLibrarian(aLibrarian);
		if (!didAddLibrarian) {
			throw new RuntimeException(
					"Unable to create schedule due to librarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}*/

	// ------------------------
	// INTERFACE
	// ------------------------
	
	public void setscheduleID(Long aScheduleID) {
		this.scheduleID = aScheduleID; 
	}

	public void setOpeningTime(Time anOpeningTime) {
		this.openingTime = anOpeningTime;
	}

	public void setClosingTime(Time aClosingTime) {
		this.closingTime = aClosingTime;
	}

	public void setDayofWeek(DayOfWeek aDayOfWeek) {
		this.dayOfWeek = aDayOfWeek; 
	}
	
	@Id
	public long getscheduleID() {
		return this.scheduleID;
	}

	public Time getOpeningTime() {
		return this.openingTime;
	}

	public Time getClosingTime() {
		return this.closingTime;
	}

	public DayOfWeek getDayofWeek() {
		return this.dayOfWeek;
	}

	@ManyToOne
	public Librarian getLibrarian() {
		return this.librarian;
	}

	public void setLibrarian(Librarian aLibrarian) {
		this.librarian = aLibrarian;
		}

}
