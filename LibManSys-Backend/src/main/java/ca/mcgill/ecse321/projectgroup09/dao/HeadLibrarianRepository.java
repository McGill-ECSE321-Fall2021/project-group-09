package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.*;
import java.util.List;

public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, Long> {
	
	List<HeadLibrarian> findHeadLibrarianByLibrary(Library library);
	HeadLibrarian findHeadLibrarianByManagerIDNum(Long managerIDNum);
	HeadLibrarian findHeadLibrarianBySchedules(Schedule schedule);
}