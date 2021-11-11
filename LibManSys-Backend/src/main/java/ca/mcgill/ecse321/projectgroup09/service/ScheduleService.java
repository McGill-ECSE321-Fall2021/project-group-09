package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.ScheduleRepository;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

/**
 * Service class for schedule model.
 */
@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepo;
	
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
	 * Takes 14 times or 7 scheduleDtos as input and returns a list of
	 * 7 schedule objects. Sets the schedules Ids to 1-7 to indicate that
	 * they are the library schedule.
	 * @param sundayOpeningTime
	 * @param sundayClosingTime
	 * @return
	 */
	@Transactional
	public List<Schedule> createLibrarySchedule(Time sundayOpeningTime, Time sundayClosingTime) {
		return null;
	}
}
