package ca.mcgill.ecse321.projectgroup09.models;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Schedule {
	/** The ID's in this list are reserved for schedules that correspond to the library hours. 
	 *  No other schedules should be created with these ID's. */
	public static List<Long> RESERVED_IDS = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L);

	// automatically generate schedule ID's, keep this one generating ID's the manual
	// way so that we can keep reserved ids for library hours.
	/** Schedule ID's start at 8, because first 7 are reserved for library hours. */
	private static Long nextScheduleId = 8L;
	private static Long getNextScheduleId() {
		Long nextId = nextScheduleId;
		nextScheduleId++;
		return nextId;
	}
	
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

	/**
	 * Default no-arg constructor.
	 */
	public Schedule() {
		this.scheduleID = getNextScheduleId();
	}
	
	/**
	 * Arg constructor for Schedule. Schedule Id will be initialized automatically.
	 * Use setScheduleId to set it manually after initializing schedule object.
	 * @param aOpeningTime
	 * @param aClosingTime
	 * @param aDayOfWeek
	 * @param aLibrarian
	 */
	public Schedule(Time aOpeningTime, Time aClosingTime, DayOfWeek aDayOfWeek,
			Librarian aLibrarian) {
		this.scheduleID = getNextScheduleId();
		this.openingTime = aOpeningTime;
		this.closingTime = aClosingTime;
		this.dayOfWeek = aDayOfWeek;
		this.librarian = aLibrarian;
	}

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

	@ManyToOne(targetEntity = Librarian.class)
	public Librarian getLibrarian() {
		return this.librarian;
	}

	public void setLibrarian(Librarian aLibrarian) {
		this.librarian = aLibrarian;
	}
	
	/**
	 * Two schedules are equal if all attributes are equal. Schedule.
	 */
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof Schedule)) {
			return false;
		}
		Schedule s = (Schedule) o;
		if (!this.scheduleID.equals(s.scheduleID)) {
			return false;
		}
		if (!this.dayOfWeek.equals(s.dayOfWeek)) {
			return false;
		}
		if (!this.openingTime.equals(s.openingTime)) {
			return false;
		}
		if (!this.closingTime.equals(s.closingTime)) {
			return false;
		}
		if (!this.librarian.equals(s.getLibrarian())) {
			return false;
		}
		return true;
	}
}
