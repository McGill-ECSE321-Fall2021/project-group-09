package ca.mcgill.ecse321.projectgroup09.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface LibraryItemRepository extends CrudRepository<LibraryItem, Long> {

	LibraryItem findLibraryItemByLibraryItemID(Long libraryItemID);
	LibraryItem findLibraryItemByLoans(Loan loan);
	List<LibraryItem> findLibraryItemByItemStatus(ItemStatus itemstatus);
	List<LibraryItem> findLibraryItemByMember(Member member);
	List<LibraryItem> findAll();
	List<LibraryItem> findLibraryItemByTitle(String title);
	List<LibraryItem> findLibraryItemByPublishedYear(int publishedYear);
}