package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {
	
	Loan findLoanByLoanID(Long loanID);
}