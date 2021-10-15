package ca.mcgill.ecse321.projectgroup09.models;

//Sneha 

import javax.persistence.*;
import java.util.Set;

@Table (name = "libraryItem")
@Entity
public abstract class LibraryItem {

	// ------------------------
	// ENUMERATIONS
	// ------------------------

	public enum ItemStatus {
		Available, Reserved, CheckedOut, LibraryOnly
	}

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// LibraryItem Attributes
	
	private int libraryItemId;
	private String title;
	private int publishedYear;
	private int loanablePeriod;
	private double dailyOverdueFee;
	private ItemStatus itemStatus;

	// LibraryItem Associations
	private LibraryManagement libraryManagement;
	private Loan loan;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public LibraryItem(int aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod,
			double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement) {
		libraryItemId = aLibraryItemId;
		title = aTitle;
		publishedYear = aPublishedYear;
		loanablePeriod = aLoanablePeriod;
		dailyOverdueFee = aDailyOverdueFee;
		itemStatus = aItemStatus;
		boolean didAddLibraryManagement = setLibraryManagement(aLibraryManagement);
		if (!didAddLibraryManagement) {
			throw new RuntimeException(
					"Unable to create libraryItem due to libraryManagement. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setLibraryItemId(int aLibraryItemId) {
		boolean wasSet = false;
		libraryItemId = aLibraryItemId;
		wasSet = true;
		return wasSet;
	}

	public boolean setTitle(String aTitle) {
		boolean wasSet = false;
		title = aTitle;
		wasSet = true;
		return wasSet;
	}

	public boolean setPublishedYear(int aPublishedYear) {
		boolean wasSet = false;
		publishedYear = aPublishedYear;
		wasSet = true;
		return wasSet;
	}

	public boolean setLoanablePeriod(int aLoanablePeriod) {
		boolean wasSet = false;
		loanablePeriod = aLoanablePeriod;
		wasSet = true;
		return wasSet;
	}

	public boolean setDailyOverdueFee(double aDailyOverdueFee) {
		boolean wasSet = false;
		dailyOverdueFee = aDailyOverdueFee;
		wasSet = true;
		return wasSet;
	}

	public boolean setItemStatus(ItemStatus aItemStatus) {
		boolean wasSet = false;
		itemStatus = aItemStatus;
		wasSet = true;
		return wasSet;
	}
	
	@Id
	public int getLibraryItemId() {
		return libraryItemId;
	}

	public String getTitle() {
		return title;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	public int getLoanablePeriod() {
		return loanablePeriod;
	}

	public double getDailyOverdueFee() {
		return dailyOverdueFee;
	}

	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	/* Code from template association_GetOne */
	public LibraryManagement getLibraryManagement() {
		return libraryManagement;
	}

	/* Code from template association_GetOne */
	public Loan getLoan() {
		return loan;
	}

	public boolean hasLoan() {
		boolean has = loan != null;
		return has;
	}

	/* Code from template association_SetOneToMany */
	public boolean setLibraryManagement(LibraryManagement aLibraryManagement) {
		boolean wasSet = false;
		if (aLibraryManagement == null) {
			return wasSet;
		}

		LibraryManagement existingLibraryManagement = libraryManagement;
		libraryManagement = aLibraryManagement;
		if (existingLibraryManagement != null && !existingLibraryManagement.equals(aLibraryManagement)) {
			existingLibraryManagement.removeLibraryItem(this);
		}
		libraryManagement.addLibraryItem(this);
		wasSet = true;
		return wasSet;
	}

	/* Code from template association_SetOptionalOneToOne */
	public boolean setLoan(Loan aNewLoan) {
		boolean wasSet = false;
		if (loan != null && !loan.equals(aNewLoan) && equals(loan.getLibraryItem())) {
			// Unable to setLoan, as existing loan would become an orphan
			return wasSet;
		}

		loan = aNewLoan;
		LibraryItem anOldLibraryItem = aNewLoan != null ? aNewLoan.getLibraryItem() : null;

		if (!this.equals(anOldLibraryItem)) {
			if (anOldLibraryItem != null) {
				anOldLibraryItem.loan = null;
			}
			if (loan != null) {
				loan.setLibraryItem(this);
			}
		}
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		LibraryManagement placeholderLibraryManagement = libraryManagement;
		this.libraryManagement = null;
		if (placeholderLibraryManagement != null) {
			placeholderLibraryManagement.removeLibraryItem(this);
		}
		Loan existingLoan = loan;
		loan = null;
		if (existingLoan != null) {
			existingLoan.delete();
		}
	}

	public String toString() {
		return super.toString() + "[" + "libraryItemId" + ":" + getLibraryItemId() + "," + "title" + ":" + getTitle()
				+ "," + "publishedYear" + ":" + getPublishedYear() + "," + "loanablePeriod" + ":" + getLoanablePeriod()
				+ "," + "dailyOverdueFee" + ":" + getDailyOverdueFee() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "itemStatus" + "="
				+ (getItemStatus() != null
						? !getItemStatus().equals(this) ? getItemStatus().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "libraryManagement = "
				+ (getLibraryManagement() != null ? Integer.toHexString(System.identityHashCode(getLibraryManagement()))
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "loan = "
				+ (getLoan() != null ? Integer.toHexString(System.identityHashCode(getLoan())) : "null");
	}
}
