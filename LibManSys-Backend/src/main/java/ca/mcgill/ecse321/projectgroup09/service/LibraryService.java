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
	
	@Transactional
	public Library createLibrary(String address, String phoneNo, String email, String libraryName, List<Schedule> schedules) {
		Library library = new Library();
		library.setLibraryEmail(email);
		library.setLibraryName(libraryName);
		library.setLibraryPhone(phoneNo);
		library.setLibraryAddress(address);
		
		
		//*********REPLACE WITH DEFAULT LIB HOURS****************
		// setup opening hours and closing hours 
		///get default schedule method 
		library.setSchedules(schedules);
		
		libraryRepository.save(library);
		return library;
	}
	

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
