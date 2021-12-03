package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.Archive;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceArchive {

	@Autowired
	private ArchiveRepository  archiveRepository;
	
	@Autowired 
	private LibraryItemRepository libraryItemRepository;
	
	@AfterEach
	public void clearDatabase() {
		archiveRepository.deleteAll();
		libraryItemRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadArchive() {
		
		Long libraryItemID; //= (long) 1; 
		String Title = "TestMovie";
		int publishedYear = 2021;
		int loanablePeriod = 21; 
		double dailyOverdueFee = 100;
		ItemStatus itemStatus = ItemStatus.Available;
		
		String insitution = "Academic";
				
		Archive archive = new Archive();
		//archive.setlibraryItemID(libraryItemID);
		archive.setTitle(Title);
		archive.setPublishedYear(publishedYear);
		archive.setLoanablePeriod(loanablePeriod);
		archive.setDailyOverdueFee(dailyOverdueFee);
		archive.setItemStatus(itemStatus);
		
		archive.setInstitution(insitution);
		
		archiveRepository.save(archive);
		
		libraryItemID = archive.getlibraryItemID();
		
		archive = null; 
		archive = archiveRepository.findArchiveBylibraryItemID(libraryItemID);
		assertNotNull(archive);

		assertEquals(Title, archive.getTitle());
		assertEquals(libraryItemID, archive.getlibraryItemID());
	}
}
