package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.ScheduleRepository;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

/**
 * Service class for schedule model.
 */
@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepo;
	
	
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
