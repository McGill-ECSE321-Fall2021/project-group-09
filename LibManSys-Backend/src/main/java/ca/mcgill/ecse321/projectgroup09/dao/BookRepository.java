package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Book;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
	
	Book findBookBylibraryItemID(Integer libraryItemID);
	Book findBookByisbn(String isbn);
}