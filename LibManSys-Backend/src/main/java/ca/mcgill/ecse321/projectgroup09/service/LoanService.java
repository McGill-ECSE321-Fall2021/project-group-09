package ca.mcgill.ecse321.projectgroup09.service;

import static ca.mcgill.ecse321.projectgroup09.utils.DateTimeUtil.addDaysToDate;
import static ca.mcgill.ecse321.projectgroup09.utils.MiscUtil.toList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LoanRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;

/**
 * author: Rajaa
 */
@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private LibrarianRepository librarianRepository;
	@Autowired
	private LibraryItemRepository libraryItemRepository;

	/**
	 * Creates a new loan with the specified inputs.
	 * @param borrowedDate
	 * @param loanStatus
	 * @param memberLibCardNumber
	 * @param librarianEmployeeId
	 * @param libraryItemId
	 * @return
	 */
	@Transactional
	public Loan createLoan(Date borrowedDate, LoanStatus loanStatus,
			Long memberLibCardNumber, Long librarianEmployeeId, Long libraryItemId) {
		// get Member, library item and librarian using ID's
		Member member = memberRepository.findMemberByLibCardNumber(memberLibCardNumber);
		LibraryItem libraryItem = libraryItemRepository.findLibraryItemByLibraryItemID(libraryItemId);
		Librarian librarian = librarianRepository.findLibrarianByEmployeeIDNum(librarianEmployeeId);
		// make sure member, library item and librarian exist
		if (member == null) {
			throw new IllegalArgumentException("Member does not exist");
		}
		if (librarian == null) {
			throw new IllegalArgumentException("Librairan does not exist");
		}
		if (libraryItem == null) {
			throw new IllegalArgumentException("Item does not exist");
		}
		// make sure inputs are valid
		if (borrowedDate == null) {
			throw new IllegalArgumentException("No BorrowedDate");
		}
		if (loanStatus == null) {
			throw new IllegalArgumentException("No loan status");
		}
		/* autogenerated
		 * if (loanId == null || loanId <0) { throw new
		 * IllegalArgumentException("No LoanId"); }
		 */
		
		Loan loan = new Loan(
				borrowedDate,
				null, // no return date yet
				0, // late fees start at 0
				false, // fees start as not paid
				loanStatus,
				libraryItem,
				member,
				librarian
				);
		// save new loan and return it
		loanRepository.save(loan);
		return loan;
	}
	
	/**
	 * Return a list of all loans present in the loan repository.
	 * @return
	 */
	@Transactional
	public List<Loan> getAllLoans() {
		return toList(loanRepository.findAll());
	}

	/**
	 * Get a list of loans for a member using the member library card number.
	 * @param libCard Member library card number.
	 * @return List of loans for member.
	 */
	@Transactional
	public List<Loan> getLoansbyMember(Long libCard) {
		if (libCard == null) {
			throw new IllegalArgumentException("Id must not be null.");
		}
		Member member = memberRepository.findMemberByLibCardNumber(libCard);
		if (member == null) {
			throw new IllegalArgumentException("Could not find member with that id in repository.");
		}
		List<Loan> loansByMember = new ArrayList<Loan>();
		for (Loan loan : loanRepository.findLoanByMember(member)) {
			loansByMember.add(loan);
		}
		return loansByMember;
	}
	
	/**
	 * Get a loan by its ID. Throws exception is loan with specified ID is
	 * not found in repository.
	 * @param loanId
	 * @return loan object with ID = 'loadId'
	 */
	public Loan getLoanbyId(Long loanId) {
		if (loanId == null || loanId < 0) {
			throw new IllegalArgumentException("Id must not be null.");
		}
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		if (loan == null) {
			throw new IllegalArgumentException("Cannot find loan with id " + loanId + " in repository.");
		}
		return loan;
	}

	/**
	 * Update loan specified by 'loanId'. Not allowed to set fields to null. If null
	 * is inputted for any param (except loanId, must be non-null), then that field
	 * will retain its current value.
	 * @param borrowedDate
	 * @param returnDate
	 * @param lateFees
	 * @param loanStatus
	 * @param loanId must not be null, id of loan object to update.
	 * @return the updated loan object.
	 */
	@Transactional
	public Loan updateLoan(Date borrowedDate,Date returnDate, Double lateFees, LoanStatus loanStatus,Long loanId){
		if (loanId == null) {
			throw new IllegalArgumentException("No LoanId");
		}
		// try and find loan to update
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		if (loanRepository.findLoanByLoanID(loanId) == null) {
			throw new IllegalArgumentException("Loan does not exist");
		}
		
		// update associations as well? TODO
		//Member member = memberRepository.findMemberByLibCardNumber(memberId);
		//LibraryItem libraryItem = libraryItemRepository.findLibraryItemByLibraryItemID(libraryItemId);
		//Librarian librarian = librarianRepository.findLibrarianByEmployeeIDNum(librarianId);

		// check if BORROWED DATE and/or RETURN DATE are being updated
		if (borrowedDate != null && returnDate != null) {
			// make sure new returnDate is after borrowDate
			if (borrowedDate.after(returnDate)) {
				throw new IllegalArgumentException("Cannot update borrowed date because it is after return date.");
			} else {			
				loan.setBorrowedDate(borrowedDate);
				loan.setReturnDate(returnDate);
			}
		}
		// else if only borrowedDate is being updated
		else if (borrowedDate != null) {
			if (borrowedDate.after(loan.getReturnDate())) {
				throw new IllegalArgumentException("Cannot update borrowed date because it is after return date.");
			} else {
				loan.setBorrowedDate(borrowedDate);	
			}
		}
		// else if only returnDate is being updated
		else if (returnDate != null) {
			if (returnDate.before(loan.getBorrowedDate())) {
				throw new IllegalArgumentException("Cannot update return date because it is before borrowed date.");
			} else {
				loan.setReturnDate(returnDate);
			}
		}
		
		// check if LATE FEES being updated
		if (lateFees != null) {
			if (lateFees < 0) {
				throw new IllegalArgumentException("Late Fees Negative");
			} else {
				loan.setLateFees(lateFees);
			}
		}
		// check if LOAN STATUS being updated
		if (loanStatus != null) {
			loan.setLoanStatus(loanStatus);
		}

		// save updated loan and return it
		loanRepository.save(loan);
		return loan;
	}

	/**
	 * Delete loan by id.
	 * Throws exception if loan specified by loanId does not exist.
	 * @param loanId
	 * @return The loan object if deleted successfully
	 */
	public Loan deleteLoan(Long loanId) {
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		if (loan == null) {
			throw new IllegalArgumentException("Loan does not exist");
		}
		loanRepository.delete(loan);
		return loan;
	}


							// BUSINESS METHODS //

	// not sure if it should be a business method rather than helper method
	private long getLateDays(Date borrowedDate, Date returnDate, Integer loanablePeriod) {
		// maybe I should call loan here, if a user wants to see the lateDays without
		// the fee?
		Date returnedDate = addDaysToDate(borrowedDate, loanablePeriod);
		// LocalDate borrowedDateTrans = convertToLocalDateViaInstant(borrowedDate);
		long diffInMillies = Math.abs(returnedDate.getTime() - borrowedDate.getTime());
		long lateDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		// LocalDate returnDateTrans = convertToLocalDateViaInstant(returnDate);
		// long lateDays = ChronoUnit.DAYS.between(returnDateTrans, borrowedDateTrans);
		return lateDays;
	}

/*	private double calculateLateFee(Date borrowedDate, Date returnDate, Long libraryItemId,
			Integer loanablePeriod) {
		long lateDays = getLateDays(borrowedDate, returnDate, loanablePeriod);
		LibraryItem libraryItem = libraryItemRepository.findLibraryItemByLibraryItemID(libraryItemId);
		double overdueFee = libraryItem.getDailyOverdueFee();
		double lateFees = overdueFee * lateDays;
		return lateFees;
	}*/

	/**
	 * This logic will happen in LibraryItemService.returnLibraryItem() instead.
	 * In theory, returnLibraryitem() could call this method, but services calling other services
	 * is too complicated for now.
	 * 
	 * @deprecated
	 */
	@Transactional
	public Loan addLateFee(Long libraryItemId, Long loanId, Double overdueFee, LoanStatus loanStatus) {
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		Date returnDate = loan.getReturnDate();
		Date borrowedDate = loan.getBorrowedDate();
		Integer loanablePeriod = loan.getLibraryItem().getLoanablePeriod();
		//LibraryItem libraryItem = libraryItemRepository.findLibraryItemByLibraryItemID(libraryItemId);
		//double lateFees = calculateLateFee(borrowedDate, returnDate, libraryItemId, loanablePeriod);
		long lateDays = getLateDays(borrowedDate, returnDate, loanablePeriod);
		//LibraryItem libraryItem = libraryItemRepository.findLibraryItemByLibraryItemID(libraryItemId);
		
		
		//double overdueFee = libraryItem.getDailyOverdueFee();
		
		double lateFees = overdueFee * lateDays;
		//return lateFees;
		//LoanStatus loanStatus = loan.getLoanStatus();
		if (loanStatus == LoanStatus.Overdue) {
			loan.setLateFees(lateFees);
		}
		if (loanStatus != LoanStatus.Overdue) {
			throw new IllegalArgumentException("Loan is not overdue");
		}
		loanRepository.save(loan);
		return loan;
	}

	/**
	 * Don't need this method, just use updateLoan instead.
	 * @deprecated
	 */
	@Transactional
	public Loan changeLateFee(Long loanId, Double newLateFee) {
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		loan.setLateFees(newLateFee);
		loanRepository.save(loan);
		return loan;
	}

	/**
	 * dont need this method, can just use updateLoan()
	 * @deprecated
	 */
	@Transactional
	public Loan changeLoanStatus(Long loanId, Long libraryItemId, LoanStatus loanStatus) {
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		//Date returnDate = loan.getReturnDate();
		//Date borrowedDate = loan.getBorrowedDate();
		//Integer loanablePeriod = loan.getLibraryItem().getLoanablePeriod();
		
		
		if (loanId == null) {
			throw new IllegalArgumentException("Loan does not exist");

		}
		if (libraryItemId == null) {
			throw new IllegalArgumentException("Item does not exist");

		}
		

		loan.setLoanStatus(loanStatus);
		loanRepository.save(loan);
		return loan;		
	}
	
	/**
	 * I think we don't need this method, because the logic to add loan fees to
	 * member amount owed will happen in libraryItemService.returnLibraryItem()
	 * instead. If the loan is overdue when the item is returned using returnLibraryItem()
	 * then that method will add the late fee to the member amount owed.
	 * If we want to get total $ owed by member, use MemberService.getMember().getAmountOwed()
	 *
	 * @deprecated
	 */
	@Transactional
	public double getLoanFeesbyMember(Long memberLib) {
		double fee = 0;
		
		if(memberLib == null) {
			throw new IllegalArgumentException("Id must not be null.");

		}
		Member member = memberRepository.findMemberByLibCardNumber(memberLib);
		for (Loan loan : loanRepository.findLoanByMember(member)) {
			double tempFee = loan.getLateFees();
			fee += tempFee;
		}
		member.setAmountOwed(fee);
		return fee; // maybe void method ie return nothing
	}
	

}
