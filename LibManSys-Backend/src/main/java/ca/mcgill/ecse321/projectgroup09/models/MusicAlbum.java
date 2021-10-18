package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class MusicAlbum extends LibraryItem {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// MusicAlbum Attributes
	private String genre;
	private String artist;
	private int albumLengthInMinutes;
	private int numSongs;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public MusicAlbum(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod,
			double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement, String aGenre,
			String aArtist, int aAlbumLengthInMinutes, int aNumSongs) {
		super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod, aDailyOverdueFee, aItemStatus,
				aLibraryManagement);
		genre = aGenre;
		artist = aArtist;
		albumLengthInMinutes = aAlbumLengthInMinutes;
		numSongs = aNumSongs;
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setGenre(String aGenre) {
		boolean wasSet = false;
		genre = aGenre;
		wasSet = true;
		return wasSet;
	}

	public boolean setArtist(String aArtist) {
		boolean wasSet = false;
		artist = aArtist;
		wasSet = true;
		return wasSet;
	}

	public boolean setAlbumLengthInMinutes(int aAlbumLengthInMinutes) {
		boolean wasSet = false;
		albumLengthInMinutes = aAlbumLengthInMinutes;
		wasSet = true;
		return wasSet;
	}

	public boolean setNumSongs(int aNumSongs) {
		boolean wasSet = false;
		numSongs = aNumSongs;
		wasSet = true;
		return wasSet;
	}

	public String getGenre() {
		return genre;
	}
	
	@Id
	public String getArtist() {
		return artist;
	}

	public int getAlbumLengthInMinutes() {
		return albumLengthInMinutes;
	}

	public int getNumSongs() {
		return numSongs;
	}

	public void delete() {
		super.delete();
	}

	public String toString() {
		return super.toString() + "[" + "genre" + ":" + getGenre() + "," + "artist" + ":" + getArtist() + ","
				+ "albumLengthInMinutes" + ":" + getAlbumLengthInMinutes() + "," + "numSongs" + ":" + getNumSongs()
				+ "]";
	}
}
