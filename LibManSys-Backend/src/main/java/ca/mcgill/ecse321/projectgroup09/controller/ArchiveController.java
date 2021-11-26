package ca.mcgill.ecse321.projectgroup09.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.ArchiveDto;
import ca.mcgill.ecse321.projectgroup09.models.Archive;
import ca.mcgill.ecse321.projectgroup09.service.ArchiveService;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ArchiveController {
	
	private static final String BASE_URL = "/archive";
	
	@Autowired
	private ArchiveService archiveService;
	

	@GetMapping(value = {BASE_URL, BASE_URL + "/"})
	public List<ArchiveDto> getAllBooks() {

		return archiveService.getAllArchives().stream().map(archive -> ArchiveDto.convertToDto(archive)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { BASE_URL + "/create/{title}/{publishedYear}/{institution}", BASE_URL + "/create/{title}/{publishedYear}/{institution}/"})
	
	public ArchiveDto createArchive(@PathVariable("title") String title, 
			@PathVariable("publishedYear") String publishedYear, 
			@PathVariable("institution") String institution) 
			throws IllegalArgumentException {
		int pubYear;
		try {
			pubYear = Integer.valueOf(publishedYear);
		} catch(Exception e) {
			throw new IllegalArgumentException("publishedYear must be valid integer. Error" + e.getMessage());
		}

		Archive archive = archiveService.createArchive(title, pubYear, institution);
		return ArchiveDto.convertToDto(archive);
	}
	
	
	
	@PostMapping(value = { BASE_URL + "/create2", BASE_URL + "/create2/"})
	public ArchiveDto createArchive2(@RequestBody Map<String, Object> jsonInput,
			HttpServletRequest request, HttpServletResponse response) {
	
		Archive archive = null;
		if (jsonInput.get("title") != null && jsonInput.get("publishedYear") != null && jsonInput.get("institution") != null) {
			try {
				String title = (String) jsonInput.get("title");
				int publishedYear = Integer.valueOf((String) jsonInput.get("publishedYear"));
				String institution = (String) jsonInput.get("institution");
				archive = archiveService.createArchive(title, publishedYear, institution);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		} else {
			throw new IllegalArgumentException("Provide following parameters to create a new book: title (String), publishedYear (int), institution (String).");
		}
		return ArchiveDto.convertToDto(archive);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
