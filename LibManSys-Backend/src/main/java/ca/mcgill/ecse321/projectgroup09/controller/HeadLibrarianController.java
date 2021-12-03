package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.service.HeadLibrarianService;

@CrossOrigin(origins = "*")
@RestController
public class HeadLibrarianController {
	
	private static final String BASE_URL = "/head-librarian";
	
	@Autowired
	private HeadLibrarianService headLibrarianService;

	@GetMapping(value = { BASE_URL + "",  BASE_URL + "/" })
	public ResponseEntity<?> getHeadLibrarian() {
		try  {
			HeadLibrarianDto hldto = HeadLibrarianDto.convertToDto(headLibrarianService.getHeadLibrarian());
			return httpSuccess(hldto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	@PostMapping(value = {BASE_URL + "/create", BASE_URL + "/create/"})
	public ResponseEntity<?> createHeadLibrarian(@RequestParam("fullName") String fullName,
			@RequestParam("email") String email, 
			@RequestParam("password") String password,
			@RequestParam("username") String username) {
		try {
			HeadLibrarianDto ldto = HeadLibrarianDto.convertToDto(headLibrarianService.createHeadLibrarian(fullName, email, password, username));
			return httpSuccess(ldto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	
	@PostMapping(value = { BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/"})
	public ResponseEntity<?> deleteHeadLibrarian(@PathVariable("id") Long managerIDNum) {
		try {
			HeadLibrarian headLibrarian = headLibrarianService.deleteHeadLibrarian(managerIDNum);
			return httpSuccess(HeadLibrarianDto.convertToDto(headLibrarian));
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}

}
