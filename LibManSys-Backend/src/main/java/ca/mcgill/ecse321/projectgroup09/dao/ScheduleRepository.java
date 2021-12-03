package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;
import java.util.List;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
	
	Schedule findScheduleByScheduleID(Long scheduleID);
	List<Schedule> findScheduleByLibrarian(Librarian librarian);
	List<Schedule> findSchedulesByDayofWeek(DayOfWeek dayOfWeek);
	//List<Schedule> findScheduleByLibrary(Library library);
	List<Schedule> findAll();
}