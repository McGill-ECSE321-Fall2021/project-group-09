package ca.mcgill.ecse321.projectgroup09.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup09.models.Newspaper;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface NewspaperRepository extends CrudRepository<Newspaper, Long> {
	Newspaper findNewspaperBylibraryItemID(Long libraryItemID);
	List<Newspaper> findAll();
	List<Newspaper> findNewspapersByTitle(String title);
	List<Newspaper> findNewspapersByPublishedYear(int publishedYear);
	List<Newspaper> findNewspapersByJournalName(String journalName);
	List<Newspaper> findNewspapersByChiefEditor(String chiefEditor);
	List<Newspaper> findNewspapersByEdition(String edition);
}