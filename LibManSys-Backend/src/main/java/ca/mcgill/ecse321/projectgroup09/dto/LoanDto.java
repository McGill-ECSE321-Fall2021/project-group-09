package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.Date;

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
			
			// safe to assume Loan must always have valid LibraryItem, Member and Librarian
			LibraryItemDto lidto = LibraryItemDto.convertToDto(libraryItem);
			this.libraryItem = lidto;
			MemberDto mdto = MemberDto.convertToDto(member);
			this.member = mdto;
			LibrarianDto ldto = LibrarianDto.convertToDto(librarian);
			this.librarian = ldto;
		}
	
	/**
	 * TODO implement
	 * @param loan
	 * @return
	 */
	public static LoanDto convertToDto(Loan loan) {
		return new LoanDto();
	}
	
	/*public List <LoanDto> convertToDtoList(List<Loan> loans) {

		return new List <LoanDto()>;
	}*/

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
