package ca.mcgill.ecse321.projectgroup09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.projectgroup09.dao.ScheduleRepository;

/**
 * Service class for schedule model.
 */
@Service
public class ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepo;
}
