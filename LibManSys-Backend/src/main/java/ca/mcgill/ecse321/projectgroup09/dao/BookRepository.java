package ca.mcgill.ecse321.projectgroup09.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Book;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {
	
	Book findBookBylibraryItemID(Long libraryItemID);
	/** Careful, ISBN is not garunteed to be unique. */
	Book findBookByISBN(String ISBN);
	List<Book> findAll();
}