package ca.mcgill.ecse321.projectgroup09.dao;

import java.sql.Date;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Schedule.DayofWeek;

@ExtendWith(SpringExtension.class)
@SpringBootTest
/**
 * 
 * @author snehas
 *
 */

public class TestPersistAndLoadLibraryItems {

	@Autowired
	private ArchiveRepository  archiveRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private MovieRepository movieRepository; 
	
	@Autowired
	private MusicAlbumRepository musicAlbumRepository; 
	
	@Autowired
	private NewspaperRepository newspaperRepository; 
	
	@AfterEach
	public void clearDatabase() {
		bookRepository.deleteAll();
		movieRepository.deleteAll();
		musicAlbumRepository.deleteAll();
		newspaperRepository.deleteAll();
		archiveRepository.deleteAll();
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
		book.setPublishedYear(2021);
		book.setLoanablePeriod(21);
		book.setDailyOverdueFee(50);
		book.setItemStatus(itemStatus);
		
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setISBN(ISBN);
		book.setNumPages(numPages);
		
		bookRepository.save(book);
		
		book = null; 
		book = bookRepository.findBookByISBN(ISBN);
		assertNotNull(book);
		
		book = bookRepository.findBookBylibraryItemID(libraryItemID);
		assertEquals(ISBN, book.getISBN());
		assertEquals(libraryItemID, book.getlibraryItemID());
		
	}
	
	
	@Test
	public void testPersistAndLoadMovie() {
		
		Long libraryItemID = (long) 3; 
		String Title = "TestMovie";
		int publishedYear = 2021;
		int loanablePeriod = 21; 
		double dailyOverdueFee = 100;
		ItemStatus itemStatus = ItemStatus.Available;
		
		String director = "Test Director";
		int runtime = 120;
		String genre = "Horror";
				
		Movie movie = new Movie();
		movie.setlibraryItemID(libraryItemID);
		movie.setTitle(Title);
		movie.setPublishedYear(2021);
		movie.setLoanablePeriod(21);
		movie.setDailyOverdueFee(50);
		movie.setItemStatus(itemStatus);
		
		movie.setDirector(director);
		movie.setRuntime(runtime);
		movie.setGenre(genre);
		
		movieRepository.save(movie);
		
		movie = null; 
		movie = movieRepository.findMovieBylibraryItemID(libraryItemID);
		assertNotNull(movie);

		assertEquals(Title, movie.getTitle());
		assertEquals(libraryItemID, movie.getlibraryItemID());
	
	}
	
	@Test
	public void testPersistAndLoadArchive() {
		
		Long libraryItemID = (long) 1; 
		String Title = "TestMovie";
		int publishedYear = 2021;
		int loanablePeriod = 21; 
		double dailyOverdueFee = 100;
		ItemStatus itemStatus = ItemStatus.Available;
		
		String insitution = "Academic";
				
		Archive archive = new Archive();
		archive.setlibraryItemID(libraryItemID);
		archive.setTitle(Title);
		archive.setPublishedYear(2021);
		archive.setLoanablePeriod(21);
		archive.setDailyOverdueFee(50);
		archive.setItemStatus(itemStatus);
		
		archive.setInstitution(insitution);
		
		archiveRepository.save(archive);
		
		archive = null; 
		archive = archiveRepository.findArchiveBylibraryItemID(libraryItemID);
		assertNotNull(archive);

		assertEquals(Title, archive.getTitle());
		assertEquals(libraryItemID, archive.getlibraryItemID());
	
	}
	
}
