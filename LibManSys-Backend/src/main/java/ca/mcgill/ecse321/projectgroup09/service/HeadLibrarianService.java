package ca.mcgill.ecse321.projectgroup09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.HeadLibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;

@Service
public class HeadLibrarianService {
	
	@Autowired
	private HeadLibrarianRepository headLibrarianRepository;
	
	@Transactional
	public HeadLibrarian createHeadLibrarian(Long managerIDNum) {
		
		if (managerIDNum == null) {
			throw new IllegalArgumentException("Please enter an ID.");
		}
		
		HeadLibrarian headLibrarian = new HeadLibrarian();
		headLibrarian.setmanagerIDNum(managerIDNum);
		
		return headLibrarian;
	}
	
	@Transactional
	public HeadLibrarian deleteHeadLibrarian(Long managerIDNum) {
		HeadLibrarian headLibrarian = headLibrarianRepository.findHeadLibrarianByManagerIDNum(managerIDNum) ;
		headLibrarianRepository.delete(headLibrarian);
		return headLibrarian;
	}
	
	

}
