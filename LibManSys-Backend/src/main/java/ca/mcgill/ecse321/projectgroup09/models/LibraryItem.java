
package ca.mcgill.ecse321.projectgroup09.models;

import java.util.ArrayList;
import java.util.List;

//JPA tags added

import javax.persistence.*;

/**
 * Library Item Model Class.
 */
@Table (name = "libraryItem")
@Entity
public abstract class LibraryItem {
	// Code for automattically generating library item IDs
	private static Long nextLibraryItemId = 1L;
	/**
	 * Gets the next unique library item ID to assign to a library item.
	 * @return {@code long} a unique library item ID.
	 */
	private static Long getNextLibraryItemId() {
		Long nextId = nextLibraryItemId;
		nextLibraryItemId++;
		return nextId;
	}
	
	public enum ItemStatus {
		Available, Reserved, CheckedOut, LibraryOnly
	}

	// LibraryItem Attributes
	private Long libraryItemID;
	private String title;
	private int publishedYear;
	private int loanablePeriod;
	private double dailyOverdueFee;
	private ItemStatus itemStatus;

	// LibraryItem Associations
	private Member member; // Member who is currently reserving this library item.
	
	@ElementCollection
	private List<Loan> loans; // Contains info on current and past loans

	/**
	 * Default No Arg Constructor for LibraryItem.
	 * Will initialize a new LibraryItem object with an automatically
	 * generated library item ID.
	 */
	public LibraryItem() {
		// initialize library item to next ID automatically
		this.libraryItemID = getNextLibraryItemId();
		// initialize collections
		member = null;
		loans = new ArrayList<Loan>();
	}
	
	// Constructor to inialize item with ID
	public LibraryItem(Long aLibraryItemID) {
		this.libraryItemID = aLibraryItemID;
	}
	
	public void setlibraryItemID(Long alibraryItemID) {
		this.libraryItemID = alibraryItemID;
	}

	public void setTitle(String aTitle) {
		this.title = aTitle;
	}

	public void setPublishedYear(int aPublishedYear) {
		this.publishedYear = aPublishedYear;
	}

	public void setLoanablePeriod(int aLoanablePeriod) {
		this.loanablePeriod = aLoanablePeriod;
	}

	public void setDailyOverdueFee(double aDailyOverdueFee) {
		this.dailyOverdueFee = aDailyOverdueFee; 
	}

	public void setItemStatus(ItemStatus anItemStatus) {
		this.itemStatus = anItemStatus;
	}
	
	@Id
	public Long getlibraryItemID() {
		return this.libraryItemID;
	}

	public String getTitle() {
		return this.title;
	}

	public int getPublishedYear() {
		return this.publishedYear;
	}

	public int getLoanablePeriod() {
		return this.loanablePeriod;
	}

	public double getDailyOverdueFee() {
		return this.dailyOverdueFee;
	}

	public ItemStatus getItemStatus() {
		return this.itemStatus;
	}

	@OneToMany(cascade = {CascadeType.ALL})
	public List<Loan> getLoans() {
		return this.loans;
	}

	@ManyToOne
	public Member getMember() {
		return member;
	}
	

	public void setLoans(List<Loan> aNewLoan) {
		this.loans = aNewLoan;
	}
	
	public void setMember(Member aMember) {
		this.member = aMember;
	}
	
}

