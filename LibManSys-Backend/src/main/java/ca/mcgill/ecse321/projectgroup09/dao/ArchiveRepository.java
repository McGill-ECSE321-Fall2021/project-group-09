package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Archive;

public interface ArchiveRepository extends CrudRepository<Archive, Long> {
	
	Archive findArchiveBylibraryItemID(Long libraryItemID);
}