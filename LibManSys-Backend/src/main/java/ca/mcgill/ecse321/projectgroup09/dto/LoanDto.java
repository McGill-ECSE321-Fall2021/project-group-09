package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.Date;

import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;

public class LoanDto {
	// Loan Attributes
	private Date borrowedDate;
	private Date returnDate;
	private double lateFees;
	private LoanStatus loanStatus;
	private Long loanID;
	// Loan Associations
	private LibraryItemDto libraryItem;
	private MemberDto member;
	private LibrarianDto librarian;
	
	/**
	 * Default No-Arg Constructor
	 */
	public LoanDto() {
		
	}
	
	/**
	 * All-argument constructor. Returns a fully populated LoanDto object.
	 * TODO implement this constructor to take all attributes as input, and
	 * set all attributes of new LoanDto object. See BookDto for example.
	 * @param aBorrowedData
	 */
	public LoanDto(Date aBorrowedData) {
		
	}
	
	/**
	 * TODO implement
	 * @param loan
	 * @return
	 */
	public static LoanDto convertToDto(Loan loan) {
		return new LoanDto();
	}
}
