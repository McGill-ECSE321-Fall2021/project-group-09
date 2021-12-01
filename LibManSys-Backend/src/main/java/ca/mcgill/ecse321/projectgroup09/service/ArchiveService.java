package ca.mcgill.ecse321.projectgroup09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.ArchiveRepository;
import ca.mcgill.ecse321.projectgroup09.models.Archive;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;

@Service
public class ArchiveService {
	// Repos // 
	@Autowired
	private ArchiveRepository archiveRepo;
	
	// Methods //
	
	/**
	 * Create and return a new Archive object. Saves new object to Archive and LibraryItem Crud repositories.
	 * Automatically allocates a new libraryItemId for object.
	 * 
	 * @param title {@code String}
	 * @param publishedYear {@code int} 
	 * @param institution {@code String}
	 */
	
	@Transactional
	public Archive createArchive(String title, int publishedYear, String institution) {
		if (title == null || institution == null) {
			throw new IllegalArgumentException("Parameters to create a new book must not be null.");
		}
		if (publishedYear<0) {
			throw new IllegalArgumentException("Published Year must be positive.");
		}
		
	Archive newArchive= new Archive ();
	newArchive.setTitle(title);
	newArchive.setPublishedYear(publishedYear);
	newArchive.setInstitution(institution);
	
	Archive savedArchive = archiveRepo.save(newArchive);
	
	return savedArchive;
	
		}
	@Transactional
	public Archive getArchiveById(Long libraryItemId) {
		if (libraryItemId == null) {
			throw new IllegalArgumentException("Id must not be null.");
		}
		Archive archive = archiveRepo.findArchiveBylibraryItemID(libraryItemId);
		return archive;
	}
	
	@Transactional
	public List<Archive> getAllArchives() {
		List<Archive> archives = (List<Archive>) archiveRepo.findAll();
		return archives;
	}
	
	@Transactional
	public List<Archive> getArchivesByTitle(String title) {
		return archiveRepo.findArchiveByTitle(title);
	}
	
	@Transactional
	public List<Archive> getArchivesByPublishedYear(int py) {
		return archiveRepo.findArchiveByPublishedYear(py);
	}
	
	@Transactional
	public List<Archive> getArchivesByInstitution(String institution) {
		return archiveRepo.findArchiveByInstitution(institution);
	}
	
	@Transactional
	public Archive updateArchive(Long libraryItemId, String title, Integer publishedYear, Integer loanablePeriod, Double dailyOverdueFee, 
			ItemStatus itemStatus, String institution) {
	
		if (libraryItemId == null) {
			throw new IllegalArgumentException("Library item ID not be null.");
		}
		
		Archive archive = archiveRepo.findArchiveBylibraryItemID(libraryItemId);
		if (archive == null) {
			throw new IllegalStateException("Could not find an archive with the specified id (id: " + libraryItemId + ").");
		}
		
		if (title != null) {
			archive.setTitle(title);
		} 
		if (publishedYear != null) {
			archive.setPublishedYear(publishedYear);
		}
		if (loanablePeriod != null) {
			archive.setLoanablePeriod(loanablePeriod);
		}
		if (dailyOverdueFee != null) {
			archive.setDailyOverdueFee(dailyOverdueFee);
		}
		if (itemStatus != null) {
			archive.setItemStatus(itemStatus);
		}
		if (institution !=null) {
			archive.setInstitution(institution);
		}
		
		archiveRepo.save(archive);
		
		return archive;
		
	}
		
	@Transactional
	public boolean deleteArchive(Archive archiveToDelete) {
		if (archiveToDelete == null) {
			return false;
		} else {
			archiveRepo.delete(archiveToDelete);
			return true;
		}
	}	
		
		
	@Transactional
	public boolean deleteArchiveById(Long libraryItemId) {
		if (libraryItemId == null) {
			throw new IllegalArgumentException("libraryItemId parameter cannot be null.");
		}
		Archive archive = archiveRepo.findArchiveBylibraryItemID(libraryItemId);
		return deleteArchive(archive);
	}	
		
		
}
