package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import java.util.List;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface LibraryItemRepository extends CrudRepository<LibraryItem, Integer> {

	LibraryItem findLibraryItemByLibraryItemID(Long libraryItemID);
	LibraryItem findLibraryItemByLoans(Loan loan);
	List<LibraryItem> findLibraryItemByItemStatus(ItemStatus itemstatus);
	List<LibraryItem> findLibraryItemByTitle(String title);
	
}