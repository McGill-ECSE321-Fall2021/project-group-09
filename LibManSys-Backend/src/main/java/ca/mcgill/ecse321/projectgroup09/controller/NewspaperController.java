package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.LibraryItemDto;
import ca.mcgill.ecse321.projectgroup09.dto.NewspaperDto;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Newspaper;
import ca.mcgill.ecse321.projectgroup09.service.NewspaperService;

/**
 * CRUD for Newspapers.
 */
@CrossOrigin(origins = "*")
@RestController
public class NewspaperController {
	
	private static final String BASE_URL = "/newspaper";

	@Autowired
	private NewspaperService newspaperService;
	
	/**
	 * Get all newspapers.
	 * @return
	 */
	@GetMapping(value = {BASE_URL, BASE_URL + "/"})
	public ResponseEntity<?> getAllNewspapers() {
		List<LibraryItemDto> newspapers = LibraryItemDto.convertToDto(newspaperService.getAllNewspapers());
		return httpSuccess(newspapers);
	}
	
	/**
	 * Create new music album.
	 * @param title
	 * @param publishedYear
	 * @param journalName
	 * @param artist
	 * @param edition
	 * @param chiefEditorInMinutes
	 * @return
	 */
	@PostMapping(value = { BASE_URL + "/create", BASE_URL + "/create/"})
	public ResponseEntity<?> createNewspaper(@RequestParam("title") String title, 
			@RequestParam("publishedYear") Integer publishedYear,
			@RequestParam("journalName") String journalName,
			@RequestParam("edition") String edition,
			@RequestParam("chiefEditor") String chiefEditor) {
		Newspaper newspaper;
		try {
			newspaper = newspaperService.createNewspaper(title, publishedYear,journalName,edition,chiefEditor);
		} catch(Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		return httpSuccess(NewspaperDto.convertToDto(newspaper));
	}
	
	/**
	 * Get an newspaper by ID.
	 * @param newspaperId
	 * @return
	 */
	@GetMapping(value = { BASE_URL + "/{id}", BASE_URL + "/{id}/" })
	public ResponseEntity<?> getNewspaperById(@PathVariable("id") Long newspaperId) {
		try {
			NewspaperDto adto = NewspaperDto.convertToDto(newspaperService.getNewspaperById(newspaperId));
			return httpSuccess(adto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	/**
	 * Update a newspaper. Fields for which no parameters
	 * are passed will retain there current value.
	 * @param newspaperId
	 * @param title
	 * @param publisherYear
	 * @param loanablePeriod
	 * @param dailyOverdueFee
	 * @param itemStatus
	 * @param journalName
	 * @param edition
	 * @param chiefEditor
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/update/{id}", BASE_URL + "/update/{id}/"})
	public ResponseEntity<?> updateNewspaper(@PathVariable("id") Long newspaperId, 
			@RequestParam(value = "title", required = false) String title, 
			@RequestParam(value = "publisherYear", required = false) Integer publisherYear,
			@RequestParam(value = "loanablePeriod", required = false) Integer loanablePeriod, 
			@RequestParam(value = "dailyOverdueFee", required = false) Double dailyOverdueFee, 
			@RequestParam(value = "itemStatus", required = false) String itemStatus,
			@RequestParam(value = "journalName", required = false) String journalName,
			@RequestParam(value = "edition", required = false) String edition,
			@RequestParam(value = "chiefEditor", required = false) String chiefEditor) {
		try {
			ItemStatus iS = ItemStatus.valueOf(itemStatus);
			NewspaperDto bookDto = NewspaperDto.convertToDto(newspaperService.updateNewspaper(newspaperId, title, publisherYear, loanablePeriod, dailyOverdueFee, iS, journalName, edition, chiefEditor));
			return httpSuccess(bookDto);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Delete newspaper from repository.
	 * @param newspaperId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/"})
	public ResponseEntity<?> deleteNewspaper(@PathVariable("id") Long newspaperId) {
		if (newspaperId == null) {
			return httpFailureMessage("Error: ID cannot be null.");
		}
		boolean deleted = false;
		try {
			deleted = newspaperService.deleteNewspaperById(newspaperId);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
		if (deleted) {
			return httpSuccess("Deleted newspaper " + newspaperId + " from repository succesfully.");
		}
		// else
		return httpFailureMessage("Failed to delete newspaper with id " + newspaperId + " because it does not exist.");
	}
	
		// getters
		@GetMapping(value = {BASE_URL + "/by-title/{title}", BASE_URL + "/by-title/{title}/" })
		public ResponseEntity<?> getNewspapersbyTitle(@PathVariable("title") String title)  {
			List <NewspaperDto> newspaperDto = new ArrayList<NewspaperDto>();
			for(Newspaper m : newspaperService.getNewspapersByTitle(title)) {
				newspaperDto.add(NewspaperDto.convertToDto(m));
			}
			return httpSuccess(newspaperDto);
		}
		
		@GetMapping(value = {BASE_URL + "/by-published-year/{year}", BASE_URL + "/by-published-year/{year}/" })
		public ResponseEntity<?> getNewspapersbyPublishedYear(@PathVariable("year") Integer year)  {
			List <NewspaperDto> newspaperDto = new ArrayList<NewspaperDto>();
			for(Newspaper newspaper : newspaperService.getNewspapersByPublishedYear(year)) {
				newspaperDto.add(NewspaperDto.convertToDto(newspaper));
			}
			return httpSuccess(newspaperDto);
		}
		
		@GetMapping(value = { BASE_URL + "/by-journal-name/{journalName}", BASE_URL + "/by-journal-name/{journalName}/" })
		public ResponseEntity<?> getNewspapersbyJournalName(@PathVariable("journalName") String journalName)  {
			List <NewspaperDto> newspaperDto = new ArrayList<NewspaperDto>();
			for(Newspaper newspaper : newspaperService.getNewspapersbyJournalName(journalName)) {
				newspaperDto.add(NewspaperDto.convertToDto(newspaper));
			}

			return httpSuccess(newspaperDto);
		}

		@GetMapping(value = { BASE_URL + "/by-edition/{edition}", BASE_URL + "/by-edition/{edition}/" })
		public ResponseEntity<?> getNewspapersbyRuntime(@PathVariable("edition") String edition)  {
			List <NewspaperDto> newspaperDto = new ArrayList<NewspaperDto>();
			for(Newspaper newspaper : newspaperService.getNewspapersbyEdition(edition)) {
				newspaperDto.add(NewspaperDto.convertToDto(newspaper));
			}
			return httpSuccess(newspaperDto);
		}
		
		@GetMapping(value = { BASE_URL + "/by-chief-editor/{chiefEditor}", BASE_URL + "/by-chief-editor/{chiefEditor}/" })
		public ResponseEntity<?> getNewspapersbyChiefEditor(@PathVariable("chiefEditor") String chiefEditor)  {
			List <NewspaperDto> newspaperDto = new ArrayList<NewspaperDto>();
			for(Newspaper newspaper : newspaperService.getNewspapersByChiefEditor(chiefEditor)) {
				newspaperDto.add(NewspaperDto.convertToDto(newspaper));
			}
			return httpSuccess(newspaperDto);
		}
	  
}
