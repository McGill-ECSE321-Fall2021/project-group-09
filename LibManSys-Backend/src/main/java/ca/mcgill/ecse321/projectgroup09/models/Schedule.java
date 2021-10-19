package ca.mcgill.ecse321.projectgroup09.models;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Schedule {
	
	public enum DayofWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}

	// Schedule Attributes

	private Long scheduleID;
	private Time openingTime;
	private Time closingTime;
	private DayofWeek dayofWeek;

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

	public void setDayofWeek(DayofWeek aDayOfWeek) {
		this.dayofWeek = aDayOfWeek; 
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

	public DayofWeek getDayofWeek() {
		return this.dayofWeek;
	}

	@ManyToOne
	public Librarian getLibrarian() {
		return this.librarian;
	}

	public void setLibrarian(Librarian aLibrarian) {
		this.librarian = aLibrarian;
		}

}
