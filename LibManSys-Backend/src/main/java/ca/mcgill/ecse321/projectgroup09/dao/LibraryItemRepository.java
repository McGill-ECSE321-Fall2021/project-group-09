package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import java.util.List;

public interface LibraryItemRepository extends CrudRepository<LibraryItem, Long> {
	
	LibraryItem findLibraryItemByLibraryItemID(Long libraryItemID);
	LibraryItem findLibraryItemByLoan(Loan loan);
	List<LibraryItem> findLibraryItemByItemStatus(ItemStatus itemstatus);
	List<LibraryItem> findLibraryItemByTitle(String title);
	
}