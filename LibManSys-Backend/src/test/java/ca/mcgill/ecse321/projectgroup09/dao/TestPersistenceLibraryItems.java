package ca.mcgill.ecse321.projectgroup09.dao;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * Persistence testing not applicable for abstract classes.
 * 
 * @author snehas
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceLibraryItems {

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
	
	
	
	
}
