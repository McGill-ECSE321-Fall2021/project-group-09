package ca.mcgill.ecse321.projectgroup09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dao.LibraryRepository;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
import ca.mcgill.ecse321.projectgroup09.service.LibraryService;

@CrossOrigin(origins = "*")
@RestController
public class LibraryController {
	
	@Autowired 
	private LibraryRepository libraryRepository; 
	

	

	@Transactional
	public Library updateLibraryHours(long headLibrarianID,  List<Schedule> schedules, String libraryName) {
		Library library = libraryRepository.findLibraryByLibraryName(libraryName);
		library.setSchedules(schedules);
		libraryRepository.save(library);
		return library;
		
	}

	@Transactional
	public Library updateLibraryEmail (Library library, String email) {
		library.setLibraryEmail(email);
		libraryRepository.save(library);
		return library;		
	}
	
	@Transactional
	public Library updateLibraryName (Library library, String name) {
		library.setLibraryEmail(name);
		libraryRepository.save(library);
		return library;		
	}
	
	@Transactional 
	public Library updateLibraryPhoneNo (Library library, String phoneNo) { 
		library.setLibraryPhone(phoneNo);
		libraryRepository.save(library);
		return library;
	}
	
	@Transactional 
	public List<Schedule> getLibraryHours(Library library) {
		List<Schedule> weeklyHours = library.getSchedules(); 
		return weeklyHours; 
	}
	
	@Transactional
	public void setLibraryHours(Library library, List<Schedule> schedules) {
		library.setSchedules(schedules);
		libraryRepository.save(library);
	}
	
	@Transactional 
	public Library deleteLibrary(String name) {
		Library library = libraryRepository.findLibraryByLibraryName(name);
		libraryRepository.delete(library);
		library = libraryRepository.findLibraryByLibraryName(name);
		return library;
		
	}
}
