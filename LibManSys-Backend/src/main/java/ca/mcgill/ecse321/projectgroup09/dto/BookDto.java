package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;

/**
 * Data Transfer Object for Book model class.
 * 
 * Need to store all information of Book class in this class,
 * including superclass information (LibraryItem).
 */
public class BookDto {
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
	
	// Book attributes
	private String author;
	private String publisher;
	private String ISBN;
	private int numPages;
	
	/**
	 * Default constructor.
	 */
	public BookDto() {
		
	}
	
	/**
	 * Initialize a new BookDto object with the specified attributes.
	 * @param aLibraryItemId
	 * @param aTitle
	 * @param aPublishedYear
	 * @param aLoanablePeriod
	 * @param aDailyOverdueFee
	 * @param aItemStatus
	 * @param aMember
	 * @param aLoans
	 * @param aAuthor
	 * @param aPublisher
	 * @param aISBN
	 * @param aNumPages
	 */
	public BookDto(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee,
			ItemStatus aItemStatus, MemberDto aMember, List<LoanDto> aLoans, String aAuthor, String aPublisher, String aISBN, int aNumPages) {
		this.libraryItemID = aLibraryItemId;
		this.title = aTitle;
		this.publishedYear = aPublishedYear;
		this.loanablePeriod = aLoanablePeriod;
		this.dailyOverdueFee = aDailyOverdueFee;
		this.itemStatus = aItemStatus;
		this.member = aMember;
		this.loans = aLoans;
		this.author = aAuthor;
		this.publisher = aPublisher;
		this.ISBN = aISBN;
		this.numPages = aNumPages;
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
	 * @return the member
	 */
	public MemberDto getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(MemberDto member) {
		this.member = member;
	}

	/**
	 * @return the loans
	 */
	public List<LoanDto> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<LoanDto> loans) {
		this.loans = loans;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * @return the numPages
	 */
	public int getNumPages() {
		return numPages;
	}

	/**
	 * @param numPages the numPages to set
	 */
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}

}
