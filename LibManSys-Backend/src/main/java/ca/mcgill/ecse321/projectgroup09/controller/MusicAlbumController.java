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
import ca.mcgill.ecse321.projectgroup09.dto.MusicAlbumDto;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;
import ca.mcgill.ecse321.projectgroup09.service.MusicAlbumService;

/**
 * CRUD for Music Albums.
 */
@CrossOrigin(origins = "*")
@RestController
public class MusicAlbumController {
	
	private static final String BASE_URL = "/musicAlbum";

	@Autowired
	private MusicAlbumService musicAlbumService;
	
	/**
	 * Get all musicAlbums.
	 * @return
	 */
	@GetMapping(value = {BASE_URL, BASE_URL + "/"})
	public ResponseEntity<?> getAllMusicAlbums() {
		List<LibraryItemDto> musicAlbums = LibraryItemDto.convertToDto(musicAlbumService.getAllMusicAlbums());
		return httpSuccess(musicAlbums);
	}
	
	/**
	 * Create new music album.
	 * @param title
	 * @param publishedYear
	 * @param genre
	 * @param artist
	 * @param numSongs
	 * @param albumLengthInMinutes
	 * @return
	 */
	@PostMapping(value = { BASE_URL + "/create", BASE_URL + "/create/"})
	public ResponseEntity<?> createMusicAlbum(@RequestParam("title") String title, 
			@RequestParam("publishedYear") Integer publishedYear,
			@RequestParam("genre") String genre,
			@RequestParam("artist") String artist,
			@RequestParam("numSongs") Integer numSongs,
			@RequestParam("albumLengthInMinutes") Integer albumLengthInMinutes) {
		MusicAlbum musicAlbum;
		try {
			musicAlbum = musicAlbumService.createMusicAlbum(title, publishedYear, genre, artist, numSongs, albumLengthInMinutes);
		} catch(Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		return httpSuccess(MusicAlbumDto.convertToDto(musicAlbum));
	}
	
	/**
	 * Get an musicAlbum by ID.
	 * @param musicAlbumId
	 * @return
	 */
	@GetMapping(value = { BASE_URL + "/{id}", BASE_URL + "/{id}/" })
	public ResponseEntity<?> getMusicAlbumById(@PathVariable("id") Long musicAlbumId) {
		try {
			MusicAlbumDto adto = MusicAlbumDto.convertToDto(musicAlbumService.getMusicAlbumById(musicAlbumId));
			return httpSuccess(adto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	/**
	 * Update a music album. Fields for which no parameters
	 * are passed will retain there current value.
	 * @param musicAlbumId
	 * @param title
	 * @param publisherYear
	 * @param loanablePeriod
	 * @param dailyOverdueFee
	 * @param itemStatus
	 * @param genre
	 * @param artist
	 * @param numSongs
	 * @param albumLengthInMinutes
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/update/{id}", BASE_URL + "/update/{id}/"})
	public ResponseEntity<?> updateMusicAlbum(@PathVariable("id") Long musicAlbumId, 
			@RequestParam(value = "title", required = false) String title, 
			@RequestParam(value = "publisherYear", required = false) Integer publisherYear,
			@RequestParam(value = "loanablePeriod", required = false) Integer loanablePeriod, 
			@RequestParam(value = "dailyOverdueFee", required = false) Double dailyOverdueFee, 
			@RequestParam(value = "itemStatus", required = false) String itemStatus,
			@RequestParam(value = "genre", required = false) String genre,
			@RequestParam(value = "artist", required = false) String artist,
			@RequestParam(value = "numSongs", required = false) Integer numSongs,
			@RequestParam(value = "albumLengthInMinutes", required = false) Integer albumLengthInMinutes) {
		try {
			ItemStatus iS = ItemStatus.valueOf(itemStatus);
			MusicAlbumDto bookDto = MusicAlbumDto.convertToDto(musicAlbumService.updateMusicAlbum(musicAlbumId, title, publisherYear, loanablePeriod, dailyOverdueFee, iS, genre, artist, numSongs, albumLengthInMinutes));
			return httpSuccess(bookDto);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Delete Music Album from repository.
	 * @param musicAlbumId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/"})
	public ResponseEntity<?> deleteMusicAlbum(@PathVariable("id") Long musicAlbumId) {
		if (musicAlbumId == null) {
			return httpFailureMessage("Error: ID cannot be null.");
		}
		boolean deleted = false;
		try {
			deleted = musicAlbumService.deleteMusicAlbumById(musicAlbumId);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
		if (deleted) {
			return httpSuccess("Deleted Music Album " + musicAlbumId + " from repository succesfully.");
		}
		// else
		return httpFailureMessage("Failed to delete Music Album with id " + musicAlbumId + " because it does not exist.");
	}
	
	// getters
	@GetMapping(value = {BASE_URL + "/by-title/{title}", BASE_URL + "/by-title/{title}/" })
	public ResponseEntity<?> getMusicAlbumsbyTitle(@PathVariable("title") String title)  {
		List <MusicAlbumDto> musicAlbumDto = new ArrayList<MusicAlbumDto>();
		for(MusicAlbum m : musicAlbumService.getMusicAlbumsByTitle(title)) {
			musicAlbumDto.add(MusicAlbumDto.convertToDto(m));
		}
		return httpSuccess(musicAlbumDto);
	}
	
	@GetMapping(value = {BASE_URL + "/by-published-year/{year}", BASE_URL + "/by-published-year/{year}/" })
	public ResponseEntity<?> getMusicAlbumsbyPublishedYear(@PathVariable("year") Integer year)  {
		List <MusicAlbumDto> musicAlbumDto = new ArrayList<MusicAlbumDto>();
		for(MusicAlbum musicAlbum : musicAlbumService.getMusicAlbumsByPublishedYear(year)) {
			musicAlbumDto.add(MusicAlbumDto.convertToDto(musicAlbum));
		}
		return httpSuccess(musicAlbumDto);
	}
	
	@GetMapping(value = { BASE_URL + "/by-genre/{genre}", BASE_URL + "/by-genre/{genre}/" })
	public ResponseEntity<?> getMusicAlbumsbyGenre(@PathVariable("genre") String genre)  {
		List <MusicAlbumDto> musicAlbumDto = new ArrayList<MusicAlbumDto>();
		for(MusicAlbum musicAlbum : musicAlbumService.getMusicAlbumsbyGenre(genre)) {
			musicAlbumDto.add(MusicAlbumDto.convertToDto(musicAlbum));
		}

		return httpSuccess(musicAlbumDto);
	}
	
	@GetMapping(value = { BASE_URL + "/by-artist/{artist}", BASE_URL + "/by-artist/{artist}/" })
	public ResponseEntity<?> getMusicAlbumsbyArtist(@PathVariable("artist") String artist)  {
		List <MusicAlbumDto> musicAlbumDto = new ArrayList<MusicAlbumDto>();
		for(MusicAlbum musicAlbum : musicAlbumService.getMusicAlbumsbyArtist(artist)) {
			musicAlbumDto.add(MusicAlbumDto.convertToDto(musicAlbum));
		}
		return httpSuccess(musicAlbumDto);
	}

	@GetMapping(value = { BASE_URL + "/by-num-songs/{numSongs}", BASE_URL + "/by-num-songs/{numSongs}/" })
	public ResponseEntity<?> getMusicAlbumsbyNumSongs(@PathVariable("numSongs") int numSongs)  {
		List <MusicAlbumDto> musicAlbumDto = new ArrayList<MusicAlbumDto>();
		for(MusicAlbum musicAlbum : musicAlbumService.getMusicAlbumByNumSongs(numSongs)) {
			musicAlbumDto.add(MusicAlbumDto.convertToDto(musicAlbum));
		}
		return httpSuccess(musicAlbumDto);
	}
	
	@GetMapping(value = { BASE_URL + "/by-album-length/{albumLength}", BASE_URL + "/by-album-length/{albumLength}/" })
	public ResponseEntity<?> getMusicAlbumsbyAlbumLength(@PathVariable("albumLength") int albumLength)  {
		List <MusicAlbumDto> musicAlbumDto = new ArrayList<MusicAlbumDto>();
		for(MusicAlbum musicAlbum : musicAlbumService.getMusicAlbumByAlbumLength(albumLength)) {
			musicAlbumDto.add(MusicAlbumDto.convertToDto(musicAlbum));
		}
		return httpSuccess(musicAlbumDto);
	}
}
