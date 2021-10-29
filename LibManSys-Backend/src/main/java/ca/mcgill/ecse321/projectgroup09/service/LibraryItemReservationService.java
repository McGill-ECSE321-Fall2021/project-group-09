package ca.mcgill.ecse321.projectgroup09.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;

@Service
public class LibraryItemReservationService {
	
	@Autowired
	private MemberRepository memberDao; // Dao is same thing as Repository
	
	@Autowired
	private LibraryItemRepository libraryItemDao;
	
	public LibraryItemReservationService() {
		
	}
	
	/**
	 * This service method reserves a book for a user,
	 * as long as that book is not already being reserved.
	 * 
	 * @param memberId {@code Long} Library card number of member attemping to reserve a Book.
	 * @param bookId {@code Long} Id of book member is attemping to reserve.
	 */
	@Transactional
	public void reserveBook(Long memberId, Long bookId) {
		// Check inputs
		if (Objects.isNull(memberId) || Objects.isNull(bookId)) {
			throw new IllegalArgumentException("Arguments must not be null");
		}
		
		// Reserving Member
		Member rm = memberDao.findMemberByLibCardNumber(memberId);
		// Book to reserve
		LibraryItem book = libraryItemDao.findLibraryItemByLibraryItemID(bookId);
		
		if (Objects.isNull(rm)) {
			throw new NullPointerException("Could not find member in Member repository.");
		}
		if (Objects.isNull(book)) {
			throw new NullPointerException("Could not find book in LibraryItem repository.");
		}
		
		// Make sure book is not reserved already
		if (book.getItemStatus().equals(LibraryItem.ItemStatus.Reserved)) {
			throw new IllegalStateException("Cannot reserve book that is already being reserved by another member.");
		}
		
		// Make sure book is able to be reserved
		if (book.getItemStatus().equals(LibraryItem.ItemStatus.LibraryOnly)) {
			throw new IllegalStateException("Cannot reserve item that is for library use only (newspapers and archives).");
		}
		
		// Add item to member's reserved list
		rm.getReserved().add(book);
		// Set book status to reserved
		book.setItemStatus(LibraryItem.ItemStatus.Reserved);
		
		// Save member and book?
		
	}
	
}
