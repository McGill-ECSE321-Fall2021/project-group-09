
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
	/** Member who is currently reserving this library item. */
	private Member member;
	
	@ElementCollection
	private List<Loan> loans; // Contains info on current and past loans

	/**
	 * Default No Arg Constructor for LibraryItem.
	 */
	public LibraryItem() {
		this.libraryItemID = null;
		// initialize collections
		member = null;
		loans = new ArrayList<Loan>();
	}
	
	/**
	 * Will initialize a new LibraryItem object with an automatically
	 * generated library item ID.
	 * @param aTitle
	 * @param publisherYear
	 */
	public LibraryItem(String aTitle, int aPublishedYear) {
		// initialize collections
		member = null;
		loans = new ArrayList<Loan>();
		this.title = aTitle;
		this.publishedYear = aPublishedYear;
	}
	
	/** 
	 * Don't use this...
	 * Constructor to inialize item with ID
	 * @param aLibraryItemID
	 */
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "libraryItemID", unique = true, nullable = false)
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

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "libraryItem")
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
	
	@Override
	public boolean equals(Object o) {
		if (! (o instanceof LibraryItem)) {
			return false;
		}
		LibraryItem li = (LibraryItem) o;
		// check if IDs NOT equal
		if (!this.getlibraryItemID().equals(li.getlibraryItemID())) {
			return false;
		}
		return true;
	}
	
}

