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
	//private Long librarianId; already stored as part of librarianDto
	//private Long memberId; already stored as part of MemberDto
	// Loan Associations
	private LibraryItemDto libraryItem;
	private MemberDto member;
	private LibrarianDto librarian;
	
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
			LibraryItemDto lidto = null;
			if (libraryItem != null) {
				lidto = LibraryItemDto.convertToDto(libraryItem);
			}
			this.libraryItem = lidto;
			// member
			MemberDto mdto = null;
			if (member!= null) {
				mdto = MemberDto.convertToDto(member);
			}
			this.member = mdto;
			// librarian
			LibrarianDto ldto = null;
			if (librarian != null) {
				ldto = LibrarianDto.convertToDto(librarian);
			}
			this.librarian = ldto;
			
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



	public LibraryItemDto getLibraryItem() {
		return libraryItem;
	}

	public void setLibraryItem(LibraryItemDto libraryItem) {
		this.libraryItem = libraryItem;
	}

	public MemberDto getMember() {
		return member;
	}

	public void setMember(MemberDto member) {
		this.member = member;
	}

	public LibrarianDto getLibrarian() {
		return librarian;
	}

	public void setLibrarian(LibrarianDto librarian) {
		this.librarian = librarian;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
}
