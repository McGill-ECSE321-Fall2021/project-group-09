package ca.mcgill.ecse321.projectgroup09.controller;

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

import ca.mcgill.ecse321.projectgroup09.dto.ArchiveDto;
import ca.mcgill.ecse321.projectgroup09.dto.LibraryItemDto;
import ca.mcgill.ecse321.projectgroup09.models.Archive;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.service.ArchiveService;

/**
 * CRUD for archives.
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class ArchiveController {
	
	private static final String BASE_URL = "/archive";
	
	@Autowired
	private ArchiveService archiveService;
	
	/**
	 * Get all archives.
	 * @return
	 */
	@GetMapping(value = {BASE_URL, BASE_URL + "/"})
	public ResponseEntity<?> getAllArchives() {
		List<LibraryItemDto> archives = LibraryItemDto.convertToDto(archiveService.getAllArchives());
		return httpSuccess(archives);
	}

	@GetMapping(value = {BASE_URL + "/by-title/{title}", BASE_URL + "/by-title/{title}/"})
	public ResponseEntity<?> getArchivesByTitle(@PathVariable String title) {
		try {
			List<LibraryItemDto> archives = LibraryItemDto.convertToDto(archiveService.getArchivesByTitle(title));
			return httpSuccess(archives);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		
	}
	
	@GetMapping(value = {BASE_URL + "/by-published-year/{pubYear}", BASE_URL + "/by-published-year/{pubYear}/"})
	public ResponseEntity<?> getArchivesByPubYear(@PathVariable int pubYear) {
		try {
			List<LibraryItemDto> as = LibraryItemDto.convertToDto(archiveService.getArchivesByPublishedYear(pubYear));
			return httpSuccess(as);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		
	}
	
	@GetMapping(value = {BASE_URL + "/by-institution/{institution}", BASE_URL + "/by-institution/{institution}/"})
	public ResponseEntity<?> getArchivesByInstitution(@PathVariable String institution) {
		try {
			List<LibraryItemDto> archives = LibraryItemDto.convertToDto(archiveService.getArchivesByInstitution(institution));
			return httpSuccess(archives);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	/**
	 * Create new archive.	
	 * @param title
	 * @param publishedYear
	 * @param institution
	 * @return
	 */
	@PostMapping(value = { BASE_URL + "/create", BASE_URL + "/create/"})
	public ResponseEntity<?> createArchive(@RequestParam("title") String title, 
			@RequestParam("publishedYear") String publishedYear,
			@RequestParam("institution") String institution) {
		
		int pubYear;
		try {
			pubYear = Integer.valueOf(publishedYear);
		} catch(Exception e) {
			return httpFailureMessage("publishedYear must be valid integer. Error: " + e.getMessage());
		}
		Archive archive;
		try {
			archive = archiveService.createArchive(title, pubYear, institution);
		} catch(Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		return httpSuccess(ArchiveDto.convertToDto(archive));
	}
	
	/**
	 * Get an archive by ID.
	 * @param archiveId
	 * @return
	 */
	@GetMapping(value = { BASE_URL + "/{id}", BASE_URL + "/{id}/" })
	public ResponseEntity<?> getArchiveById(@PathVariable("id") Long archiveId) {
		try {
			ArchiveDto adto = ArchiveDto.convertToDto(archiveService.getArchiveById(archiveId));
			return httpSuccess(adto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	/**
	 * Update archive. Not all fields (except archive ID) need to be filled.
	 * @param archiveId
	 * @param title
	 * @param publisherYear
	 * @param loanablePeriod
	 * @param dailyOverdueFee
	 * @param itemStatus
	 * @param institution
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/update/{id}", BASE_URL + "/update/{id}/"})
	public ResponseEntity<?> updateArchive(@PathVariable("id") Long archiveId, 
			@RequestParam(value = "title", required = false) String title, 
			@RequestParam(value = "publisherYear", required = false) Integer publisherYear,
			@RequestParam(value = "loanablePeriod", required = false) Integer loanablePeriod, 
			@RequestParam(value = "dailyOverdueFee", required = false) Double dailyOverdueFee, 
			@RequestParam(value = "itemStatus", required = false) String itemStatus,
			@RequestParam(value = "institution", required = false) String institution) {
		try {
			ItemStatus iS = ItemStatus.valueOf(itemStatus);
			ArchiveDto bookDto = ArchiveDto.convertToDto(archiveService.updateArchive(archiveId, title, publisherYear, loanablePeriod, dailyOverdueFee, iS, institution));
			return httpSuccess(bookDto);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Delete archive from repository.
	 * @param archiveId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/"})
	public ResponseEntity<?> deleteArchive(@PathVariable("id") Long archiveId) {
		if (archiveId == null) {
			return httpFailureMessage("Error: ID cannot be null.");
		}
		boolean deleted = false;
		try {
			deleted = archiveService.deleteArchiveById(archiveId);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
		if (deleted) {
			return httpSuccess("Deleted book " + archiveId + " from repository succesfully.");
		}
		// else
		return httpFailureMessage("Failed to delete book: Book with id " + archiveId + " does not exist.");
	}
	
}
