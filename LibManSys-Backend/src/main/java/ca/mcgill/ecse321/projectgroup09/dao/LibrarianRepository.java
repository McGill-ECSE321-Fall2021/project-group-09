package ca.mcgill.ecse321.projectgroup09.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface LibrarianRepository extends CrudRepository<Librarian, Long> {

	Librarian findLibrarianByLoans(Loan loan);
	Librarian findLibrarianBySchedules(Schedule schedule);
	Librarian findLibrarianByEmployeeIDNumber(Long employeeIDNumber);
	Librarian findLibrarianByLibrarianUsername(String librarianUsername);
	List<Librarian> findAll();
}