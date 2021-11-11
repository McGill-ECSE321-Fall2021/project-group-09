package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import java.util.List;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface LoanRepository extends CrudRepository<Loan, Long> {
	
	Loan findLoanByLoanID(Long loanID);
	List<Loan> findLoanByMember(Member member);
	List<Loan> findLoanByLibrarian(Librarian librarian);
	List<Loan> findLoanByLibraryItem(LibraryItem libraryItem);
}