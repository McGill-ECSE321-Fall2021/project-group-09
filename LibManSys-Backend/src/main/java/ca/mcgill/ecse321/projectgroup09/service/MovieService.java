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
	
	/**
	 * 
	 * @param title
	 * @param publishedYear
	 * @param director
	 * @param runtime
	 * @param genre
	 * @return
	 */
	@Transactional
	public Movie createMovie(String title, Integer publishedYear,
			String director, Integer runtime, String genre) {

        if (movieRepository.findMoviesByDirector(director)== null ||
        		movieRepository.findMoviesByGenre(genre)== null  ||
        		movieRepository.findMoviesByPublishedYear(publishedYear)== null
               ){
            throw new IllegalArgumentException ("Movies doe not exist");
        }
        if(genre == null) {
            throw new IllegalArgumentException ("Movies doe not have a grenre");
        }
        if(publishedYear == null) {
            throw new IllegalArgumentException ("Movies doe not have a publishedYear");
        }
        if(director == null) {
            throw new IllegalArgumentException ("Movies doe not have a director");
        }
        
        
		Movie movie = new Movie();
		//movie.setlibraryItemID(libraryItemId); //do we set ids? no
		movie.setTitle(title);
		movie.setPublishedYear(publishedYear);
		//movie.setLoanablePeriod(loanablePeriod);
		//movie.setDailyOverdueFee(dailyOverdueFee);
		//movie.setItemStatus(itemStatus);
		movie.setDirector(director);
		movie.setRuntime(runtime);
		movie.setGenre(genre);
		movieRepository.save(movie);
		return movie;
	}
	
	/**
	 * Fields left null will not be updated.
	 * @param libraryItemId
	 * @param title
	 * @param publishedYear
	 * @param loanablePeriod
	 * @param dailyOverdueFee
	 * @param itemStatus
	 * @param director
	 * @param runtime
	 * @param genre
	 * @return
	 */
	@Transactional
	public Movie updateMovie(Long libraryItemId, String title, Integer publishedYear,
			Integer loanablePeriod, Double dailyOverdueFee, ItemStatus itemStatus, 
			String director, Integer runtime, String genre) {
        if(genre == null) {
            throw new IllegalArgumentException ("Movies doe not have a grenre");
        }
        if(loanablePeriod == null) {
            throw new IllegalArgumentException ("Movies doe not have a loablePeriod");
        }
        if(publishedYear == null) {
            throw new IllegalArgumentException ("Movies doe not have a publishedYear");
        }
        if(director == null) {
            throw new IllegalArgumentException ("Movies doe not have a director");
        }
        
		Movie movie = movieRepository.findMovieBylibraryItemID(libraryItemId);
		if (movie == null) {
			throw new IllegalArgumentException("Could not find movie with that ID.");
		}
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
	
	/**
	 * 
	 * @param libraryItemId
	 * @return
	 */
	@Transactional
	public boolean deleteMovie(Long libraryItemId) {
		
	      Movie movie = movieRepository.findMovieBylibraryItemID(libraryItemId);
	      if (movie ==null) {
	     throw new IllegalArgumentException ("Movie does not exist"); 
	      }
	      movieRepository.delete(movie);
	      return true;
		}
	
	/**
	 * wrapper
	 */
	@Transactional
	public boolean deleteMovieById(Long libraryItemId) {
		return deleteMovie(libraryItemId);
	}
	
	/**
	 * 
	 * @param libraryItemId
	 * @return
	 */
	@Transactional
		public Movie getMovieById(Long libraryItemId) {
		 Movie movie = movieRepository.findMovieBylibraryItemID(libraryItemId);
	    return movie;
		}
	
					//BUSINESS METHODS//	

		@Transactional
		public List<Movie> getAllMovies(){
			return toList(movieRepository.findAll());
		}
		
		@Transactional
		public List<Movie> getMoviesByTitle(String title) {
			return movieRepository.findMoviesByTitle(title);
		}
		
		@Transactional
		public List<Movie> getMoviesbyPublishedYear(Integer year) {
			return toList(movieRepository.findMoviesByPublishedYear(year));
		}
		
		@Transactional
		public List<Movie> getMoviesbyGenre(String genre) {
			return toList(movieRepository.findMoviesByGenre(genre));
		}
		
		
		
		@Transactional
		public List<Movie> getMoviesbyDirector(String director) {
			return toList(movieRepository.findMoviesByDirector(director));
		}
		
		@Transactional
		public List<Movie> getMoviesByRuntime(int runtime) {
			return movieRepository.findMoviesByRuntime(runtime);
		}
		
					//HELPER METHODS//	
	
	//from tutorials
		private <T> List<T> toList(Iterable<T> iterable){
			List<T> resultList = new ArrayList<T>();
			for (T t : iterable) {
				resultList.add(t);
			}
			return resultList;
	

}}
