package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.Archive;
import ca.mcgill.ecse321.projectgroup09.models.Book;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Movie;
import ca.mcgill.ecse321.projectgroup09.models.MusicAlbum;
import ca.mcgill.ecse321.projectgroup09.models.Newspaper;

public class LibraryItemDto {
	
	public enum LibraryItemType {
		ARCHIVE, BOOK, MOVIE, MUSIC_ALBUM, NEWSPAPER
	}
	
	// LibraryItem attributes
	private Long libraryItemID;
	private String title;
	private int publishedYear;
	private int loanablePeriod;
	private double dailyOverdueFee;
	private ItemStatus itemStatus;

	// LibraryItem associations
	/** Lib card number of reserving member. */
	private Long reservingMemberLibCardNumber;
	private List<Long> loans;
	
	// type of library item
	private LibraryItemType type;
	
	/**
	 * No-arg constructor
	 */
	LibraryItemDto() {
		
	}
	
	/**
	 * Create a new library item dto object.
	 * @param aLibraryItemId
	 * @param aTitle
	 * @param aPublishedYear
	 * @param aLoanablePeriod
	 * @param aDailyOverdueFee
	 * @param aItemStatus
	 * @param aMember
	 * @param aLoans
	 */
	public LibraryItemDto(Long aLibraryItemId, String aTitle, int aPublishedYear,
			int aLoanablePeriod, double aDailyOverdueFee, ItemStatus aItemStatus,
			Member aMember, List<Loan> aLoans, LibraryItemType aType) {
		this.libraryItemID = aLibraryItemId;
		this.title = aTitle;
		this.publishedYear = aPublishedYear;
		this.loanablePeriod = aLoanablePeriod;
		this.dailyOverdueFee = aDailyOverdueFee;
		this.itemStatus = aItemStatus;
		// convert Member to dto if exists
		if (aMember != null) {
			this.reservingMemberLibCardNumber = aMember.getLibCardNumber();
		} else {
			// else if member is null, just set to null in dto
			this.reservingMemberLibCardNumber = null;
		}
		// convert collection
		List<Long> aLoanIds = aLoans.stream().map(loan -> loan.getLoanID()).collect(Collectors.toList());
		this.loans = aLoanIds;
		
		// subclass type
		this.type = aType;
	}
	
	/**
	 * determine type of library item and return matching dto type
	 * @param libraryItem
	 * @return
	 */
	public static LibraryItemDto convertToDto(LibraryItem libraryItem) {
		LibraryItemDto lidto = null;
		if (libraryItem instanceof Archive) {
			lidto =  (LibraryItemDto) ArchiveDto.convertToDto((Archive) libraryItem);
		} else if (libraryItem instanceof Book) {
			lidto = (LibraryItemDto) BookDto.convertToDto((Book) libraryItem);
		} else if (libraryItem instanceof Movie) {
			lidto = (LibraryItemDto) MovieDto.convertToDto((Movie) libraryItem);
		} else if (libraryItem instanceof MusicAlbum) {
			lidto = (LibraryItemDto) MusicAlbumDto.convertToDto((MusicAlbum) libraryItem);
		} else if (libraryItem instanceof Newspaper) {
			lidto = (LibraryItemDto) NewspaperDto.convertToDto((Newspaper) libraryItem);
		} else {
			throw new IllegalStateException("invalid type of (or probably just null) library item.");
		}
		return lidto;
	}
	
	/**
	 * Convert list of library items to list of library item dtos.
	 * @param libraryItems
	 * @return
	 */
	public static List<LibraryItemDto> convertToDto(List<? extends LibraryItem> libraryItems) {
		return libraryItems.stream().map(li -> LibraryItemDto.convertToDto(li)).collect(Collectors.toList());
	}

	/**
	 * @return the libraryItemID
	 */
	public Long getLibraryItemID() {
		return libraryItemID;
	}

	/**
	 * @param libraryItemID the libraryItemID to set
	 */
	public void setLibraryItemID(Long libraryItemID) {
		this.libraryItemID = libraryItemID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the publishedYear
	 */
	public int getPublishedYear() {
		return publishedYear;
	}

	/**
	 * @param publishedYear the publishedYear to set
	 */
	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	/**
	 * @return the loanablePeriod
	 */
	public int getLoanablePeriod() {
		return loanablePeriod;
	}

	/**
	 * @param loanablePeriod the loanablePeriod to set
	 */
	public void setLoanablePeriod(int loanablePeriod) {
		this.loanablePeriod = loanablePeriod;
	}

	/**
	 * @return the dailyOverdueFee
	 */
	public double getDailyOverdueFee() {
		return dailyOverdueFee;
	}

	/**
	 * @param dailyOverdueFee the dailyOverdueFee to set
	 */
	public void setDailyOverdueFee(double dailyOverdueFee) {
		this.dailyOverdueFee = dailyOverdueFee;
	}

	/**
	 * @return the itemStatus
	 */
	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	/**
	 * @param itemStatus the itemStatus to set
	 */
	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}

	/**
	 * @return the type
	 */
	public LibraryItemType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(LibraryItemType type) {
		this.type = type;
	}

	/**
	 * @return the member
	 */
	public Long getReservingMemberLibCardNumber() {
		return reservingMemberLibCardNumber;
	}

	/**
	 * @param member the member to set
	 */
	public void setReservingMemberLibCardNumber(Long member) {
		this.reservingMemberLibCardNumber = member;
	}

	/**
	 * @return the loans
	 */
	public List<Long> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<Long> loans) {
		this.loans = loans;
	}
}
