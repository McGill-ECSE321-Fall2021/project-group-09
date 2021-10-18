package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Newspaper;
import java.util.List;

public interface NewspaperRepository extends CrudRepository<Newspaper, Long> {
	
	Newspaper findNewspaperBylibraryItemID(Long libraryItemID);
	List<Newspaper> findNewspapersByJournalName(String journalName);
	List<Newspaper> findNewspapersByChiefEditor(String chiefEditor);
}