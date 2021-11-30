package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.ScheduleRepository;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

/**
 * Service class for schedule model.
 */
@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepo;
	
	@Autowired
	private LibrarianRepository lRepo;
	
	@Autowired
	private HeadLibrarianRepository hlRepo;
	
	/**
	 * Create a new schedule object and save it to schedule repository.
	 * Associates it with the given librarian.
	 * @param aOpeningTime
	 * @param aClosingTime
	 * @param aDayOfWeek
	 * @param aLibrarian
	 * @return
	 */
	@Transactional
	public Schedule createScheduleForLibrarian(Time aOpeningTime, Time aClosingTime, DayOfWeek aDayOfWeek, Long librarianId) {
		// make sure input parameters not null
		if (aOpeningTime == null || aClosingTime == null || aDayOfWeek == null || librarianId == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
		// make sure librarian exists
		Librarian aLibrarian = lRepo.findLibrarianByEmployeeIDNumber(librarianId);
		if (aLibrarian == null) {
			throw new IllegalStateException("Could not find librarian with id " + librarianId + "in repository.");
		}
		// make sure opening time before closing time
		if (!aOpeningTime.before(aClosingTime)) {
			throw new IllegalArgumentException("Cannot create a schedule with closing time before opening time.");
		}
		// make sure librarian exists
		if (lRepo.findLibrarianByEmployeeIDNumber(aLibrarian.getemployeeIDNumber()) == null) {
			throw new IllegalArgumentException("Librarian does not exist in librarian repository.");
		}
		Schedule s = new Schedule();
		s.setOpeningTime(aOpeningTime);
		s.setClosingTime(aClosingTime);
		s.setDayofWeek(aDayOfWeek);
		s.setLibrarian(aLibrarian);
		// save and return new object
		s = scheduleRepo.save(s);
		
		// update librarian associations
		aLibrarian.getSchedules().add(s);
		
		lRepo.save(aLibrarian);
		
		return s;
	}
	
	/**
	 * Returns the schedule object with ID {@code scheduleId}, if it is present in the schedule repository.
	 * @param scheduleId
	 * @return {@code Schedule} object matching {@code scheduleId} or {@code null} if not found in repository.
	 */
	@Transactional
	public Schedule getScheduleById(Long scheduleId) {
		if (scheduleId == null) {
			throw new IllegalArgumentException("Id must not be null.");
		}
		Schedule s = scheduleRepo.findScheduleByScheduleID(scheduleId);
		if (s == null) {
			throw new IllegalStateException("Schedule with id " + scheduleId + " does not exist.");
		}
		return s;
	}
	
	/**
	 * Get all schedules corresponding to a day of the week. Does not include library hour schedules.
	 * @param aDayOfWeek
	 * @return
	 */
	@Transactional
	public List<Schedule> getSchedulesByDay(DayOfWeek aDayOfWeek) {
		if (aDayOfWeek == null) {
			throw new IllegalArgumentException("Argument must not be null.");
		}
		List<Schedule> sList = scheduleRepo.findSchedulesByDayofWeek(aDayOfWeek);
		if (sList != null) {
			// do not return schedules with ID 1-7
			for (Schedule s : sList) {
				if (Schedule.RESERVED_IDS.contains(s.getscheduleID())) {
					sList.remove(s);
				}
			}
		}
		return sList;
	}
	
	/**
	 * Get all schedules corresponding to a librarian. Does not include library hour schedules.
	 * @param librarianId
	 * @return
	 */
	@Transactional
	public List<Schedule> getSchedulesByLibrarian(Long librarianId) {
		if (librarianId == null) {
			throw new IllegalArgumentException("Argument must not be null.");
		}
		Librarian librarian = lRepo.findLibrarianByEmployeeIDNumber(librarianId);
		if (librarian == null) {
			throw new IllegalStateException("Could not find librarian with id " + librarianId + "in repository.");
		}
		List<Schedule> sList = scheduleRepo.findScheduleByLibrarian(librarian);
		if (sList != null) {
			// do not return schedules with ID 1-7
			for (Schedule s : sList) {
				if (Schedule.RESERVED_IDS.contains(s.getscheduleID())) {
					sList.remove(s);
				}
			}
		}
		return sList;
	}
	
	/**
	 * Get all schedules. Does not include library hour schedules.
	 * @return
	 */
	@Transactional
	public List<Schedule> getAllSchedules() {
		List<Schedule> sList = scheduleRepo.findAll();
		if (sList != null) {
			// do not return schedules with ID 1-7
			for (Schedule s : sList) {
				if (Schedule.RESERVED_IDS.contains(s.getscheduleID())) {
					sList.remove(s);
				}
			}
		}
		return sList;
	}
	
	/**
	 * Update the schedule specified by {@code scheduleId} and saves it to schedule repository..
	 * Any arguments left null will not be updated.
	 * @param scheduleId must not be null
	 * @param aOpeningTime
	 * @param aClosingTime
	 * @param aDayOfWeek
	 * @param aLibrarian
	 * @return The updated schedule object.
	 */
	@Transactional
	public Schedule updateSchedule(Long scheduleId, Time aOpeningTime, Time aClosingTime, DayOfWeek aDayOfWeek, Long librarianId) {
		if (scheduleId == null) {
			throw new IllegalArgumentException("Schedule ID must not be null.");
		}
		Schedule s = scheduleRepo.findScheduleByScheduleID(scheduleId);
		if (s == null) {
			throw new IllegalStateException("Could not find a schedule with the specified id (id: " + scheduleId + ").");
		}
		// update attributes of schedule that are not null
		if (aOpeningTime != null) {
			// make sure new opening time is before closing time
			if (aOpeningTime.after(s.getClosingTime())) {
				throw new IllegalStateException("Could not update schedule with a new opening time (" + aOpeningTime + ") that is after current closing time (" + s.getClosingTime() + ").");
			}
			s.setOpeningTime(aOpeningTime);
		}
		if (aClosingTime != null) {
			// make sure new closing time is after opening time
			if (aClosingTime.before(s.getOpeningTime())) {
				throw new IllegalStateException("Could not update schedule with a new closing time (" + aClosingTime + ") that is after current opening time (" + s.getOpeningTime() + ").");
			}
			s.setClosingTime(aClosingTime);
		}
		if (aDayOfWeek !=  null) {
			s.setDayofWeek(aDayOfWeek);
		}
		if (librarianId != null) {
			// get librarian
			Librarian aLibrarian = lRepo.findLibrarianByEmployeeIDNumber(librarianId);
			// make sure librarian exists?
			if (aLibrarian == null) {
				throw new IllegalStateException("Could not update schedule with librarian that does not exist.");
			}
			s.setLibrarian(aLibrarian);
		}
		// save schedule and return it
		scheduleRepo.save(s);
		return s;
	}
	
	/**
	 * Delete the specified schedule from the schedule repository.
	 * @param scheduleToDelete Schedule to delete
	 * @return {@code true} if schedule was deleted from repository, {@code false} if {@code scheduleToDelete} was null.
	 */
	@Transactional
	public boolean deleteSchedule(Schedule scheduleToDelete) {
		if (scheduleToDelete == null) {
			return false;
		} else {
			scheduleRepo.delete(scheduleToDelete);
			return true;
		}
	}
	
	/**
	 * Delete the specified schedule from the schedule repositorie using schedule ID.
	 * @param scheduleId ID of schedule to delete
	 * @return {@code true} if schedule was deleted from repository, {@code false} if {@code scheduleId} was not found in repository.
	 */
	@Transactional
	public boolean deleteScheduleById(Long scheduleId) {
		if (scheduleId == null) {
			throw new IllegalArgumentException("libraryItemId parameter cannot be null.");
		}
		Schedule schedule = scheduleRepo.findScheduleByScheduleID(scheduleId);
		return deleteSchedule(schedule);
	}
	
	/**
	 * Takes strings as inputs and converts to Time objects before calling other version of this method.
	 * @param headLibrarianId
	 * @param sundayOpeningTime
	 * @param sundayClosingTime
	 * @param mondayOpeningTime
	 * @param mondayClosingTime
	 * @param tuesdayOpeningTime
	 * @param tuesdayClosingTime
	 * @param wednesdayOpeningTime
	 * @param wednesdayClosingTime
	 * @param thursdayOpeningTime
	 * @param thursdayClosingTime
	 * @param fridayOpeningTime
	 * @param fridayClosingTime
	 * @param saturdayOpeningTime
	 * @param saturdayClosingTime
	 * @return
	 */
	@Transactional
	public List<Schedule> createLibraryScheduleList(Long headLibrarianId, String sundayOpeningTime, String sundayClosingTime,
			String mondayOpeningTime, String mondayClosingTime, String tuesdayOpeningTime, String tuesdayClosingTime,
			String wednesdayOpeningTime, String wednesdayClosingTime, String thursdayOpeningTime, String thursdayClosingTime,
			String fridayOpeningTime, String fridayClosingTime, String saturdayOpeningTime, String saturdayClosingTime) {
		// check inputs not null
		if (sundayOpeningTime == null || sundayClosingTime == null || mondayOpeningTime == null || mondayClosingTime == null
				|| tuesdayOpeningTime == null || tuesdayClosingTime == null || wednesdayOpeningTime == null || wednesdayClosingTime == null
				|| thursdayOpeningTime == null || thursdayClosingTime == null || fridayOpeningTime == null || fridayClosingTime == null
				|| saturdayOpeningTime == null || saturdayClosingTime == null || headLibrarianId == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
		Time t1 = Time.valueOf(sundayOpeningTime);
		Time t2 = Time.valueOf(sundayClosingTime);
		Time t3 = Time.valueOf(mondayOpeningTime);
		Time t4 = Time.valueOf(mondayClosingTime);
		Time t5 = Time.valueOf(tuesdayOpeningTime);
		Time t6 = Time.valueOf(tuesdayClosingTime);
		Time t7 = Time.valueOf(wednesdayOpeningTime);
		Time t8 = Time.valueOf(wednesdayClosingTime);
		Time t9 = Time.valueOf(thursdayOpeningTime);
		Time t10 = Time.valueOf(thursdayClosingTime);
		Time t11 = Time.valueOf(fridayOpeningTime);
		Time t12 = Time.valueOf(fridayClosingTime);
		Time t13 = Time.valueOf(saturdayOpeningTime);
		Time t14 = Time.valueOf(saturdayClosingTime);
		return createLibraryScheduleList(headLibrarianId, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14);
	}
	
	/**
	 * Create library hours list.
	 * Takes 14 times or 7 scheduleDtos as input and returns a list of
	 * 7 schedule objects. Sets the schedules Ids to 1-7 to indicate that
	 * they are the library schedule.
	 * @param headLibrarianId
	 * @param sundayOpeningTime
	 * @param sundayClosingTime
	 * @param mondayOpeningTime
	 * @param mondayClosingTime
	 * @param tuesdayOpeningTime
	 * @param tuesdayClosingTime
	 * @param wednesdayOpeningTime
	 * @param wednesdayClosingTime
	 * @param thursdayOpeningTime
	 * @param thursdayClosingTime
	 * @param fridayOpeningTime
	 * @param fridayClosingTime
	 * @param saturdayOpeningTime
	 * @param saturdayClosingTime
	 * @return
	 */
	@Transactional
	public List<Schedule> createLibraryScheduleList(Long headLibrarianId, Time sundayOpeningTime, Time sundayClosingTime,
			Time mondayOpeningTime, Time mondayClosingTime, Time tuesdayOpeningTime, Time tuesdayClosingTime,
			Time wednesdayOpeningTime, Time wednesdayClosingTime, Time thursdayOpeningTime, Time thursdayClosingTime,
			Time fridayOpeningTime, Time fridayClosingTime, Time saturdayOpeningTime, Time saturdayClosingTime) {
		if (sundayOpeningTime == null || sundayClosingTime == null || mondayOpeningTime == null || mondayClosingTime == null
				|| tuesdayOpeningTime == null || tuesdayClosingTime == null || wednesdayOpeningTime == null || wednesdayClosingTime == null
				|| thursdayOpeningTime == null || thursdayClosingTime == null || fridayOpeningTime == null || fridayClosingTime == null
				|| saturdayOpeningTime == null || saturdayClosingTime == null) {
			throw new IllegalArgumentException("Time arguments must not be null.");
		}
		// check opening times before closing times
		if (!timeBefore(sundayOpeningTime, sundayClosingTime) || !timeBefore(mondayOpeningTime, mondayClosingTime) || !timeBefore(tuesdayOpeningTime, tuesdayClosingTime)
				|| !timeBefore(wednesdayOpeningTime, wednesdayClosingTime) || !timeBefore(thursdayOpeningTime, thursdayClosingTime)
				|| !timeBefore(fridayOpeningTime, fridayClosingTime) || !timeBefore(saturdayOpeningTime, saturdayClosingTime)) {
			throw new IllegalStateException("Opening times must be before closing times.");
		}
		if (headLibrarianId == null) {
			throw new IllegalArgumentException("Argument must not be null.");
		}
		HeadLibrarian hl = hlRepo.findHeadLibrarianByManagerIDNum(headLibrarianId);
		if (hl == null) {
			throw new IllegalArgumentException("Head librarian does not exist in repository.");
		}
		List<Schedule> sList = new ArrayList<Schedule>();
		DayOfWeek days[] = DayOfWeek.values();
		// Create a schedule for each day of the week
		for (int i = 1; i < 8; i++) {
			int dayIndex = (i - 2) % 7;
			// java modulo gives negative numbers so ensure indexs are all positive
			if (dayIndex < 0) {
				dayIndex+=7;
			}
			DayOfWeek day = days[dayIndex];
			Schedule s = new Schedule();
			// set IDs to reserved library hours schedule id (1-7)
			s.setscheduleID(Long.valueOf(i));
			// set day of week
			s.setDayofWeek(day);
			// set librarian to head librarian
			s.setLibrarian(hl);
			switch (day) {
			case SUNDAY:
				s.setOpeningTime(sundayOpeningTime);
				s.setClosingTime(sundayClosingTime);
				break;
			case MONDAY:
				s.setOpeningTime(mondayOpeningTime);
				s.setClosingTime(mondayClosingTime);
				break;
			case TUESDAY:
				s.setOpeningTime(tuesdayOpeningTime);
				s.setClosingTime(tuesdayClosingTime);
				break;
			case WEDNESDAY:
				s.setOpeningTime(wednesdayOpeningTime);
				s.setClosingTime(wednesdayClosingTime);
				break;
			case THURSDAY:
				s.setOpeningTime(thursdayOpeningTime);
				s.setClosingTime(thursdayClosingTime);
				break;
			case FRIDAY:
				s.setOpeningTime(fridayOpeningTime);
				s.setClosingTime(fridayClosingTime);
				break;
			case SATURDAY:
				s.setOpeningTime(saturdayOpeningTime);
				s.setClosingTime(saturdayClosingTime);
				break;
			}
			// Save schedule to repo
			scheduleRepo.save(s);
			// Add schedule to list
			sList.add(s);
		}
		return sList;
	}
	
	/**
	 * Create default schedule for the library.
	 * Default schedule is:
	 * 8:00 - 18:00 Monday to Friday,
	 * 10:00 - 14:00 Saturday to Sunday
	 * <br>
	 * Saves the schedules to the first 7 Ids of schedule repo.
	 * 1 = Sunday 7 = Saturday
	 * 
	 * @param headLibrarianId head librarian who will be associated with the schedule objects.
	 * @return list of schedules representing the library hours.
	 */
	@Transactional
	public List<Schedule> createDefaultLibraryScheduleList(Long headLibrarianId) {
		if (headLibrarianId == null) {
			throw new IllegalArgumentException("Argument must not be null.");
		}
		HeadLibrarian hl = hlRepo.findHeadLibrarianByManagerIDNum(headLibrarianId);
		if (hl == null) {
			throw new IllegalArgumentException("Head librarian does not exist in repository.");
		}
		List<Schedule> sList = new ArrayList<Schedule>();
		DayOfWeek days[] = DayOfWeek.values();
		// Create a schedule for each day of the week
		for (int i = 1; i < 8; i++) {
			int dayIndex = (i - 2) % 7;
			// java modulo gives negative numbers so ensure indexs are all positive
			if (dayIndex < 0) {
				dayIndex+=7;
			}
			DayOfWeek day = days[dayIndex];
			Schedule s = new Schedule();
			// set IDs to reserved library hours schedule id (1-7)
			s.setscheduleID(Long.valueOf(i));
			// set day of week
			s.setDayofWeek(day);
			// set librarian to head librarian
			s.setLibrarian(hl);
			if (day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {
				// weekend hours
				s.setOpeningTime(Library.DEFAULT_WEEKEND_OPENING_TIME);
				s.setClosingTime(Library.DEFAULT_WEEKEND_CLOSING_TIME);
			} else {
				// weekday hours
				s.setOpeningTime(Library.DEFAULT_WEEKDAY_OPENING_TIME);
				s.setClosingTime(Library.DEFAULT_WEEKDAY_CLOSING_TIME);
			}
			// Save schedule to repo
			scheduleRepo.save(s);
			// Add schedule to list
			sList.add(s);
		}
		return sList;
	}
	
	/**
	 * TODO unneeded: just use Date.before(Date)
	 * Determines if Time t1 is before Time t2.
	 * @return {@code true} if t1 is before t2, {@code false} otherwise.
	 */
	public static boolean timeBefore(Time t1, Time t2) {
		if (t1.compareTo(t2) < 0) {
			// t1 is before t2
			return true;
		} else {
			return false;
		}
	}
}
