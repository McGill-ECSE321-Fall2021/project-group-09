package ca.mcgill.ecse321.projectgroup09.controller;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.ScheduleDto;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
import ca.mcgill.ecse321.projectgroup09.service.ScheduleService;

@CrossOrigin(origins = "*")
@RestController
public class ScheduleController {

	private static final String BASE_URL = "/schedule";
	
	@Autowired
	private ScheduleService scheduleService;
	
	/**
	 * Create a schedule for a librarian
	 * @param openingTimeString Formatted as "hh:mm:ss"
	 * @param closingTimeString Formatted as "hh:mm:ss"
	 * @param dayOfWeekString All caps day of week string.
	 * @param librarianId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/create", BASE_URL + "/create/"})
	public ResponseEntity<?> createSchedule(@RequestParam("openingTime") String openingTimeString, @RequestParam("closingTime") String closingTimeString,
			@RequestParam("dayOfWeek") String dayOfWeekString, @RequestParam("librarianId") Long librarianId) {
		// parse times and day
		Time openingTime = null;
		Time closingTime = null;
		DayOfWeek dayOfWeek = null;
		try {
			openingTime = Time.valueOf(openingTimeString);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: failed to parse openingTime as valid Time: " + e.getMessage());
		}
		try {
			closingTime = Time.valueOf(closingTimeString);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: failed to parse closingTime as valid Time: " + e.getMessage());
		}
		try {
			dayOfWeek = DayOfWeek.valueOf(dayOfWeekString);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: failed to parse dayOfWeek as valid DayOfWeek (must be all caps): " + e.getMessage());
		}
		ScheduleDto sdto = null;
		try {
			Schedule s = scheduleService.createScheduleForLibrarian(openingTime, closingTime, dayOfWeek, librarianId);
			sdto = ScheduleDto.convertToDto(s);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(sdto);
	}
	
	/**
	 * 
	 * @param scheduleId
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/get-by-id/{id}", BASE_URL + "/get-by-id/{id}/"})
	public ResponseEntity<?> getScheduleById(@PathVariable("id") Long scheduleId) {
		ScheduleDto sdto = null;
		try {
			Schedule s = scheduleService.getScheduleById(scheduleId);
			sdto = ScheduleDto.convertToDto(s);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(sdto);
	}
	
	/**
	 * Return all employee schedules for specified day. (not including library hour schedules)
	 * @param day
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/get-by-day/{day}", BASE_URL + "/get-by-day/{day}/"})
	public ResponseEntity<?> getSchedulesByDay(@PathVariable("day") String day) {
		// parse day
		DayOfWeek dayOfWeek = null;
		try {
			dayOfWeek = DayOfWeek.valueOf(day);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		// get schedudles
		List<ScheduleDto> sdtos = null;
		try {
			List<Schedule> s = scheduleService.getSchedulesByDay(dayOfWeek);
			sdtos = ScheduleDto.convertToDtos(s);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(sdtos);
	}
	
	/**
	 * Return list of schedules corresponding to librarian with specified employee id.
	 * @param librarianId
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/get-by-librarian/{librarianId}", BASE_URL + "/get-by-librarian/{librarianId}/"})
	public ResponseEntity<?> getSchedulesByLibrarian(@PathVariable("librarianId") Long librarianId) {
		List<ScheduleDto> sdtos = null;
		try {
			List<Schedule> s = scheduleService.getSchedulesByLibrarian(librarianId);
			sdtos = ScheduleDto.convertToDtos(s);
			return ResponseEntity.status(HttpStatus.OK).body(sdtos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Get all schedules. Does not include special library hour schedules.
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "", BASE_URL + "/", BASE_URL + "/get-all", BASE_URL + "/get-all/"})
	public ResponseEntity<?> getAllSchedules() {
		try {
			List<ScheduleDto> s = ScheduleDto.convertToDtos(scheduleService.getAllSchedules());
			return ResponseEntity.status(HttpStatus.OK).body(s);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Update a schedule. If any parameters are not provided, they will remain unchanged.
	 * @param scheduleId
	 * @param openingTimeString
	 * @param closingTimeString
	 * @param dayOfWeekString
	 * @param librarianId
	 * @return Updated schedule information if schedule updated successfully, otherwise returns error message.
	 */
	@PostMapping(value = {BASE_URL + "/update/{id}", BASE_URL + "/upate/{id}/"})
	public ResponseEntity<?> updateSchedule(@PathVariable("id") Long scheduleId, @RequestParam(value = "openingTime", required = false) String openingTimeString,
			@RequestParam(value = "closingTime", required = false) String closingTimeString, @RequestParam(value = "dayOfWeek", required = false) String dayOfWeekString,
			@RequestParam(value = "librarianId", required = false) Long librarianId) {
		// parse times and dayofweek input
		Time openingTime = null;
		Time closingTime = null;
		DayOfWeek dayOfWeek = null;
		try {
			openingTime = Time.valueOf(openingTimeString);
			closingTime = Time.valueOf(closingTimeString);
			dayOfWeek = DayOfWeek.valueOf(dayOfWeekString);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: failed to parse inputs as valid Time or DayOfWeek.");
		}
		// update
		try {
			Schedule s = scheduleService.updateSchedule(scheduleId, openingTime, closingTime, dayOfWeek, librarianId);
			return ResponseEntity.status(HttpStatus.OK).body(s);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}
			
	/**
	 * Delete schedule by id.
	 * @param scheduleId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/"})
	public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long scheduleId) {
		return null;
	}
	
	/**
	 * Do we need this as a controller method?
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/create-library-hours", BASE_URL + "/create-library-hours/"})
	public ResponseEntity<?> createLibraryHours() {
		return null;
	}

	/**
	 * Do we need this as a controller method?
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/create-default-library-hours", BASE_URL + "/create-default-library-hours/"})
	public ResponseEntity<?> createDefaultLibraryHours() {
		return null;
	}
}