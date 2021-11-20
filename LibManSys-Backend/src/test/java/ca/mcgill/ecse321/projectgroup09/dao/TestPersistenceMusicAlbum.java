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
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceMusicAlbum {

	@Autowired
	private MusicAlbumRepository musicAlbumRepository; 
	
	@Autowired 
	private LibraryItemRepository libraryItemRepository;
	
	@AfterEach
	public void clearDatabase() {
		musicAlbumRepository.deleteAll();
		libraryItemRepository.deleteAll();
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
		musicAlbum.setTitle(Title);
		musicAlbum.setPublishedYear(publishedYear);
		musicAlbum.setLoanablePeriod(loanablePeriod);
		musicAlbum.setDailyOverdueFee(dailyOverdueFee);
		musicAlbum.setItemStatus(itemStatus);
		
		musicAlbum.setGenre(genre);
		musicAlbum.setArtist(artist);
		musicAlbum.setAlbumLengthInMinutes(albumLenghtInMinutes);
		musicAlbum.setNumSongs(numSongs);
		
		
		MusicAlbum updatedMusicAlbum = musicAlbumRepository.save(musicAlbum);
		libraryItemID = updatedMusicAlbum.getlibraryItemID();
		
		musicAlbum = null;
		
		musicAlbum = musicAlbumRepository.findMusicAlbumBylibraryItemID(libraryItemID);
		assertNotNull(musicAlbum);

		assertEquals(artist, musicAlbum.getArtist());
		assertEquals(dailyOverdueFee, musicAlbum.getDailyOverdueFee());
	
	}
}
