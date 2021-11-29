package ca.mcgill.ecse321.projectgroup09.dto;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
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
	/** Employee Id number of associated librarian */
	private Long librarianEmployeeID;
	
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
	public ScheduleDto(Long aScheduleId, Time aOpeningTime, Time aClosingTime, DayOfWeek aDayofWeek, Librarian aLibrarian) {
		this.scheduleID = aScheduleId;
		this.openingTime = aOpeningTime;
		this.closingTime = aClosingTime;
		this.dayOfWeek = aDayofWeek;
		// get id of librarian if association is present, otherwise just set to null
		if (aLibrarian != null) {
			this.librarianEmployeeID = aLibrarian.getemployeeIDNumber();
		} else {
			this.librarianEmployeeID = null;
		}
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
	 * Return a schedule dto object representing the schedule object.
	 * @param schedule
	 * @return
	 */
	public static ScheduleDto convertToDto(Schedule schedule) {
		ScheduleDto s = new ScheduleDto(
				schedule.getscheduleID(),
				schedule.getOpeningTime(),
				schedule.getClosingTime(),
				schedule.getDayofWeek(),
				schedule.getLibrarian()
				);
		return s;
	}
	
	/**
	 * Convert a List of schedules into a list of dtos
	 * @param schedule
	 * @return
	 */
	public static List<ScheduleDto> convertToDtos(List<Schedule> schedules) {
		List<ScheduleDto> scheduleDto = schedules.stream().map(s -> ScheduleDto.convertToDto(s)).collect(Collectors.toList());
		return scheduleDto;
	}
	
}
