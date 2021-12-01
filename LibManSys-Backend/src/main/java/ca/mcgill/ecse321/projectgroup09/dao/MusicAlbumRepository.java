package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;
import java.util.List;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface MusicAlbumRepository extends CrudRepository<MusicAlbum, Long> {
	
	MusicAlbum findMusicAlbumBylibraryItemID(Long libraryItemID);
	List<MusicAlbum> findMusicAlbumByTitle(String title);
	List<MusicAlbum> findMusicAlbumByPublishedYear(int publishedYear);
	List<MusicAlbum> findMusicAlbumByGenre(String genre);
	List<MusicAlbum> findMusicAlbumByArtist(String artist);
	List<MusicAlbum> findMusicAlbumByAlbumLengthInMinutes(int albumLengthInMinutes);
	List<MusicAlbum> findMusicAlbumByNumSongs(int numSongs);
}