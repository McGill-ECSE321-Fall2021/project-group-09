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
import ca.mcgill.ecse321.projectgroup09.dto.MovieDto;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Movie;
import ca.mcgill.ecse321.projectgroup09.service.MovieService;

/**
 * CRUD for movies.
 */
@CrossOrigin(origins = "*")
@RestController
public class MovieController {
	
	private static final String BASE_URL = "/movie";

	@Autowired
	private MovieService movieService;
	
	/**
	 * Get all movies.
	 * @return
	 */
	@GetMapping(value = {BASE_URL, BASE_URL + "/"})
	public ResponseEntity<?> getAllMovies() {
		List<LibraryItemDto> movies = LibraryItemDto.convertToDto(movieService.getAllMovies());
		return httpSuccess(movies);
	}
	
	/**
	 * Create new movie.
	 * @param title
	 * @param publishedYear
	 * @param director
	 * @param runtime
	 * @param genre
	 * @return
	 */
	@PostMapping(value = { BASE_URL + "/create", BASE_URL + "/create/"})
	public ResponseEntity<?> createMovie(@RequestParam("title") String title, 
			@RequestParam("publishedYear") Integer publishedYear,
			@RequestParam("director") String director,
			@RequestParam("runtime") Integer runtime,
			@RequestParam("genre") String genre) {
		Movie movie;
		try {
			movie = movieService.createMovie(title, publishedYear, director, runtime, genre);
		} catch(Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		return httpSuccess(MovieDto.convertToDto(movie));
	}
	
	/**
	 * Get an movie by ID.
	 * @param movieId
	 * @return
	 */
	@GetMapping(value = { BASE_URL + "/{id}", BASE_URL + "/{id}/" })
	public ResponseEntity<?> getMovieById(@PathVariable("id") Long movieId) {
		try {
			MovieDto adto = MovieDto.convertToDto(movieService.getMovieById(movieId));
			return httpSuccess(adto);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
	}
	
	
	@PostMapping(value = {BASE_URL + "/update/{id}", BASE_URL + "/update/{id}/"})
	public ResponseEntity<?> updateMovie(@PathVariable("id") Long movieId, 
			@RequestParam(value = "title", required = false) String title, 
			@RequestParam(value = "publisherYear", required = false) Integer publisherYear,
			@RequestParam(value = "loanablePeriod", required = false) Integer loanablePeriod, 
			@RequestParam(value = "dailyOverdueFee", required = false) Double dailyOverdueFee, 
			@RequestParam(value = "itemStatus", required = false) String itemStatus,
			@RequestParam(value = "director", required = false) String director,
			@RequestParam(value = "runtime", required = false) Integer runtime,
			@RequestParam(value = "genre", required = false) String genre) {
		try {
			ItemStatus iS = ItemStatus.valueOf(itemStatus);
			MovieDto bookDto = MovieDto.convertToDto(movieService.updateMovie(movieId, title, publisherYear, loanablePeriod, dailyOverdueFee, iS, director, runtime, genre));
			return httpSuccess(bookDto);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Delete movie from repository.
	 * @param movieId
	 * @return
	 */
	@PostMapping(value = {BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/"})
	public ResponseEntity<?> deleteMovie(@PathVariable("id") Long movieId) {
		if (movieId == null) {
			return httpFailureMessage("Error: ID cannot be null.");
		}
		boolean deleted = false;
		try {
			deleted = movieService.deleteMovieById(movieId);
		} catch (Exception e) {
			return httpFailureMessage("Error: " + e.getMessage());
		}
		if (deleted) {
			return httpSuccess("Deleted Movie " + movieId + " from repository succesfully.");
		}
		// else
		return httpFailureMessage("Failed to delete Movie with id " + movieId + " because it does not exist.");
	}
	
	// Other getters
	
	@GetMapping(value = {BASE_URL + "/by-title/{title}", BASE_URL + "/by-title/{title}/" })
	public ResponseEntity<?> getMoviesbyTitle(@PathVariable("title") String title)  {
		List <MovieDto> movieDto = new ArrayList<MovieDto>();
		for(Movie movie : movieService.getMoviesByTitle(title)) {
			movieDto.add(MovieDto.convertToDto(movie));
		}
		return httpSuccess(movieDto);
	}
	
	@GetMapping(value = {BASE_URL + "/by-published-year/{year}", BASE_URL + "/by-published-year/{year}/" })
	public ResponseEntity<?> getMoviesbyPublishedYear(@PathVariable("year") Integer year)  {
		List <MovieDto> movieDto = new ArrayList<MovieDto>();
		for(Movie movie : movieService.getMoviesbyPublishedYear(year)) {
			movieDto.add(MovieDto.convertToDto(movie));
		}
		return httpSuccess(movieDto);
	}
	
	@GetMapping(value = { BASE_URL + "/by-genre/{genre}", BASE_URL + "/by-genre/{genre}/" })
	public ResponseEntity<?> getMoviesbyGenre(@PathVariable("genre") String genre)  {
		List <MovieDto> movieDto = new ArrayList<MovieDto>();
		for(Movie movie : movieService.getMoviesbyGenre(genre)) {
			movieDto.add(MovieDto.convertToDto(movie));
		}

		return httpSuccess(movieDto);
	}
	
	@GetMapping(value = { BASE_URL + "/by-director/{director}", BASE_URL + "/by-director/{director}/" })
	public ResponseEntity<?> getMoviesbyDirector(@PathVariable("director") String director)  {
		List <MovieDto> movieDto = new ArrayList<MovieDto>();
		for(Movie movie : movieService.getMoviesbyDirector(director)) {
			movieDto.add(MovieDto.convertToDto(movie));
		}
		return httpSuccess(movieDto);
	}

	@GetMapping(value = { BASE_URL + "/by-runtime/{runtime}", BASE_URL + "/by-runtime/{runtime}/" })
	public ResponseEntity<?> getMoviesbyRuntime(@PathVariable("runtime") int runtime)  {
		List <MovieDto> movieDto = new ArrayList<MovieDto>();
		for(Movie movie : movieService.getMoviesByRuntime(runtime)) {
			movieDto.add(MovieDto.convertToDto(movie));
		}
		return httpSuccess(movieDto);
	}
	
}
