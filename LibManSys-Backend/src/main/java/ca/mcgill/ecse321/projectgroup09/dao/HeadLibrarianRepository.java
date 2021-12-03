package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;
import java.util.List;
/**
 * 
 * @author Zarif Ashraf
 *
 */
public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, Long> {
	
	List<HeadLibrarian> findHeadLibrarianByLibrary(Library library);
	HeadLibrarian findHeadLibrarianByManagerIDNum(Long managerIDNum);
	HeadLibrarian findHeadLibrarianBySchedules(Schedule schedule);
//	HeadLibrarian findByAccountID(Long hlManagerId);
	List<HeadLibrarian> findAll();
}