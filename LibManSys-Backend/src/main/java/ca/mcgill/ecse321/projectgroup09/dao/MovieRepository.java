package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long> {
	
	Movie findMovieBylibraryItemID(Long libraryItemID);
}