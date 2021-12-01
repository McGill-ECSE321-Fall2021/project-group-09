package ca.mcgill.ecse321.projectgroup09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.MusicAlbumRepository;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;

@Service
public class MusicAlbumService {
	// Repos // 
		@Autowired
		private MusicAlbumRepository musicAlbumRepo;
		
		@Transactional
		public MusicAlbum createMusicAlbum(String title, int publishedYear,
				String genre, String artist, int numSongs, int albumLengthInMinutes) {
			if (title == null || genre == null || artist==null) {
				throw new IllegalArgumentException("Parameters to create a new music album must not be null.");
			}
			if (publishedYear<0 || numSongs<0 || albumLengthInMinutes<0) {
				throw new IllegalArgumentException("Paramater must be positive.");
			}
			
		MusicAlbum newMusicAlbum= new MusicAlbum ();
		newMusicAlbum.setTitle(title);
		newMusicAlbum.setPublishedYear(publishedYear);
		newMusicAlbum.setGenre(genre);
		newMusicAlbum.setArtist(artist);
		newMusicAlbum.setNumSongs(numSongs);
		newMusicAlbum.setAlbumLengthInMinutes(albumLengthInMinutes);
		
		
		MusicAlbum savedMusicAlbum = musicAlbumRepo.save(newMusicAlbum);
		
		return savedMusicAlbum;
		
			}	
		
		@Transactional
		public MusicAlbum getMusicAlbumById(Long libraryItemId) {
			if (libraryItemId == null) {
				throw new IllegalArgumentException("Id must not be null.");
			}
			MusicAlbum musicAlbum = musicAlbumRepo.findMusicAlbumBylibraryItemID(libraryItemId);
			return musicAlbum;
		}
		
		@Transactional
		public List<MusicAlbum> getAllMusicAlbums() {
			List<MusicAlbum> musicAlbums = (List<MusicAlbum>) musicAlbumRepo.findAll();
			return musicAlbums;
		}
		
		@Transactional
		public List<MusicAlbum> getMusicAlbumsByTitle(String title) {
			return musicAlbumRepo.findMusicAlbumByTitle(title);
		}
		
		@Transactional
		public List<MusicAlbum> getMusicAlbumsByPublishedYear(int year) {
			return musicAlbumRepo.findMusicAlbumByPublishedYear(year);
		}
		
		@Transactional
		public List<MusicAlbum> getMusicAlbumsbyGenre(String genre) {
			return musicAlbumRepo.findMusicAlbumByGenre(genre);
		}
		
		@Transactional
		public List<MusicAlbum> getMusicAlbumsbyArtist(String genre) {
			return musicAlbumRepo.findMusicAlbumByArtist(genre);
		}
		
		@Transactional
		public List<MusicAlbum> getMusicAlbumsbyDirector(String artist) {
			return musicAlbumRepo.findMusicAlbumByArtist(artist);
		}
		
		@Transactional
		public List<MusicAlbum> getMusicAlbumByAlbumLength(int albumLength) {
			return musicAlbumRepo.findMusicAlbumByAlbumLengthInMinutes(albumLength);
		}
		
		@Transactional
		public List<MusicAlbum> getMusicAlbumByNumSongs(int numSongs) {
			return musicAlbumRepo.findMusicAlbumByNumSongs(numSongs);
		}
		
		@Transactional
		public MusicAlbum updateMusicAlbum(Long libraryItemId, String title, Integer publishedYear, 
				Integer loanablePeriod, Double dailyOverdueFee, ItemStatus itemStatus,
				String genre, String artist, Integer numSongs, Integer albumLengthInMinutes) {
		
			if (libraryItemId == null) {
				throw new IllegalArgumentException("Library item ID not be null.");
			}
			
			MusicAlbum musicAlbum = musicAlbumRepo.findMusicAlbumBylibraryItemID(libraryItemId);
			if (musicAlbum == null) {
				throw new IllegalStateException("Could not find a music Album with the specified id (id: " + libraryItemId + ").");
			}
			
			if (title != null) {
				musicAlbum.setTitle(title);
			} 
			if (publishedYear != null) {
				musicAlbum.setPublishedYear(publishedYear);
			}
			if (loanablePeriod != null) {
				musicAlbum.setLoanablePeriod(loanablePeriod);
			}
			if (dailyOverdueFee != null) {
				musicAlbum.setDailyOverdueFee(dailyOverdueFee);
			}
			if (itemStatus != null) {
				musicAlbum.setItemStatus(itemStatus);
			}
			if (genre !=null) {
				musicAlbum.setGenre(genre);
			}
			if (artist !=null) {
				musicAlbum.setArtist(artist);
			}
			if (numSongs!=null) {
				musicAlbum.setNumSongs(numSongs);
			}
			if (albumLengthInMinutes != null) {
				musicAlbum.setAlbumLengthInMinutes(albumLengthInMinutes);
			}
			
			musicAlbumRepo.save(musicAlbum);
			
			return musicAlbum;
			
		}
		
		@Transactional
		public boolean deleteMusicAlbum(MusicAlbum musicAlbumToDelete) {
			if (musicAlbumToDelete == null) {
				return false;
			} else {
				musicAlbumRepo.delete(musicAlbumToDelete);
				return true;
			}
		}	
		
		@Transactional
		public boolean deleteMusicAlbumById(Long libraryItemId) {
			if (libraryItemId == null) {
				throw new IllegalArgumentException("libraryItemId parameter cannot be null.");
			}
			MusicAlbum musicAlbum = musicAlbumRepo.findMusicAlbumBylibraryItemID(libraryItemId);
			return deleteMusicAlbum(musicAlbum);
		}	
		
}
