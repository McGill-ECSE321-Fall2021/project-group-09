package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Library;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface LibraryRepository extends CrudRepository<Library, String> {
	
	Library findLibraryByLibraryName(String libraryName);
}