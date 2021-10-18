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
		book.setPublishedYear(publishedYear);
		book.setLoanablePeriod(loanablePeriod);
		book.setDailyOverdueFee(dailyOverdueFee);
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
		movie.setPublishedYear(publishedYear);
		movie.setLoanablePeriod(loanablePeriod);
		movie.setDailyOverdueFee(dailyOverdueFee);
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
		archive.setPublishedYear(publishedYear);
		archive.setLoanablePeriod(loanablePeriod);
		archive.setDailyOverdueFee(dailyOverdueFee);
		archive.setItemStatus(itemStatus);
		
		archive.setInstitution(insitution);
		
		archiveRepository.save(archive);
		
		archive = null; 
		archive = archiveRepository.findArchiveBylibraryItemID(libraryItemID);
		assertNotNull(archive);

		assertEquals(Title, archive.getTitle());
		assertEquals(libraryItemID, archive.getlibraryItemID());
	
	}
	
	@Test
	public void testPersistAndLoadNewspaper() {
		
		Long libraryItemID = (long) 1; 
		String Title = "TestMovie";
		int publishedYear = 2021;
		int loanablePeriod = 21; 
		double dailyOverdueFee = 100;
		ItemStatus itemStatus = ItemStatus.Available;
		
		String journalName = "Test Journal";
		String edition = "First";
		String chiefEditor = "Test Editor";
				
		Newspaper newspaper = new Newspaper();
		newspaper.setlibraryItemID(libraryItemID);
		newspaper.setTitle(Title);
		newspaper.setPublishedYear(publishedYear);
		newspaper.setLoanablePeriod(loanablePeriod);
		newspaper.setDailyOverdueFee(dailyOverdueFee);
		newspaper.setItemStatus(itemStatus);
		
		newspaper.setJournalName(journalName);
		newspaper.setEdition(edition);
		newspaper.setChiefEditor(chiefEditor);
		
		newspaperRepository.save(newspaper);
		
		newspaper = null;
		newspaper = newspaperRepository.findNewspaperBylibraryItemID(libraryItemID);
		assertNotNull(newspaper);

		assertEquals(edition, newspaper.getEdition());
		assertEquals(libraryItemID, newspaper.getlibraryItemID());
	
	}
	
	@Test
	public void testPersistAndLoadMusicAlbum() {
		
		Long libraryItemID = (long) 30; 
		String Title = "TestMovie";
		int publishedYear = 2021;
		int loanablePeriod = 21; 
		double dailyOverdueFee = 100;
		ItemStatus itemStatus = ItemStatus.Available;
		
		String genre = "Rock";
		String artist = "Test Band";
		int albumLenghtInMinutes = 45;
		int numSongs = 10;
				
		MusicAlbum musicAlbum = new MusicAlbum();
		musicAlbum.setlibraryItemID(libraryItemID);
		musicAlbum.setTitle(Title);
		musicAlbum.setPublishedYear(publishedYear);
		musicAlbum.setLoanablePeriod(loanablePeriod);
		musicAlbum.setDailyOverdueFee(dailyOverdueFee);
		musicAlbum.setItemStatus(itemStatus);
		
		musicAlbum.setGenre(genre);
		musicAlbum.setArtist(artist);
		musicAlbum.setAlbumLengthInMinutes(albumLenghtInMinutes);
		musicAlbum.setNumSongs(numSongs);
		
		
		musicAlbumRepository.save(musicAlbum);
		musicAlbum = null;
		
		musicAlbum = musicAlbumRepository.findMusicAlbumBylibraryItemID(libraryItemID);
		assertNotNull(musicAlbum);

		assertEquals(artist, musicAlbum.getArtist());
		assertEquals(dailyOverdueFee, musicAlbum.getDailyOverdueFee());
	
	}
	
}
