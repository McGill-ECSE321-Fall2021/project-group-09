package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Newspaper;

public interface NewspaperRepository extends CrudRepository<Newspaper, Long> {
	
	Newspaper findNewspaperBylibraryItemID(Long libraryItemID);
}