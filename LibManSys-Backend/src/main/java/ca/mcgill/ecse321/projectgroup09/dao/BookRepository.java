package ca.mcgill.ecse321.projectgroup09.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Book;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
	
	Book findBookBylibraryItemID(Long libraryItemID);
	Book findBookByISBN(String ISBN);
	List<Book> findAll();
}