package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.*;

public interface LibrarianRepository extends CrudRepository<Librarian, Long> {
	
	Librarian findLibrarianByLoans(Loan loan);
	Librarian findLibrarianBySchedules(Schedule schedule);
	Librarian findLibrarianByEmployeeIDNum(Long employeeIDNum);
}