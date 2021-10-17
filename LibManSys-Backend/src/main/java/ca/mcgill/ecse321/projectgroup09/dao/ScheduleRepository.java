package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
	
	Schedule findScheduleByScheduleID(Long scheduleID);
}