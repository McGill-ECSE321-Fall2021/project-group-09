package ca.mcgill.ecse321.projectgroup09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.LibraryDto;
import ca.mcgill.ecse321.projectgroup09.dto.ScheduleDto;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
import ca.mcgill.ecse321.projectgroup09.service.LibraryService;

@CrossOrigin(origins = "*")
@RestController
public class LibraryController {

	@Autowired 
	private LibraryService libraryService; 
	

	@PostMapping(value = {"/library/create", "/library/create/"})
	public LibraryDto createLibrary(@PathVariable("address") String address, @PathVariable("phoneNo") String phoneNo, @PathVariable("email") String email,
			@PathVariable("libraryName") String libraryName, @PathVariable("schedules") List<Schedule> schedules) {
		
		Library library = libraryService.createLibrary(address, phoneNo, email, libraryName, schedules);
		return LibraryDto.convertToDto(library);
	}

	@PostMapping(value = {"/library/update/hours", "/library/update/hours/"})
	public LibraryDto updateLibraryHours(@PathVariable("headLibrarianID") long headLibrarianID, @PathVariable("schedules") List<Schedule> schedules, 
			@PathVariable("libraryName") String libraryName) {
		
		Library library = libraryService.updateLibraryHours(headLibrarianID, schedules, libraryName);
		return LibraryDto.convertToDto(library);

	}

	@PostMapping(value = {"/library/update/email", "/library/update/email/"})
	public LibraryDto updateLibraryEmail (@PathVariable("library") Library library, @PathVariable("email") String email) {
		libraryService.updateLibraryEmail(library, email);
		return LibraryDto.convertToDto(library);	
	}

	@PostMapping(value = {"/library/update/name", "/library/update/name/"})
	public LibraryDto updateLibraryName (Library library, String name) {
		libraryService.updateLibraryName(library, name);
		return LibraryDto.convertToDto(library);	
	}

	@PostMapping(value = {"/library/update/phoneNo", "/library/update/phoneNo/"})
	public LibraryDto updateLibraryPhoneNo (@PathVariable("library") Library library, @PathVariable("phoneNo") String phoneNo) { 
		libraryService.updateLibraryPhoneNo(library, phoneNo);
		return LibraryDto.convertToDto(library);
	}

	@GetMapping(value = {"/library/hours", "/library/hours/"})
	public List<ScheduleDto> getLibraryHours(@PathVariable("library") Library library) {
		return ScheduleDto.convertToDto(libraryService.getLibraryHours(library));
	}

//	@PostMapping(value = {"/library/create", "/library/create/"})
//	public void setLibraryHours(@PathVariable("library") Library library, @PathVariable("schedules") List<Schedule> schedules) {
//		library.setSchedules(schedules);
//	}

	@DeleteMapping(value = {"/library/delete", "/library/delete"})
	public LibraryDto deleteLibrary(@PathVariable("name") String name) {
		Library library = libraryService.deleteLibrary(name);
	
		return LibraryDto.convertToDto(library);

	}
}
