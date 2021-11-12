package ca.mcgill.ecse321.projectgroup09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.*;
import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;


public class NewspaperService {
	
	@Autowired
	private NewspaperRepository NewspaperRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Transactional
	public Newspaper createNewspaper(String title, int publishedYear, String journalName, String edition, String chiefEditor) {
		if (title == null || journalName == null || edition==null || chiefEditor==null) {
			throw new IllegalArgumentException("Parameters to create a new newspaper must not be null.");
		}
		if (publishedYear<0) {
			throw new IllegalArgumentException("Published Year must be positive.");
		}
		
	Newspaper newNewspaper= new Newspaper ();
	newNewspaper.setTitle(title);
	newNewspaper.setPublishedYear(publishedYear);
	newNewspaper.setJournalName(journalName);
	newNewspaper.setEdition(edition);
	newNewspaper.setChiefEditor(chiefEditor);
	
	
	Newspaper savedNewspaper = NewspaperRepo.save(newNewspaper);
	
	return savedNewspaper;
	
		}
	
	@Transactional
	public Newspaper getNewspaperById(Long libraryItemId) {
		if (libraryItemId == null) {
			throw new IllegalArgumentException("Id must not be null.");
		}
		Newspaper newspaper = NewspaperRepo.findNewspaperBylibraryItemID(libraryItemId);
		return newspaper;
	}
	
	@Transactional
	public List<Newspaper> getAllNewspapers() {
		List<Newspaper> newspapers = (List<Newspaper>) NewspaperRepo.findAll();
		return newspapers;
	}
	
@Transactional
	
	public Newspaper updateNewspaper(Long libraryItemId, String title, Integer publishedYear, Integer loanablePeriod, Double dailyOverdueFee, 
			ItemStatus itemStatus, String journalName, String edition, String chiefEditor) {
	
		if (libraryItemId == null) {
			throw new IllegalArgumentException("Library item ID not be null.");
		}
		
		Newspaper newspaper = NewspaperRepo.findNewspaperBylibraryItemID(libraryItemId);
		if (newspaper == null) {
			throw new IllegalStateException("Could not find a Newspaper with the specified id (id: " + libraryItemId + ").");
		}
		
		if (title != null) {
			newspaper.setTitle(title);
		} 
		if (publishedYear != null) {
			newspaper.setPublishedYear(publishedYear);
		}
		if (loanablePeriod != null) {
			newspaper.setLoanablePeriod(loanablePeriod);
		}
		if (dailyOverdueFee != null) {
			newspaper.setDailyOverdueFee(dailyOverdueFee);
		}
		if (itemStatus != null) {
			newspaper.setItemStatus(itemStatus);
		}
		if (journalName!=null) {
			newspaper.setJournalName(journalName);
		}
		if (edition != null) {
			newspaper.setEdition(edition);
		}
		if(chiefEditor !=null) {
			newspaper.setChiefEditor(chiefEditor);
		}
		
		NewspaperRepo.save(newspaper);
		
		
		return newspaper;
		
	}
@Transactional
public boolean deleteNewspaper(Newspaper newspaperToDelete) {
	if (newspaperToDelete == null) {
		return false;
	} else {
		NewspaperRepo.delete(newspaperToDelete);
		return true;
	}
}	
	
	
@Transactional
public boolean deleteNewspaperById(Long libraryItemId) {
	if (libraryItemId == null) {
		throw new IllegalArgumentException("libraryItemId parameter cannot be null.");
	}
	Newspaper newspaper = NewspaperRepo.findNewspaperBylibraryItemID(libraryItemId);
	return deleteNewspaper(newspaper);
}
