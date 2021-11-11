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
