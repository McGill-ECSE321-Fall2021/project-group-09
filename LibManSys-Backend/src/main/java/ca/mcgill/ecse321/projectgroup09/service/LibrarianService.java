package ca.mcgill.ecse321.projectgroup09.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

@Service
public class LibrarianService {
	
	@Autowired
	private LibrarianRepository librarianRepository;
	
	@Transactional
	public Librarian createLibrarian(String librarianEmail, String librarianPassword, String librarianUsername, Long employeeIDNum) {
		
		if (employeeIDNum == null) {
			throw new IllegalArgumentException("Please enter an ID.");
		}
		
		Librarian librarian = new Librarian();
		//librarian.setAccountId(employeeIDNum);
		librarian.setemployeeIDNum(employeeIDNum);
		librarian.setLibrarianEmail(librarianEmail);
		librarian.setLibrarianPassword(librarianPassword);
		librarian.setFullName(librarianUsername);
		return librarian;
	}
	
	public void setLibrarianHours(Long employeeIDNum, List<Schedule> schedules) {
		Librarian l1 = librarianRepository.findLibrarianByEmployeeIDNum(employeeIDNum);
		l1.setSchedules(schedules);
	}
	
	public List<Schedule> getLibrarianHours(Long employeeIDNum) {
		return librarianRepository
				.findLibrarianByEmployeeIDNum(employeeIDNum)
				.getSchedules();
	}
	
	public Librarian deleteLibrarian(Long employeeIDNum) {
		Librarian librarian = librarianRepository.findLibrarianByEmployeeIDNum(employeeIDNum);
		librarianRepository.delete(librarian);
		librarian = librarianRepository.findLibrarianByEmployeeIDNum(employeeIDNum);
		return librarian;
	}
	

}
