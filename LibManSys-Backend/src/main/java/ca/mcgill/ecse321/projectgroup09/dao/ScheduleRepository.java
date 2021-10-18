package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
import ca.mcgill.ecse321.projectgroup09.models.Schedule.DayofWeek;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
	
	Schedule findScheduleByScheduleID(Long scheduleID);
	List<Schedule> findScheduleByLibrarian(Librarian librarian);
	List<Schedule> findSchedulesByDayofWeek(DayofWeek dayofWeek);
	//List<Schedule> findScheduleByLibrary(Library library);
}