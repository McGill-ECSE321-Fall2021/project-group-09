package ca.mcgill.ecse321.projectgroup09.service;

import java.util.*;

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
		loan.setLateFees(lateFees); //0?
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
      loanRepository.delete(loan);
      
	}
	
	public Loan readLoan(Long loanId) {
	 Loan loan = loanRepository.findLoanByLoanID(loanId);
    return loan;
	}
	
	//business methods
	
	public int setLateFee() {	
	return 0;
	}	
	
	public int getLateDays() {	
	return 0;
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
	
	//from tutorials
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
