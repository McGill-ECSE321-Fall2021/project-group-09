package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
import ca.mcgill.ecse321.projectgroup09.models.Schedule.DayOfWeek;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
	
	Schedule findScheduleByScheduleID(Integer scheduleID);
	List<Schedule> findScheduleByLibrarian(Librarian librarian);
	List<Schedule> findSchedulesByDayOfWeek(DayOfWeek dayOfWeek);
	List<Schedule> findScheduleByLibrary(Library library);
}