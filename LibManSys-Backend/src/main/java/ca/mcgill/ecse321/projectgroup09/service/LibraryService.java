package ca.mcgill.ecse321.projectgroup09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import ca.mcgill.ecse321.projectgroup09.dao.*;
import ca.mcgill.ecse321.projectgroup09.models.*;

@Service
public class LibraryService {

	@Autowired 
	private LibraryRepository libraryRepository; 
	
	@Autowired ScheduleRepository scheduleRepository; 
	
	@Transactional
	public Library createLibrary(String address, String phoneNo, String email, String libraryName) {
		Library library = new Library();
		library.setLibraryEmail(email);
		library.setLibraryName(libraryName);
		library.setLibraryPhone(phoneNo);
		library.setLibraryAddress(address);
		
		libraryRepository.save(library);
		return library;
	}
	
	@Transactional
	public Library setLibraryHours(long headLibrarianID,  List<Schedule> schedules, String libraryName) {
		Library library = libraryRepository.findLibraryByLibraryName(libraryName);
		library.setSchedules(schedules);
		libraryRepository.save(library);
		return library;
		
	}

	@Transactional
	public Library updateLibraryEmail (String name, String email) {
		Library library = libraryRepository.findLibraryByLibraryName(name);
		
		if (email == null) {
			throw new IllegalArgumentException("Please enter a new email.");
		}
		library.setLibraryEmail(email);
		libraryRepository.save(library);
		return library;		
	}
	
	
	@Transactional 
	public Library updateLibraryPhoneNo (String name, String phoneNo) { 
		Library library = libraryRepository.findLibraryByLibraryName(name);
		
		if (phoneNo == null) {
			throw new IllegalArgumentException("Please enter a new phone number.");
		}
		
		library.setLibraryPhone(phoneNo);
		libraryRepository.save(library);
		return library;
	}
	
	@Transactional 
	public List<Schedule> getLibraryHours(String name) {
		Library library = libraryRepository.findLibraryByLibraryName(name);
		List<Schedule> weeklyHours = library.getSchedules(); 
		return weeklyHours; 
	}
	
	@Transactional 
	public List<Schedule> getLibrarySchedules() {
		
		List<Schedule> weeklySchedule = new ArrayList<Schedule>();
		
		for (long i =0; i < 7; i++) {
			weeklySchedule.add(scheduleRepository.findScheduleByScheduleID(i+1));
		}
		
		return weeklySchedule; 
	}
	
//	@Transactional
//	public void setLibraryHours(String name, List<Schedule> schedules) {
//		Library library = libraryRepository.findLibraryByLibraryName(name);
//		library.setSchedules(schedules);
//		libraryRepository.save(library);
//	}
	
	@Transactional 
	public Library deleteLibrary(String name) {
		Library library = libraryRepository.findLibraryByLibraryName(name);
		libraryRepository.delete(library);
		library = libraryRepository.findLibraryByLibraryName(name);
		return library;
		
	}
	
	
}
