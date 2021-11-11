package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.HeadLibrarianRepository;
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
	private HeadLibrarianRepository hlRepo;
	
	/**
	 * Create a new schedule object and save it to schedule repository.
	 * @param aOpeningTime
	 * @param aClosingTime
	 * @param aDayOfWeek
	 * @param aLibrarian
	 * @return
	 */
	@Transactional
	public Schedule createSchedule(Time aOpeningTime, Time aClosingTime, DayOfWeek aDayOfWeek, Librarian aLibrarian) {
		// make sure input parameters not null
		if (aOpeningTime == null || aClosingTime == null || aDayOfWeek == null || aLibrarian == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
		// make sure opening time before closing time
		
		Schedule s = new Schedule();
		//TODO
		return s;
	}
	
	/**
	 * 
	 * @param scheduleId
	 * @return
	 */
	@Transactional
	public Schedule getScheduleById(Long scheduleId) {
		return null;
	}
	
	/**
	 * 
	 * @param aDayOfWeek
	 * @return
	 */
	@Transactional
	public List<Schedule> getSchedulseByDay(DayOfWeek aDayOfWeek) {
		return null;
	}
	
	/**
	 * 
	 * @param librarian
	 * @return
	 */
	@Transactional
	public List<Schedule> getSchedulesByLibrarian(Librarian librarian) {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional
	public List<Schedule> getAllSchedules() {
		return null;
		
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
	public Schedule updateSchedule(Long scheduleId, Time aOpeningTime, Time aClosingTime, DayOfWeek aDayOfWeek, Librarian aLibrarian) {
		if (scheduleId == null) {
			throw new IllegalArgumentException("Schedule ID must not be null.");
		}
		Schedule s = scheduleRepo.findScheduleByScheduleID(scheduleId);
		if (s == null) {
			throw new IllegalStateException("Could not find a schedule with the specified if (id: " + scheduleId + ").");
		}
		// update attributes of schedule that are not null
		if (aOpeningTime != null) {
			s.setOpeningTime(aOpeningTime);
		}
		if (aClosingTime != null) {
			s.setClosingTime(aClosingTime);
		}
		if (aDayOfWeek !=  null) {
			s.setDayofWeek(aDayOfWeek);
		}
		if (aLibrarian != null) {
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
	 * Create library hours.
	 * Takes 14 times or 7 scheduleDtos as input and returns a list of
	 * 7 schedule objects. Sets the schedules Ids to 1-7 to indicate that
	 * they are the library schedule.
	 * @param sundayOpeningTime
	 * @param sundayClosingTime
	 * @return
	 */
	@Transactional
	public List<Schedule> createLibraryScheduleList(Time sundayOpeningTime, Time sundayClosingTime) {
		return null;
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
}
