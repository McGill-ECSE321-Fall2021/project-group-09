package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.Movie;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceMovie {


	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired 
	private LibraryItemRepository libraryItemRepository;

	@AfterEach
	public void clearDatabase() {
		movieRepository.deleteAll();
		libraryItemRepository.deleteAll();
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
		movie.setTitle(Title);
		movie.setPublishedYear(publishedYear);
		movie.setLoanablePeriod(loanablePeriod);
		movie.setDailyOverdueFee(dailyOverdueFee);
		movie.setItemStatus(itemStatus);
		
		movie.setDirector(director);
		movie.setRuntime(runtime);
		movie.setGenre(genre);
		
		movieRepository.save(movie);
		libraryItemID = movie.getlibraryItemID();
		
		movie = null; 
		movie = movieRepository.findMovieBylibraryItemID(libraryItemID);
		assertNotNull(movie);

		assertEquals(Title, movie.getTitle());
		assertEquals(libraryItemID, movie.getlibraryItemID());
	
	}
}
