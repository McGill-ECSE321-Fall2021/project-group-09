package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;

public class MusicAlbumDto extends LibraryItemDto {			
	// Archive attribute
	private String genre;
	private String artist;
	private int numSongs;
	private int albumLengthInMinutes;
	
	
	public MusicAlbumDto() {	
	
	}
	
	public MusicAlbumDto(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee,
			ItemStatus aItemStatus, Member aMember, List<Loan> aLoans, String aGenre, String anArtist, int numSongs, int albumLengthInMinutes) {
		super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod,aDailyOverdueFee, aItemStatus, aMember, aLoans, LibraryItemType.MUSIC_ALBUM);
		this.genre = aGenre;
		this.artist=anArtist;
		this.numSongs=numSongs;
		this.albumLengthInMinutes=albumLengthInMinutes;
	}
	
	public static MusicAlbumDto convertToDto(MusicAlbum musicAlbum) {
		if (musicAlbum == null) {
			throw new IllegalArgumentException("Music Album parameter cannot be null.");
		}
		
		MusicAlbumDto musicAlbumDto = new MusicAlbumDto(
				musicAlbum.getlibraryItemID(),
				musicAlbum.getTitle(),
				musicAlbum.getPublishedYear(),
				musicAlbum.getLoanablePeriod(),
				musicAlbum.getDailyOverdueFee(),
				musicAlbum.getItemStatus(),
				musicAlbum.getMember(),
				musicAlbum.getLoans(),
				musicAlbum.getGenre(),
				musicAlbum.getArtist(),
				musicAlbum.getNumSongs(),
				musicAlbum.getAlbumLengthInMinutes()
				
		);
		return musicAlbumDto;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre=genre;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist=artist;
	}
	
	public int getNumSongs() {
		return numSongs;
	}
	
	public void setNumSongs(int numSongs) {
		this.numSongs=numSongs;
	}
	
	public int getAlbumLengthInMinutes() {
		return albumLengthInMinutes;
	}
	
	public void setAlbumLengthInMinutes(int albumLengthInMinutes) {
		this.albumLengthInMinutes=albumLengthInMinutes;
	}
				
}
