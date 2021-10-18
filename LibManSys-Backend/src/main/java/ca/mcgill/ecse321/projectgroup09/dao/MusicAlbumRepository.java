//package ca.mcgill.ecse321.projectgroup09.dao;
//
//import org.springframework.data.repository.CrudRepository;
//import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;
//import java.util.List;
//public interface MusicAlbumRepository extends CrudRepository<MusicAlbum, Long> {
//	
//	MusicAlbum findMusicAlbumBylibraryItemID(Long libraryItemID);
//	List<MusicAlbum> findMusicAlbumByGenre(String genre);
//	List<MusicAlbum> findMusicAlbumByArtist(String artist);
//}