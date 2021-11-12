package ca.mcgill.ecse321.projectgroup09.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.LoanDto;
import ca.mcgill.ecse321.projectgroup09.dto.MovieDto;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Movie;
import ca.mcgill.ecse321.projectgroup09.service.MovieService;

@CrossOrigin(origins = "*")
@RestController
public class MovieController {
	

	@Autowired
	private MovieService movieService;
	
	
	@PostMapping(value = { "/Movie/CreateMovie}", "/Movie/CreateMovie/" })
	public MovieDto createLoan(@PathVariable("title") String title, @PathVariable("publishedYear") Integer publishedYear,
			@PathVariable("loanablePeriod") Integer loanablePeriod, @PathVariable("dailyOverdueFee") Double dailyOverdueFee,
			@PathVariable("itemStatus") ItemStatus itemStatus, @PathVariable("director") String director,
			@PathVariable("libraryItemId") Long libraryItemId,@PathVariable("runtime") Integer runtime,
			@PathVariable("genre") String genre)  {
		Movie movie = movieService.createMovie(libraryItemId, title, publishedYear, loanablePeriod, dailyOverdueFee, itemStatus, director, runtime, genre);
		return MovieDto.convertToDto(movie);
	}
	
	@GetMapping(value = { "/Movie/All", "/Movie/All/" })
	public List<MovieDto> getAllMovies() {
		// Create list of all books, converted to Dto's
		return movieService.getAllMovies().stream().map(movie -> MovieDto.convertToDto(movie)).collect(Collectors.toList());
	}

	@PostMapping(value = { "/Moan/Delete/{id}", "/Moan/Delete/{id}" })
	public void deleteMovie(@PathVariable("loanId") Long loanId)  {
		movieService.deleteMovie(loanId);
		//return true;
	}
	
	@PostMapping(value = { "/Movie/PublishedYear", "/Movie/PublishedYear/" })
	public List<MovieDto> getMoviesbyPublishedYear(@PathVariable("publishedYear") Integer publishedYear)  {
		List <MovieDto> movieDto = new ArrayList<MovieDto>();
		for(Movie movie : movieService.getMoviesbyPublishedYear(publishedYear)) {
			movieDto.add(MovieDto.convertToDto(movie));
		}

		return movieDto;
	}
	
	@PostMapping(value = { "/Movie/Genre", "/Movie/Genre/" })
	public List<MovieDto> getMoviesbyGenre(@PathVariable("genre") String genre)  {
		List <MovieDto> movieDto = new ArrayList<MovieDto>();
		for(Movie movie : movieService.getMoviesbyGenre(genre)) {
			movieDto.add(MovieDto.convertToDto(movie));
		}

		return movieDto;
	}
	
	@PostMapping(value = { "/Movie/Director", "/Movie/Director/" })
	public List<MovieDto> getMoviesbyDirector(@PathVariable("director") String director)  {
		List <MovieDto> movieDto = new ArrayList<MovieDto>();
		for(Movie movie : movieService.getMoviesbyGenre(director)) {
			movieDto.add(MovieDto.convertToDto(movie));
		}

		return movieDto;
	}

}
