package ca.mcgill.ecse321.projectgroup09.service;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.*;
import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;

/**
 * author: Rajaa
 */
@Service
public class MovieService {
	@Autowired
	private MovieRepository  movieRepository;
	
	@Transactional
	public Movie createMovie(Long libraryItemId, String title, Integer publishedYear,
			Integer loanablePeriod, Double dailyOverdueFee, ItemStatus itemStatus, String director,
			Integer runtime, String genre) {

        if (movieRepository.findMoviesByDirector(director)== null ||
        		movieRepository.findMoviesByGenre(genre)== null  ||
        		movieRepository.findMoviesByPublishedYear(publishedYear)== null
               ){
            throw new IllegalArgumentException ("Movie does not exist");
        }
        
        
		Movie movie = new Movie();
		movie.setlibraryItemID(libraryItemId); //do we set ids?
		movie.setTitle(title);
		movie.setPublishedYear(publishedYear);
		movie.setLoanablePeriod(loanablePeriod);
		movie.setDailyOverdueFee(dailyOverdueFee);
		movie.setItemStatus(itemStatus);
		movie.setDirector(director);
		movie.setRuntime(runtime);
		movie.setGenre(genre);
		movieRepository.save(movie);
		return movie;
	}
	
	@Transactional
	public Movie updateMovie(Long libraryItemId, String title, Integer publishedYear,
			Integer loanablePeriod, Double dailyOverdueFee, ItemStatus itemStatus, String director,
			Integer runtime, String genre) {

        if (movieRepository.findMoviesByDirector(director)== null ||
        		movieRepository.findMoviesByGenre(genre)== null  ||
        		movieRepository.findMoviesByPublishedYear(publishedYear)== null
               ){
            throw new IllegalArgumentException ("Movie does not exist");
        }
        
        
		Movie movie = movieRepository.findMovieBylibraryItemID(libraryItemId);
		//movie.setlibraryItemID(libraryItemId); don't set id since we know it here
		movie.setTitle(title);
		movie.setPublishedYear(publishedYear);
		movie.setLoanablePeriod(loanablePeriod);
		movie.setDailyOverdueFee(dailyOverdueFee);
		movie.setItemStatus(itemStatus);
		movie.setDirector(director);
		movie.setRuntime(runtime);
		movie.setGenre(genre);
		movieRepository.save(movie);
		return movie;
	}
	
	public void deleteMovie(Long libraryItemId) {
		
	      Movie movie = movieRepository.findMovieBylibraryItemID(libraryItemId);
	      if (movie ==null) {
	     throw new IllegalArgumentException ("Movie does not exist"); 
	      }
	      movieRepository.delete(movie);
	      
		}
		
		public Movie readMovie(Long libraryItemId) {
		 Movie movie = movieRepository.findMovieBylibraryItemID(libraryItemId);
	    return movie;
		}
		
		@Transactional
		public List<Movie> getAllMovies(){
			return toList(movieRepository.findAll());
		}
		

	
	//from tutorials
		private <T> List<T> toList(Iterable<T> iterable){
			List<T> resultList = new ArrayList<T>();
			for (T t : iterable) {
				resultList.add(t);
			}
			return resultList;
	

}}
