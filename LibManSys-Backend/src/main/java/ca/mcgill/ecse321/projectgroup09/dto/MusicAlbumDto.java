package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;

public class MusicAlbumDto{
	
			// LibraryItem attributes
			private Long libraryItemID;
			private String title;
			private int publishedYear;
			private int loanablePeriod;
			private double dailyOverdueFee;
			private ItemStatus itemStatus;
			
			// LibraryItem associations
			private MemberDto member;
			private List<LoanDto> loans;
			
			// Archive attribute
			private String genre;
			private String artist;
			private int numSongs;
			private int albumLengthInMinutes;
			

			public MusicAlbumDto() {	
			
			}
			
			public MusicAlbumDto(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee,
					ItemStatus aItemStatus, Member aMember, List<Loan> aLoans, String aGenre, String anArtist, int numSongs, int albumLengthInMinutes) {
				
				this.libraryItemID = aLibraryItemId;
				this.title = aTitle;
				this.publishedYear = aPublishedYear;
				this.loanablePeriod = aLoanablePeriod;
				this.dailyOverdueFee = aDailyOverdueFee;
				this.itemStatus = aItemStatus;

				if (member != null) {
					MemberDto aMemberDto = MemberDto.convertToDto(aMember);
					this.member = aMemberDto;
				} else {

					this.member = null;
				}

				List<LoanDto> aLoansDto = aLoans.stream().map(loan -> LoanDto.convertToDto(loan)).collect(Collectors.toList());
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
			
			public Long getLibraryItemID() {
				return libraryItemID;
			}
			
			public void setLibraryItemID(Long libraryItemID) {
				this.libraryItemID = libraryItemID;
			}
			
			public String getTitle() {
				return title;
			}
			
			public void setTitle(String title) {
				this.title = title;
			}
			
			public int getPublishedYear() {
				return publishedYear;
			}
			
			public void setPublishedYear(int publishedYear) {
				this.publishedYear = publishedYear;
			}
			
			public int getLoanablePeriod() {
				return loanablePeriod;
			}
			
			public void setLoanablePeriod(int loanablePeriod) {
				this.loanablePeriod = loanablePeriod;
			}
			
			public double getDailyOverdueFee() {
				return dailyOverdueFee;
			}
			
			public void setDailyOverdueFee(double dailyOverdueFee) {
				this.dailyOverdueFee = dailyOverdueFee;
			}
			
			public ItemStatus getItemStatus() {
				return itemStatus;
			}
			
			public void setItemStatus(ItemStatus itemStatus) {
				this.itemStatus = itemStatus;
			}
			
			public MemberDto getMember() {
				return member;
			}
			
			public void setMember(MemberDto member) {
				this.member = member;
			}
			
			public List<LoanDto> getLoans() {
				return loans;
			}
			
			public void setLoans(List<LoanDto> loans) {
				this.loans = loans;
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
