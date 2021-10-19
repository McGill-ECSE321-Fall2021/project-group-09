
package ca.mcgill.ecse321.projectgroup09.models;

import java.util.List;

//JPA tags added

import javax.persistence.*;


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
	private LibraryManagement libraryManagement;
	@ElementCollection
	private List<Loan> loans;

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
	public LibraryManagement getLibraryManagement() {
		return this.libraryManagement;
	}

	
	public void setLibraryManagement(LibraryManagement aLibraryManagement) {
		this.libraryManagement = aLibraryManagement;
	}

	
	public void setLoans(List<Loan> aNewLoan) {
		this.loans = aNewLoan;
	}

}

