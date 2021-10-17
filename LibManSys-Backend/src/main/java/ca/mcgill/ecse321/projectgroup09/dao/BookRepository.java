package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup09.models.*;

public interface BookRepository extends CrudRepository<Book, Long> {
	
	Book findBookBylibraryItemID(Long libraryItemID);
	Book findBookByisbn(String isbn);
}