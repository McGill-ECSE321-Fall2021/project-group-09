package ca.mcgill.ecse321.projectgroup09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.HeadLibrarianDto;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.service.HeadLibrarianService;

@CrossOrigin(origins = "*")
@RestController
public class HeadLibrarianController {
	
	@Autowired
	private HeadLibrarianService headLibrarianService;

	@PostMapping(value = {"/headLibrarian/create/{managerIDNum}", "/library/create/{managerIDNum}/"})
	public HeadLibrarianDto createHeadLibrarian(@PathVariable("managerIDNum") Long managerIDNum) {
		
		HeadLibrarian headLibrarian = headLibrarianService.createHeadLibrarian(managerIDNum);
		return HeadLibrarianDto.convertToDto(headLibrarian);
	}
	
	@DeleteMapping(value = {"/headLibrarian/delete", "/library/create/delete/"})
	public HeadLibrarianDto deleteHeadLibrarian(Long managerIDNum) {
		
		HeadLibrarian headLibrarian = headLibrarianService.deleteHeadLibrarian(managerIDNum);
		return HeadLibrarianDto.convertToDto(headLibrarian);
	}

}
