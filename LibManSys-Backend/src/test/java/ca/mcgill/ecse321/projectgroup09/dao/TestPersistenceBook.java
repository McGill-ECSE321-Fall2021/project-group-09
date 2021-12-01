package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceBook {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private LibraryItemRepository libraryItemRepository; 
	
	@AfterEach
	public void clearDatabase() {
		bookRepository.deleteAll();
		libraryItemRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadBook() {
		
		Long libraryItemID = (long) 1; 
		String Title = "TestBook";
		int publishedYear = 2021;
		int loanablePeriod = 21; 
		double dailyOverdueFee = 50;
		ItemStatus itemStatus = ItemStatus.Available;
		
		String author = "Test Author";
		String publisher = "Test Publisher"; 
		String ISBN = "1234-5678-9101";
		int numPages = 200;
		
		Book book = new Book();
		book.setlibraryItemID(libraryItemID);
		book.setTitle(Title);
		book.setPublishedYear(publishedYear);
		book.setLoanablePeriod(loanablePeriod);
		book.setDailyOverdueFee(dailyOverdueFee);
		book.setItemStatus(itemStatus);
		
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setISBN(ISBN);
		book.setNumPages(numPages);
		
		book = bookRepository.save(book);

		libraryItemID = book.getlibraryItemID();
		
		book = null; 
		
		book = bookRepository.findBookBylibraryItemID(libraryItemID);
		
		assertNotNull(book);
		assertEquals(ISBN, book.getISBN());
		assertEquals(libraryItemID, book.getlibraryItemID());
		
	}
}
