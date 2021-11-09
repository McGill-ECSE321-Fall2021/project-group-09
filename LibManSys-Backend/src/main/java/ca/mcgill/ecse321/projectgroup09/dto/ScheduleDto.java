package ca.mcgill.ecse321.projectgroup09.dto;

import java.sql.Time;
import java.time.DayOfWeek;

/**
 * Data Transfer Object class for Schedule model class.
 * Holds all attributes of Schedule class.
 */
public class ScheduleDto {
	// Schedule Attributes
	private Long scheduleID;
	private Time openingTime;
	private Time closingTime;
	private DayOfWeek dayOfWeek;

	// Schedule Associations
	private LibrarianDto librarian;
	
	/**
	 * Default No-Arg constructor.
	 */
	public ScheduleDto() {
		
	}
	
	/**
	 * Initialize a new ScheduleDto object with the specified attributes.
	 * @param aScheduleId
	 * @param aOpeningTime
	 * @param aClosingTime
	 * @param aDayofWeek
	 * @param aLibrarian
	 */
	public ScheduleDto(Long aScheduleId, Time aOpeningTime, Time aClosingTime, DayOfWeek aDayofWeek, LibrarianDto aLibrarian) {
		this.scheduleID = aScheduleId;
		this.openingTime = aOpeningTime;
		this.closingTime = aClosingTime;
		this.dayOfWeek = aDayofWeek;
		this.librarian = aLibrarian;
	}

	/**
	 * @return the scheduleID
	 */
	public Long getScheduleID() {
		return scheduleID;
	}

	/**
	 * @param scheduleID the scheduleID to set
	 */
	public void setScheduleID(Long scheduleID) {
		this.scheduleID = scheduleID;
	}

	/**
	 * @return the openingTime
	 */
	public Time getOpeningTime() {
		return openingTime;
	}

	/**
	 * @param openingTime the openingTime to set
	 */
	public void setOpeningTime(Time openingTime) {
		this.openingTime = openingTime;
	}

	/**
	 * @return the closingTime
	 */
	public Time getClosingTime() {
		return closingTime;
	}

	/**
	 * @param closingTime the closingTime to set
	 */
	public void setClosingTime(Time closingTime) {
		this.closingTime = closingTime;
	}

	/**
	 * @return the dayofWeek
	 */
	public DayOfWeek getDayofWeek() {
		return dayOfWeek;
	}

	/**
	 * @param dayofWeek the dayofWeek to set
	 */
	public void setDayofWeek(DayOfWeek dayofWeek) {
		this.dayOfWeek = dayofWeek;
	}

	/**
	 * @return the librarian
	 */
	public LibrarianDto getLibrarian() {
		return librarian;
	}

	/**
	 * @param librarian the librarian to set
	 */
	public void setLibrarian(LibrarianDto librarian) {
		this.librarian = librarian;
	}
}
