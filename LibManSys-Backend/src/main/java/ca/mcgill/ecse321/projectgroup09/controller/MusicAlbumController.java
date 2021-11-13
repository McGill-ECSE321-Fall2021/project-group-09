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

import ca.mcgill.ecse321.projectgroup09.dto.MusicAlbumDto;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;
import ca.mcgill.ecse321.projectgroup09.service.MusicAlbumService;


@CrossOrigin(origins = "*")
@RestController
public class MusicAlbumController {
	
private static final String BASE_URL = "/music album";
	
	@Autowired
	private MusicAlbumService musicAlbumService;
	

	@GetMapping(value = {BASE_URL, BASE_URL + "/"})
	public List<MusicAlbumDto> getAllBooks() {

		return musicAlbumService.getAllMusicAlbums().stream().map(musicAlbum -> MusicAlbumDto.convertToDto(musicAlbum)).collect(Collectors.toList());
	}
	
	@PostMapping(value = { BASE_URL + "/create/{title}/{publishedYear}/{genre}/{artist}/", BASE_URL + "/create/{title}/{publishedYear}/{genre}/{artist}"})
	
	public MusicAlbumDto createmusicAlbum(@PathVariable("title") String title, @PathVariable("publishedYear") String publishedYear, @PathVariable("genre") String genre, @PathVariable("artist") String artist, @PathVariable("numSongs") int numSongs, @PathVariable("albumLengthInMinutes") int albumLengthInMinutes) 
			throws IllegalArgumentException {
		int pubYear;
		int numSongs_i;
		int albumLengthInMinutes_i;
		
		try {
			pubYear = Integer.valueOf(publishedYear);
		} catch(Exception e) {
			throw new IllegalArgumentException("publishedYear must be valid integer. Error" + e.getMessage());
		}
		
		try {
			numSongs_i = Integer.valueOf(numSongs);
		} catch(Exception e) {
			throw new IllegalArgumentException("numSongs must be valid integer. Error" + e.getMessage());
		}
		
		try {
			albumLengthInMinutes_i= Integer.valueOf(albumLengthInMinutes);
		} catch(Exception e) {
			throw new IllegalArgumentException("albumLengthInMinutes must be valid integer. Error" + e.getMessage());
		}
		
		
	
		MusicAlbum musicAlbum = musicAlbumService.createMusicAlbum(title, pubYear, genre, artist, numSongs, albumLengthInMinutes);
		return MusicAlbumDto.convertToDto(musicAlbum);
	}
