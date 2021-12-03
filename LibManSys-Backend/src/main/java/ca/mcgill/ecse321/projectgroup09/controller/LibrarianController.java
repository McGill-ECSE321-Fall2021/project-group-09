package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailure;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.LibrarianDto;
import ca.mcgill.ecse321.projectgroup09.service.LibrarianService;

@CrossOrigin(origins = "*")
@RestController
public class LibrarianController {
	
	private static final String BASE_URL = "/librarian";
	
	@Autowired
	private LibrarianService librarianService;
	
	@GetMapping(value = {BASE_URL + "", BASE_URL + "/"})
	public ResponseEntity<?> getAllLibrarians() {
		try {
			List<LibrarianDto> ls = LibrarianDto.convertToDtos(librarianService.getAll());
			return httpSuccess(ls);
		} catch (Exception e) {
			if (e.getMessage() == null) {
				return httpFailure(e.getStackTrace());
			}
			return httpFailureMessage(e.getMessage());
		}
	}
	
	/**
	 * Create a new librarian with no assigned schedules, loans or bookings.
	 * @param fullName
	 * @param email
	 * @param password
	 * @param username
	 * @return DTO of newly created librarian.
	 */
	@PostMapping(value = {BASE_URL + "/create", BASE_URL + "/create/"})
	public ResponseEntity<?> createLibrarian(@RequestParam("fullName") String fullName,
			@RequestParam("email") String email, 
			@RequestParam("password") String password,
			@RequestParam("username") String username) {
		try {
			LibrarianDto ldto = LibrarianDto.convertToDto(librarianService.createLibrarian(fullName, email, password, username));
			return httpSuccess(ldto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param employeeIDNUmber
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/{id}", BASE_URL + "/{id}/"})
	public ResponseEntity<?> getLibrarianByEmployeeIDNumber(@PathVariable("id") Long employeeIDNumber) {
		try {
			LibrarianDto ldto = LibrarianDto.convertToDto(librarianService.getLibrarianByEmployeeIDNumber(employeeIDNumber));
			return httpSuccess(ldto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	
	/**
	 * Login a librarian. Given matching username and password, return
	 * librarian associated with it.
	 */
	@PostMapping(value = {BASE_URL + "/login", BASE_URL + "/login/"})
	public ResponseEntity<?> loginLibrarian(@RequestParam("username") String username, 
			@RequestParam("password") String password) {
		try {
			LibrarianDto ldto = LibrarianDto.convertToDto(librarianService.loginLibrarian(username, password));
			return httpSuccess(ldto);
		} catch (Exception e) {
			return httpFailure("Error: " + e.getMessage());
		}
	}
	
	@PostMapping(value = { BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/"})
	public ResponseEntity<?> deletelibrarian(@PathVariable("id") Long employeeIDNum) {
		try {
			LibrarianDto l = LibrarianDto.convertToDto(librarianService.deleteLibrarian(employeeIDNum));
			return httpSuccess(l);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
}
