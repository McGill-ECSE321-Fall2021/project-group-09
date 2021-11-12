package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;

public class LoanDto {
	// Loan Attributes
	private Date borrowedDate;
	private Date returnDate;
	private double lateFees;
	private LoanStatus loanStatus;
	private Long loanId;
	private Long librarianId;
	private Long memberId;
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
				Long memberId, Long librarianId, Long libraryItemId) {
  
	        
		  this.borrowedDate = borrowedDate;
			this.returnDate = returnDate;
			this.lateFees = lateFees;
			this.loanStatus = loanStatus;
			this.librarianId = librarianId;
			this.memberId = memberId;
			this.loanId = loanId;
			
		}
	
	
	
	/**
	 * All-argument constructor. Returns a fully populated LoanDto object.
	 * TODO implement this constructor to take all attributes as input, and
	 * set all attributes of new LoanDto object. See BookDto for example.
	 * @param aBorrowedData
	 */
	/*public LoanDto(Date aBorrowedData) {
		
	}*/
	
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

	public Long getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(Long librarianId) {
		this.librarianId = librarianId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}
