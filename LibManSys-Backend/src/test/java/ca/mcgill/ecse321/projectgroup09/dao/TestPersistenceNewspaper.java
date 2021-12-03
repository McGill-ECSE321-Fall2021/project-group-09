package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Newspaper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceNewspaper {

	@Autowired
	private NewspaperRepository newspaperRepository; 
	
	@Autowired
	private LibraryItemRepository libraryItemRepository; 
	
	@AfterEach
	public void clearDatabase() {
		newspaperRepository.deleteAll();
		libraryItemRepository.deleteAll();
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
		newspaper.setTitle(Title);
		newspaper.setPublishedYear(publishedYear);
		newspaper.setLoanablePeriod(loanablePeriod);
		newspaper.setDailyOverdueFee(dailyOverdueFee);
		newspaper.setItemStatus(itemStatus);
		
		newspaper.setJournalName(journalName);
		newspaper.setEdition(edition);
		newspaper.setChiefEditor(chiefEditor);
		
		Newspaper updatedNewspaper = newspaperRepository.save(newspaper);
		libraryItemID = updatedNewspaper.getlibraryItemID();
		
		newspaper = null;
		newspaper = newspaperRepository.findNewspaperBylibraryItemID(libraryItemID);
		assertNotNull(newspaper);

		assertEquals(edition, newspaper.getEdition());
		assertEquals(libraryItemID, newspaper.getlibraryItemID());
	
	}
}
