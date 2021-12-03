package ca.mcgill.ecse321.projectgroup09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;

@Service
public class HeadLibrarianService {
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	@Transactional
	public HeadLibrarian createHeadLibrarian(String fullName, String email, String password, String username) {
		List<HeadLibrarian> hls = headLibrarianRepository.findAll();
		if (hls != null && hls.size() > 0) {
			throw new IllegalStateException("Head librarian already exists, cannot create second head librarian.");
		}
		if (fullName == null || email == null || password == null || username == null) {
			throw new IllegalArgumentException("Arguments cannot be null when creating a new librarian.");
		}		
		HeadLibrarian headLibrarian = new HeadLibrarian(fullName, email, password, username, null, null, null);
		headLibrarianRepository.save(headLibrarian);
		return headLibrarian;
	}
	
	@Transactional
	public HeadLibrarian getHeadLibrarian() {
		List<HeadLibrarian> hl = headLibrarianRepository.findAll();
		if (hl == null || hl.size() < 1) {
			throw new IllegalStateException("No head librarian!!!");
		}
		if (hl.size() > 1) {
			throw new IllegalStateException("Can only be on head librarian!!!! (1) employeeID num: " + hl.get(1).getemployeeIDNumber());
		}
		return hl.get(0);
	}
	
	@Transactional
	public HeadLibrarian deleteHeadLibrarian(Long managerIDNum) {
		HeadLibrarian headLibrarian = headLibrarianRepository.findHeadLibrarianByManagerIDNum(managerIDNum);
		if (headLibrarian == null) {
			throw new IllegalArgumentException("Head librarian does not exist");
		}
		headLibrarianRepository.delete(headLibrarian);
		return headLibrarian;
	}
	
	

}
