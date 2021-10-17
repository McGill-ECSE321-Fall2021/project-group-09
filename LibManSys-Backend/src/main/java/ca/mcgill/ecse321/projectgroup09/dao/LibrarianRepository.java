package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.*;

public interface LibrarianRepository extends CrudRepository<Librarian, Long> {
	
	Librarian findLibrarianByLoan(Loan loan);
	Librarian findLibrarianBySchedule(Schedule schedule);
	Librarian findLibrarianbyEmployeeIdNum(Long employeeIDNum);
}