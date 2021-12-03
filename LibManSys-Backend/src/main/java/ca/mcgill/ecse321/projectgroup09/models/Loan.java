package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class Loan {

	public enum LoanStatus {
		Active, Renewed, Overdue, Closed
	}

	// Loan Attributes
	private Date borrowedDate;
	private Date returnDate;
	private double lateFees;
	private boolean feesPaid;
	private LoanStatus loanStatus;
	private Long loanID;

	// Loan Associations
	private LibraryItem libraryItem;
	private Member member;
	private Librarian librarian;

	public Loan() {}
	
	public Loan(Date aBorrowedDate, Date aReturnDate, double aLateFees,
			boolean aFeesPaid, LoanStatus aLoanStatus,
			LibraryItem aLibraryItem, Member aMember, Librarian aLibrarian) {
		this.borrowedDate = aBorrowedDate;
		this.returnDate = aReturnDate;
		this.lateFees = aLateFees;
		this.feesPaid = aFeesPaid;
		this.loanStatus = aLoanStatus;
		
		this.libraryItem = aLibraryItem;
		this.member = aMember;
		this.librarian = aLibrarian;
	}

	public void setBorrowedDate(Date aBorrowedDate) {
		this.borrowedDate = aBorrowedDate;
	}

	public void setReturnDate(Date aReturnDate) {
		this.returnDate = aReturnDate;
	}

	public void setLateFees(double aLateFees) {
		this.lateFees = aLateFees;
	}

	public void setLoanStatus(LoanStatus aLoanStatus) {
		this.loanStatus = aLoanStatus;
	}

	/**
	 * @deprecated
	 * @param aloanID
	 */
	public void setloanID(Long aloanID) {
		this.loanID = aloanID; 
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getLoanID() {
		return loanID;
	}
	
	public Date getBorrowedDate() {
		return this.borrowedDate;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public double getLateFees() {
		return this.lateFees;
	}

	public LoanStatus getLoanStatus() {
		return this.loanStatus;
	}

	
	@ManyToOne(targetEntity = LibraryItem.class)
	//@JoinColumn(name = "libraryItem_id", insertable=false, updatable=false)
	public LibraryItem getLibraryItem() {
		return libraryItem;
	}

	
	@ManyToOne(targetEntity = Member.class)
	//@JoinColumn(name = "account_id", insertable=false, updatable=false)
	public Member getMember() {
		return member;
	}

	
	@ManyToOne(targetEntity = Librarian.class)
	//@JoinColumn(name = "account_id", insertable=false, updatable=false)
	public Librarian getLibrarian() {
		return librarian;
	}

	public void setLibraryItem(LibraryItem aNewLibraryItem) {
		this.libraryItem = aNewLibraryItem;

	}

	public void setMember(Member aMember) {
		this.member = aMember;
		
	}

	public void setLibrarian(Librarian aLibrarian) {
		this.librarian = aLibrarian;
	}

	/**
	 * @return the areFeesPaid
	 */
	public boolean isAreFeesPaid() {
		return feesPaid;
	}

	/**
	 * @param areFeesPaid the areFeesPaid to set
	 */
	public void setAreFeesPaid(boolean areFeesPaid) {
		this.feesPaid = areFeesPaid;
	}

	
}
