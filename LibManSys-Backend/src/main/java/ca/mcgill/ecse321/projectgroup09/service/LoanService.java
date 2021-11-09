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
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;

/**
 * author: Rajaa
 */
@Service
public class LoanService {
	
	@Autowired
	private LoanRepository  loanRepository;
	
	@Transactional
	public Loan createLoan(Date borrowedDate, Date returnDate, Double lateFees, LoanStatus loanStatus,
			Long loanId,Member member, Librarian librarian, LibraryItem libraryItem) {
        if (loanRepository.findLoanByMember(member)== null
               ){
            throw new IllegalArgumentException ("Member does not exist");
        }
        if (loanRepository.findLoanByLibraryItem(libraryItem)== null
                ){
             throw new IllegalArgumentException ("Library Item does not exist");
         }
        
		Loan loan = new Loan();
		loan.setBorrowedDate(borrowedDate);
		loan.setReturnDate(borrowedDate);
		loan.setLateFees(lateFees); //0? or called fro
		loan.setLoanStatus(loanStatus);
		loan.setloanID(loanId); //random number? or catch if no id put random
		loan.setMember(member);
		loan.setLibrarian(librarian);
		loan.setLibraryItem(libraryItem);
		loanRepository.save(loan);
		return loan;
	}
	
	@Transactional
	public Loan updateLoan(Date borrowedDate, Date returnDate, Double lateFees, LoanStatus loanStatus,
			Long loanId,Member member, Librarian librarian, LibraryItem libraryItem) {
		 if (loanRepository.findLoanByLoanID(loanId)== null
	               ){
	            throw new IllegalArgumentException ("Loan does not exist");
	        }
        if (loanRepository.findLoanByMember(member)== null
               ){
            throw new IllegalArgumentException ("Member does not exist");
        }
        if (loanRepository.findLoanByLibraryItem(libraryItem)== null
                ){
             throw new IllegalArgumentException ("Library Item does not exist");
         }
        
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		loan.setBorrowedDate(borrowedDate);
		loan.setReturnDate(borrowedDate);
		loan.setLateFees(0); 
		loan.setLoanStatus(loanStatus);
		//loan.setloanID(loanId);
		loan.setMember(member);
		loan.setLibrarian(librarian);
		loan.setLibraryItem(libraryItem);
		loanRepository.save(loan);
		return loan;
	}
	
	public void deleteLoan(Long loanId) {
      Loan loan = loanRepository.findLoanByLoanID(loanId);
      if (loan ==null) {
 	     throw new IllegalArgumentException ("Loan does not exist"); 
 	      }
      loanRepository.delete(loan);
      
	}
	
	public Loan readLoan(Long loanId) {
	 Loan loan = loanRepository.findLoanByLoanID(loanId);
	 
    return loan;
	}
	
	//business methods

	public long getLateDays(Date borrowedDate, Date returnDate) {	
		
		//calculate scheduled return date
		// borrowed date + loanable period
		
		//LocalDate borrowedDateTrans = convertToLocalDateViaInstant(borrowedDate);
		long diffInMillies = Math.abs(returnDate.getTime() - borrowedDate.getTime());
		long lateDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		//LocalDate returnDateTrans = convertToLocalDateViaInstant(returnDate);
		//long lateDays = ChronoUnit.DAYS.between(returnDateTrans, borrowedDateTrans);
	return lateDays;
	}	
	
	public double getLateFee(Date borrowedDate, Date returnDate, LibraryItem libraryItem) {	
		long lateDays = getLateDays(borrowedDate, returnDate);
		double overdueFee = libraryItem.getDailyOverdueFee();
		double lateFees = overdueFee * lateDays;
		return lateFees;	
	}	
	public void setLateFee(Date borrowedDate, Date returnDate,Long lateDays, LibraryItem libraryItem,Long loanId) {	
		double lateFees = getLateFee(borrowedDate, returnDate, libraryItem);
		Loan loan = loanRepository.findLoanByLoanID(loanId);
		loan.setLateFees(lateFees);
	}	
	

	
	@Transactional
	public List<Loan> getAllLoans(){
		return toList(loanRepository.findAll());
	}

	@Transactional
	public List<Loan> getLoansbyMember(Member member) {
		List<Loan> loansByMember = new ArrayList<>();
		for (Loan loan :loanRepository.findLoanByMember(member)) {
			loansByMember.add(loan);
		}
		return loansByMember;
	}
	
	@Transactional
	public double getLoanFeesbyMember(Member member) {
		double fee = 0;
		for (Loan loan :loanRepository.findLoanByMember(member)) {
			double tempFee = loan.getLateFees();
			fee += tempFee;
		}
		member.setAmountOwed(fee);
		return fee; //maybe void method ie return nothing
	}
	/*
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}*/
	
	//from tutorials
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
