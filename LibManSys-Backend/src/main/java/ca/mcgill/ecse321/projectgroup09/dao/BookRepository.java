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
	List<Book> findAll();
	/** Careful, ISBN is not garunteed to be unique. */
	List<Book> findBooksByISBN(String ISBN);
	List<Book> findBooksByTitle(String title);
	List<Book> findBooksByPublishedYear(int publishedYear);
	List<Book> findBooksByAuthor(String author);
	List<Book> findBooksByPublisher(String publisher);
	List<Book> findBooksByNumPages(int numPages);
}