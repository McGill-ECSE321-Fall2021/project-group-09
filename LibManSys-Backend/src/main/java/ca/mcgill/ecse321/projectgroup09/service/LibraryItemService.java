package ca.mcgill.ecse321.projectgroup09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;

@Service
public class LibraryItemService {
	
	@Autowired
	LibraryItemRepository libraryItemRepo;
	
	private Long nextId;
	
	
	/**
	 * Constructor
	 */
	public LibraryItemService() {
		nextId = 1L;
	}
	
	/**
	 * 
	 * @return
	 */
	@Transactional
	public Long getNextLibraryItemId() {
		Long nextId = this.nextId;
		this.nextId++;
		return nextId;
	}
	
}
