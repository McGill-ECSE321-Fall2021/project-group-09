package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Newspaper;
import java.util.List;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface NewspaperRepository extends CrudRepository<Newspaper, Integer> {
	
	Newspaper findNewspaperBylibraryItemID(Long libraryItemID);
	List<Newspaper> findNewspapersByJournalName(String journalName);
	List<Newspaper> findNewspapersByChiefEditor(String chiefEditor);
}