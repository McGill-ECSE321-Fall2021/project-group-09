package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;

public interface MusicAlbumRepository extends CrudRepository<MusicAlbum, Long> {
	
	MusicAlbum findMusicAlbumBylibraryItemID(Long libraryItemID);
}