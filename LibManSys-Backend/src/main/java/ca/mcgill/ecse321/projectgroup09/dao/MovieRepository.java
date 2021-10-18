//package ca.mcgill.ecse321.projectgroup09.dao;
//
//import org.springframework.data.repository.CrudRepository;
//import ca.mcgill.ecse321.projectgroup09.models.Movie;
//import java.util.List;
//
//public interface MovieRepository extends CrudRepository<Movie, Long> {
//	
//	Movie findMovieBylibraryItemID(Long libraryItemID);
//	List<Movie> findMoviesByPublishedYear(int publishedYear);
//	List<Movie> findMoviesByGenre(String genre);
//	List<Movie> findMoviesByDirector(String director);
//}