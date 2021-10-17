package ca.mcgill.ecse321.projectgroup09.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;

public interface HeadLibrarianRepository extends CrudRepository<HeadLibrarian, Long> {
	
	HeadLibrarian findHeadLibrarianbyManagerIDNum(Long managerIDNum);
}