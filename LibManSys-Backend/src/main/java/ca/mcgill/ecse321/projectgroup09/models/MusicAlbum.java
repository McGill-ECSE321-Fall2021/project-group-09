package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;


@Entity
public class MusicAlbum extends LibraryItem {


	// MusicAlbum Attributes
	private String genre;
	private String artist;
	private int albumLengthInMinutes;
	private int numSongs;

	@Override
	public void setlibraryItemID(Long alibraryItemID) {
		super.setlibraryItemID(alibraryItemID);
	}

	@Override
	public void setTitle(String aTitle) {
		super.setTitle(aTitle);
	}

	@Override
	public void setPublishedYear(int aPublishedYear) {
		super.setPublishedYear(aPublishedYear);
	}

	
	@Override
	public void setLoanablePeriod(int aLoanablePeriod) {
		super.setLoanablePeriod(aLoanablePeriod);
	}

	
	@Override
	public void setDailyOverdueFee(double aDailyOverdueFee) {
		super.setDailyOverdueFee(aDailyOverdueFee);
	}

	
	@Override
	public void setItemStatus(ItemStatus anItemStatus) {
		super.setItemStatus(anItemStatus);
	}
	
	@Override
	public Long getlibraryItemID() {
		return super.getlibraryItemID();
	}

	
	@Override
	public String getTitle() {
		return super.getTitle();
	}

	@Override
	public int getPublishedYear() {
		return super.getPublishedYear();
	}

	/*public MusicAlbum(Integer aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod,
			double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement, String aGenre,
			String aArtist, int aAlbumLengthInMinutes, int aNumSongs) {
		super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod, aDailyOverdueFee, aItemStatus,
				aLibraryManagement);
		genre = aGenre;
		artist = aArtist;
		albumLengthInMinutes = aAlbumLengthInMinutes;
		numSongs = aNumSongs;
	}*/
	
	@Override
	public int getLoanablePeriod() {
		return super.getLoanablePeriod();
	}

	@Override
	public double getDailyOverdueFee() {
		return super.getDailyOverdueFee();
	}

	@Override
	public ItemStatus getItemStatus() {
		return super.getItemStatus();
	}
	
	
	public void setGenre(String aGenre) {
		this.genre = aGenre;
	}

	public void setArtist(String anArtist) {
		this.artist = anArtist;
	}

	public void setAlbumLengthInMinutes(int anAlbumLengthInMinutes) {
		this.albumLengthInMinutes = anAlbumLengthInMinutes;
	}

	public void setNumSongs(int aNumSongs) {
		this.numSongs = aNumSongs;
	}

	public String getGenre() {
		return this.genre;
	}
	

	public String getArtist() {
		return this.artist;
	}

	public int getAlbumLengthInMinutes() {
		return this.albumLengthInMinutes;
	}

	public int getNumSongs() {
		return this.numSongs;
	}

}