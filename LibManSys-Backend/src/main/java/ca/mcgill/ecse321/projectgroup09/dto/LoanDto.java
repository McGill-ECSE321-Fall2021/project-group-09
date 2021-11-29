package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;

public class LoanDto {
	// Loan Attributes
	private Date borrowedDate;
	private Date returnDate;
	private double lateFees;
	private LoanStatus loanStatus;
	private Long loanId;

	// Loan Associations
	private Long libraryItemID;
	/** Lib card number of associated member. */
	private Long memberLibCardNumber;
	/** Employee Id number of associated librarian */
	private Long librarianEmployeeID;
	
	/**
	 * Default No-Arg Constructor
	 */
	public LoanDto() {
		
	}
	
	  public LoanDto(Date borrowedDate, Date returnDate, Double lateFees, LoanStatus loanStatus, Long loanId,
				LibraryItem libraryItem, Member member, Librarian librarian) {
		  this.borrowedDate = borrowedDate;
			this.returnDate = returnDate;
			this.lateFees = lateFees;
			this.loanStatus = loanStatus;
			this.loanId = loanId;
			// Check if associations present before converting
			Long lidto = null;
			if (libraryItem != null) {
				lidto = libraryItem.getlibraryItemID();
			}
			this.libraryItemID = lidto;
			// member
			Long mdto = null;
			if (member!= null) {
				mdto = member.getLibCardNumber();
			}
			this.memberLibCardNumber = mdto;
			// librarian
			Long ldto = null;
			if (librarian != null) {
				ldto = librarian.getemployeeIDNumber();
			}
			this.librarianEmployeeID = ldto;
			
			// if lidto is null, just means loan has no library item associated with 
			//if (lidto == null) {
			//	throw new IllegalStateException("invalid type of library item in LOANDTO");
			//}
		}
	
	/**
	 * Use cascade = false if converting a Loan to a DTO as part of another DTO conversion. This
	 * will avoid circular references.
	 * 
	 * Basically, if calling converToDto from another converToDto method, use casacde = false,
	 * otherwise if calling convertToDto from controller method use cascade = true
	 * 
	 * @param loan
	 * @param cascade If true, converts associations to DTOs, else if false, sets associations to null.
	 * @return
	 */
	public static LoanDto convertToDto(Loan loan, boolean cascade) {
		LoanDto loanDto;
		/*
		// remove association, convert, then add back to avoid circular references
		loan.getLibraryItem().getLoans().remove(loan);
		loan.getMember().getLoans().remove(loan);
		loan.getLibrarian().getLoans().remove(loan);
		*/
		if (cascade) {
			loanDto = new LoanDto(
					loan.getBorrowedDate(),
					loan.getReturnDate(),
					loan.getLateFees(),
					loan.getLoanStatus(),
					loan.getLoanID(),
					loan.getLibraryItem(),
					loan.getMember(),
					loan.getLibrarian()
					);
		} else {
			loanDto = new LoanDto(
					loan.getBorrowedDate(),
					loan.getReturnDate(),
					loan.getLateFees(),
					loan.getLoanStatus(),
					loan.getLoanID(),
					null,
					null,
					null
					);
		}
		
		
		/*
		loan.getLibraryItem().getLoans().add(loan);
		loan.getMember().getLoans().add(loan);
		loan.getLibrarian().getLoans().add(loan);
		*/
		return loanDto;
	}
	
	/**
	 * Convert a list of Loan objects to a list of loan dtos.
	 * @param loans
	 * @return
	 */
	public static List<LoanDto> convertToDtos(List<Loan> loans, boolean cascade) {
		List<LoanDto> loanDtos = loans.stream().map(l -> LoanDto.convertToDto(l, cascade)).collect(Collectors.toList());
		return loanDtos;
	}

	public Date getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(Date borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public double getLateFees() {
		return lateFees;
	}

	public void setLateFees(double lateFees) {
		this.lateFees = lateFees;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	/**
	 * @return the libraryItem
	 */
	public Long getLibraryItemID() {
		return libraryItemID;
	}

	/**
	 * @param libraryItem the libraryItem to set
	 */
	public void setLibraryItemID(Long libraryItem) {
		this.libraryItemID = libraryItem;
	}

	/**
	 * @return the member
	 */
	public Long getMemberLibCardNumber() {
		return memberLibCardNumber;
	}

	/**
	 * @param member the member to set
	 */
	public void setMemberLibCardNumber(Long member) {
		this.memberLibCardNumber = member;
	}

	/**
	 * @return the librarian
	 */
	public Long getLibrarianEmployeeID() {
		return librarianEmployeeID;
	}

	/**
	 * @param librarian the librarian to set
	 */
	public void setLibrarianEmployeeID(Long librarian) {
		this.librarianEmployeeID = librarian;
	}
}
