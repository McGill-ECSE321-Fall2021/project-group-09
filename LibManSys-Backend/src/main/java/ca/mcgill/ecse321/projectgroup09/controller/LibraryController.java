package ca.mcgill.ecse321.projectgroup09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.service.LibraryService;

@CrossOrigin(origins = "*")
@RestController
public class LibraryController {
	
	private static final String BASE_URL = "/library";
	
	@Autowired
	private LibraryService libraryService;
	
	// Hello world
	@GetMapping(value = {"", "/"})
	public String homeHelloWorld() {
		return "Hello World!";
	}
}
