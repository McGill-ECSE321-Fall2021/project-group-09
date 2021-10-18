package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.*;

import java.util.Date;
// Omar

@Entity
public class Loan {

	// ------------------------
	// ENUMERATIONS
	// ------------------------

	public enum LoanStatus {
		Active, Renewed, Overdue, Closed
	}

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Loan Attributes
	private Date borrowedDate;
	private Date returnDate;
	private double lateFees;
	private LoanStatus loanStatus;
	private Long loanID;

	// Loan Associations
	private LibraryItem libraryItem;
	private Member member;
	private Librarian librarian;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Loan(Date aBorrowedDate, Date aReturnDate, double aLateFees, LoanStatus aLoanStatus,
			LibraryItem aLibraryItem, Member aMember, Librarian aLibrarian) {
		borrowedDate = aBorrowedDate;
		returnDate = aReturnDate;
		lateFees = aLateFees;
		loanStatus = aLoanStatus;
		boolean didAddLibraryItem = setLibraryItem(aLibraryItem);
		if (!didAddLibraryItem) {
			throw new RuntimeException(
					"Unable to create loan due to libraryItem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		boolean didAddMember = setMember(aMember);
		if (!didAddMember) {
			throw new RuntimeException(
					"Unable to create loan due to member. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		boolean didAddLibrarian = setLibrarian(aLibrarian);
		if (!didAddLibrarian) {
			throw new RuntimeException(
					"Unable to create loan due to librarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setBorrowedDate(Date aBorrowedDate) {
		boolean wasSet = false;
		borrowedDate = aBorrowedDate;
		wasSet = true;
		return wasSet;
	}

	public boolean setReturnDate(Date aReturnDate) {
		boolean wasSet = false;
		returnDate = aReturnDate;
		wasSet = true;
		return wasSet;
	}

	public boolean setLateFees(double aLateFees) {
		boolean wasSet = false;
		lateFees = aLateFees;
		wasSet = true;
		return wasSet;
	}

	public boolean setLoanStatus(LoanStatus aLoanStatus) {
		boolean wasSet = false;
		loanStatus = aLoanStatus;
		wasSet = true;
		return wasSet;
	}
	
	public boolean setloanID(Long aloanID) {
		boolean wasSet = false;
		loanID = aloanID;
		wasSet = true;
		return wasSet;
	}

	@Id
	public Long getLoanID() {
		return loanID;
	}
	
	public Date getBorrowedDate() {
		return borrowedDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public double getLateFees() {
		return lateFees;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	/* Code from template association_GetOne */
	@ManyToOne
	public LibraryItem getLibraryItem() {
		return libraryItem;
	}

	/* Code from template association_GetOne */
	@ManyToOne
	public Member getMember() {
		return member;
	}

	/* Code from template association_GetOne */
	@ManyToOne 
	public Librarian getLibrarian() {
		return librarian;
	}

	/* Code from template association_SetOneToOptionalOne */
	public boolean setLibraryItem(LibraryItem aNewLibraryItem) {
		boolean wasSet = false;
		if (aNewLibraryItem == null) {
			// Unable to setLibraryItem to null, as loan must always be associated to a
			// libraryItem
			return wasSet;
		}

		Loan existingLoan = aNewLibraryItem.getLoan();
		if (existingLoan != null && !equals(existingLoan)) {
			// Unable to setLibraryItem, the current libraryItem already has a loan, which
			// would be orphaned if it were re-assigned
			return wasSet;
		}

		LibraryItem anOldLibraryItem = libraryItem;
		libraryItem = aNewLibraryItem;
		libraryItem.setLoan(this);

		if (anOldLibraryItem != null) {
			anOldLibraryItem.setLoan(null);
		}
		wasSet = true;
		return wasSet;
	}

	/* Code from template association_SetOneToAtMostN */
	public boolean setMember(Member aMember) {
		boolean wasSet = false;
		// Must provide member to loan
		if (aMember == null) {
			return wasSet;
		}

		// member already at maximum (10)
		if (aMember.numberOfLoans() >= Member.maximumNumberOfLoans()) {
			return wasSet;
		}

		Member existingMember = member;
		member = aMember;
		if (existingMember != null && !existingMember.equals(aMember)) {
			boolean didRemove = existingMember.removeLoan(this);
			if (!didRemove) {
				member = existingMember;
				return wasSet;
			}
		}
		member.addLoan(this);
		wasSet = true;
		return wasSet;
	}

	/* Code from template association_SetOneToMany */
	public boolean setLibrarian(Librarian aLibrarian) {
		boolean wasSet = false;
		if (aLibrarian == null) {
			return wasSet;
		}

		Librarian existingLibrarian = librarian;
		librarian = aLibrarian;
		if (existingLibrarian != null && !existingLibrarian.equals(aLibrarian)) {
			existingLibrarian.removeLoan(this);
		}
		librarian.addLoan(this);
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		LibraryItem existingLibraryItem = libraryItem;
		libraryItem = null;
		if (existingLibraryItem != null) {
			existingLibraryItem.setLoan(null);
		}
		Member placeholderMember = member;
		this.member = null;
		if (placeholderMember != null) {
			placeholderMember.removeLoan(this);
		}
		Librarian placeholderLibrarian = librarian;
		this.librarian = null;
		if (placeholderLibrarian != null) {
			placeholderLibrarian.removeLoan(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "lateFees" + ":" + getLateFees() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "borrowedDate" + "="
				+ (getBorrowedDate() != null
						? !getBorrowedDate().equals(this) ? getBorrowedDate().toString().replaceAll("  ", "    ")
								: "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "returnDate" + "="
				+ (getReturnDate() != null
						? !getReturnDate().equals(this) ? getReturnDate().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "loanStatus" + "="
				+ (getLoanStatus() != null
						? !getLoanStatus().equals(this) ? getLoanStatus().toString().replaceAll("  ", "    ") : "this"
						: "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "libraryItem = "
				+ (getLibraryItem() != null ? Integer.toHexString(System.identityHashCode(getLibraryItem())) : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "member = "
				+ (getMember() != null ? Integer.toHexString(System.identityHashCode(getMember())) : "null")
				+ System.getProperties().getProperty("line.separator") + "  " + "librarian = "
				+ (getLibrarian() != null ? Integer.toHexString(System.identityHashCode(getLibrarian())) : "null");
	}
}
