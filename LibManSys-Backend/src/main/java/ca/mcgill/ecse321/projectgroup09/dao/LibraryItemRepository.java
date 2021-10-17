package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;

public interface LibraryItemRepository extends CrudRepository<LibraryItem, Long> {
	
	LibraryItem findLibraryItembyLibraryItemID(Long libraryItemID);
}