package ca.mcgill.ecse321.projectgroup09.service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.*;
import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;

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
	private BookRepository bookRepository;
	@Autowired
	private LibrarianRepository librarianRepository;
	@Autowired
	private LibraryItemRepository libraryItemRepository;

	@Transactional
	public Loan createLoan(Date borrowedDate,  Long loanId,
			Long memberId, Long librarianId, Long libraryItemId) {
		

		Loan loan = new Loan();
		Member member = memberRepository.findMemberByLibCardNumber(memberId);
		LibraryItem libraryItem = libraryItemRepository.findLibraryItemByLibraryItemID(libraryItemId);
		Librarian librarian = librarianRepository.findLibrarianByEmployeeIDNum(librarianId);

		/*if (loanRepository.findLoanByMember(member) == null) {
			throw new IllegalArgumentException("Member does not exist");
		}*/
		
		if (memberId == null) {
			throw new IllegalArgumentException("Member does not exist");
		}
		if (librarianId == null) {
			throw new IllegalArgumentException("Librairan does not exist");
		}
		if (libraryItemId == null) {
			throw new IllegalArgumentException("Item does not exist");
		}
		if (borrowedDate == null) {
			throw new IllegalArgumentException("No BorrowedDate");
		}
		if (loanId == null || loanId <0) {
			throw new IllegalArgumentException("No LoanId");
		}
		
	/*	if (loanRepository.findLoanByLibraryItem(libraryItem) == null) {
			throw new IllegalArgumentException("Library Item does not exist");
		}*/
		loan.setBorrowedDate(borrowedDate);
		//loan.setReturnDate(returnDate);
		loan.setLateFees(0); //0?
		loan.setLoanStatus(LoanStatus.Active);
		loan.setloanID(loanId); // random number? or catch if no id put random
		loan.setMember(member);
		loan.setLibrarian(librarian);
		loan.setLibraryItem(libraryItem);
		loanRepository.save(loan);
		return loan;
	}

	@Transactional
	public Loan updateLoan(Date borrowedDate,Date returnDate, Double lateFees, LoanStatus loanStatus,Long loanId){
		

		Loan loan = loanRepository.findLoanByLoanID(loanId);
		//Member member = memberRepository.findMemberByLibCardNumber(memberId);
		//LibraryItem libraryItem = libraryItemRepository.findLibraryItemByLibraryItemID(libraryItemId);
		//Librarian librarian = librarianRepository.findLibrarianByEmployeeIDNum(librarianId);

		if (loanRepository.findLoanByLoanID(loanId) == null) {
			throw new IllegalArgumentException("Loan does not exist");
		}
		
		
		if (borrowedDate == null) {
		loan.setBorrowedDate(borrowedDate);
		}
		
		if (returnDate.before(borrowedDate)) {
			throw new IllegalArgumentException("Return date is before Borrowed Date");
			}
		if (lateFees<0) {
			throw new IllegalArgumentException("Late Fees Negative");
			}
		if (loanId == null) {
			throw new IllegalArgumentException("No LoanId");
		}
		
		loan.setBorrowedDate(borrowedDate);
		loan.setReturnDate(returnDate);
		loan.setLateFees(lateFees);
		loan.setLoanStatus(loanStatus);
		// loan.setloanID(loanId);
		loanRepository.save(loan);
		return loan;
	}

	public boolean deleteLoan(Long loanId) {
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		if (loan == null) {
			throw new IllegalArgumentException("Loan does not exist");
		}
		loanRepository.delete(loan);
		return true;

	}


							// BUSINESS METHODS //

	// not sure if it should be a business method rather than helper method
	private long getLateDays(Date borrowedDate, Date returnDate, Integer loanablePeriod) {
		// maybe I should call loan here, if a user wants to see the lateDays without
		// the fee?
		Date returnedDate = addDate(borrowedDate, loanablePeriod);
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

	@Transactional
	public Loan addLateFee(Long libraryItemId, Long loanId, Double overdueFee, LoanStatus loanStatus) {
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		Date returnDate = loan.getReturnDate();
		Date borrowedDate = loan.getBorrowedDate();
		Integer loanablePeriod = loan.getLibraryItem().getLoanablePeriod();
		//LibraryItem libraryItem = libraryItemRepository.findLibraryItemByLibraryItemID(libraryItemId);
		//double lateFees = calculateLateFee(borrowedDate, returnDate, libraryItemId, loanablePeriod);
		long lateDays = getLateDays(borrowedDate, returnDate, loanablePeriod);
		LibraryItem libraryItem = libraryItemRepository.findLibraryItemByLibraryItemID(libraryItemId);
		
		
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

	@Transactional
	public Loan changeLateFee(Long loanId, Double newLateFee) {
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		loan.setLateFees(newLateFee);
		loanRepository.save(loan);
		return loan;
	}

	@Transactional
	public Loan changeLoanStatus(Long loanId, Long libraryItemId, LoanStatus loanStatus) {
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		Date returnDate = loan.getReturnDate();
		Date borrowedDate = loan.getBorrowedDate();
		Integer loanablePeriod = loan.getLibraryItem().getLoanablePeriod();
		
		
		if (loanId == null) {
			throw new IllegalArgumentException("Loan does not exist");

		}
		if (libraryItemId == null) {
			throw new IllegalArgumentException("Item does not exist");

		}
		

		loan.setLoanStatus(loanStatus);
		loanRepository.save(loan);
		return loan;
	/*	if (calculateLateFee(borrowedDate, returnDate, libraryItemId, loanablePeriod) > 0) {
			loan.setLoanStatus(LoanStatus.Overdue);
		}*/
	//	if (calculateLateFee(borrowedDate, returnDate, libraryItem, loanablePeriod) == 0 // && renewal false */) {
		//	loan.setLoanStatus(LoanStatus.Active);
	//	}
		
		/*if (loanablePeriod == null) {
			throw new IllegalArgumentException("No loanable  Date");

		}*/
		// if renewal method (in libraryItem) set as renewal

		// if returned method in libraryItem set as closed
		
		
		
	}

	@Transactional
	public List<Loan> getAllLoans() {
		return toList(loanRepository.findAll());
	}

	@Transactional
	public List<Loan> getLoansbyMember(Long libCard) {
		Member member = memberRepository.findMemberByLibCardNumber(libCard);
		List<Loan> loansByMember = new ArrayList<>();
		for (Loan loan : loanRepository.findLoanByMember(member)) {
			loansByMember.add(loan);
		}
		if (libCard ==null) {
			throw new IllegalArgumentException("Id must not be null.");

		}
		return loansByMember;
	}
	
	public Loan getLoanbyId(Long loanId) {
		if (loanId == null || loanId <0) {
			throw new IllegalArgumentException("Id must not be null.");
		}
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		return loan;
	}
	

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

	/*
	 * public LocalDate convertToLocalDateViaInstant(Date dateToConvert) { return
	 * dateToConvert.toInstant() .atZone(ZoneId.systemDefault()) .toLocalDate(); }
	 */

							// HELPER METHODS //

	private Date addDate(Date borrowedDate, Integer loanablePeriod) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, loanablePeriod);
		Date modifiedDate = cal.getTime();
		return modifiedDate;
	}

	// from tutorials
	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
