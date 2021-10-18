package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Loan {

	

	public enum LoanStatus {
		Active, Renewed, Overdue, Closed
	}


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

	public void setloanID(Long aloanID) {
		this.loanID = aloanID; 
	}

	@Id
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

	
	@ManyToOne
	public LibraryItem getLibraryItem() {
		return libraryItem;
	}

	
	@ManyToOne
	public Member getMember() {
		return member;
	}

	
	@ManyToOne 
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

	
}
