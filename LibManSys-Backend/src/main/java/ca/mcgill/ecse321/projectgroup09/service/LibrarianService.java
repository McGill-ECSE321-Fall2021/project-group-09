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
	
	/**
	 * Creates a new librarian and saves it to the librarian repository.
	 * Creates librarian with no (schedule, loan or booking) associations.
	 * @param fullName
	 * @param email
	 * @param password
	 * @param username
	 * @return newly created Librarian object.
	 */
	@Transactional
	public Librarian createLibrarian(String fullName, String email, String password, String username) {
		if (fullName == null || email == null || password == null || username == null
				|| fullName.isBlank() || email.isBlank() || password.isBlank() || username.isBlank()) {
			throw new IllegalArgumentException("Arguments cannot be null when creating a new librarian.");
		}
		Librarian l = librarianRepository.findLibrarianByLibrarianUsername(username);
		if (l != null) {
			throw new IllegalStateException("Username is taken.");
		}
		
		// create new librarian with input parameters and empty associations
		Librarian librarian = new Librarian(fullName, email, password, username, null, null, null);
		
		// save new librarian and return it
		librarianRepository.save(librarian);
		return librarian;
	}
	
	@Transactional
	public Librarian getLibrarianByEmployeeIDNumber(Long employeeIDNumber) {
		if (employeeIDNumber == null) {
			throw new IllegalArgumentException("Argument cannot be null.");
		}
		Librarian l = librarianRepository.findLibrarianByEmployeeIDNumber(employeeIDNumber);
		if (l == null) {
			throw new IllegalStateException("Librarian with employee ID number: " + employeeIDNumber + " does not exist.");
		}
		return l;
	}
	
	@Transactional
	public List<Librarian> getAll() {
		return librarianRepository.findAll();
	}
	
	@Transactional
	public Librarian setLibrarianHours(Long employeeIDNum, List<Schedule> schedules) {
		if (schedules == null || schedules.isEmpty()) {
			throw new IllegalArgumentException("'schedules' input parameter cannot be null or empty list.");
		}
		Librarian l = librarianRepository.findLibrarianByEmployeeIDNumber(employeeIDNum);
		if (l == null) {
			throw new IllegalArgumentException("Could not find librarian with employee id " + employeeIDNum + " in repository.");
		}
		l.setSchedules(schedules);
		librarianRepository.save(l);
		return l;
	}
	
	@Transactional
	public List<Schedule> getLibrarianHours(Long employeeIDNum) {
		if (employeeIDNum == null) {
			throw new IllegalArgumentException("'employeeIDNum' input parameter cannot be null");
		}
		Librarian l = librarianRepository.findLibrarianByEmployeeIDNumber(employeeIDNum);
		if (l == null) {
			throw new IllegalArgumentException("Could not find librarian with employee id " + employeeIDNum + " in repository.");
		}
		return l.getSchedules();
	}
	
	/**
	 * Return deleted librarian if successful.
	 * @param employeeIDNum
	 * @return
	 */
	@Transactional
	public Librarian deleteLibrarian(Long employeeIDNum) {
		if (employeeIDNum == null) {
			throw new IllegalArgumentException("'employeeIDNum' input parameter cannot be null");
		}
		Librarian librarian = librarianRepository.findLibrarianByEmployeeIDNumber(employeeIDNum);
		if (librarian == null) {
			throw new IllegalArgumentException("Could not find librarian with employee id " + employeeIDNum + " in repository.");
		}
		librarianRepository.delete(librarian);
		//??? librarian = librarianRepository.findLibrarianByEmployeeIDNum(employeeIDNum);
		return librarian;
	}
	
	/**
	 * Return librarian matching username and password.
	 * @param username
	 * @param password
	 * @return
	 */
	@Transactional
	public Librarian loginLibrarian(String username, String password) {
		if (username == null || username.isBlank()) {
			throw new IllegalArgumentException("Please provide a username.");
		}
		if (password == null || password.isBlank()) {
			throw new IllegalArgumentException("Please provide a password.");
		}
		Librarian l = librarianRepository.findLibrarianByLibrarianUsername(username);
		if (l == null) {
			throw new IllegalStateException("Could not find librarian with that username.");
		}
		if (!l.getLibrarianPassword().equals(password)) {
			throw new IllegalStateException("Password does not match username.");
		}
		return l;
	}
}
