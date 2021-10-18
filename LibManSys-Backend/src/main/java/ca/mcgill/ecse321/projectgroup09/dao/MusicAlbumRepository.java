package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;
import java.util.List;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface MusicAlbumRepository extends CrudRepository<MusicAlbum, Integer> {
	
	MusicAlbum findMusicAlbumBylibraryItemID(Integer libraryItemID);
	List<MusicAlbum> findMusicAlbumByGenre(String genre);
	List<MusicAlbum> findMusicAlbumByArtist(String artist);
}