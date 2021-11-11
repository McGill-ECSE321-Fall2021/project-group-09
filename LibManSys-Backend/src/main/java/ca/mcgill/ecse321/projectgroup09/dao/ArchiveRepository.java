package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import ca.mcgill.ecse321.projectgroup09.models.Archive;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface ArchiveRepository extends CrudRepository<Archive, Long> {
	
	Archive findArchiveBylibraryItemID(Long libraryItemID);
	List<Archive> findArchivesByInstitution(String institution);
	
}