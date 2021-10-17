package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;

public interface LibrarianRepository extends CrudRepository<Librarian, Long> {
	
	Librarian findLibrarianbyEmployeeIdNum(Long employeeIDNum);
}