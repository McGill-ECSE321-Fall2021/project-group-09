package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.projectgroup09.dao.LibrarianRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LibraryItemRepository;
import ca.mcgill.ecse321.projectgroup09.dao.LoanRepository;
import ca.mcgill.ecse321.projectgroup09.dao.MemberRepository;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;

/**
 * Library Item Service.
 * 
 * <ul>Services Provided:
 * <li>Create Read Update Delete methods</li>
 * <li>Reserve a library item</li>
 * <li>Checkout a library item</li>
 * </ul>
 */
@Service
public class LibraryItemService {
	
	@Autowired
	private LibraryItemRepository libraryItemRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private LibrarianRepository librarianRepo;
	
	@Autowired
	private LoanRepository loanRepo;
	
	
	// Create methods should be in individual library item subclasses
	// Update methods should also be in individual library item subclasses
	
	/**
	 * Read - Returns the library item object specified by the library item ID
	 * if it is present in the library item repository.
	 * @param libraryItemId {@code long} library item id
	 * @return {@code LibraryItem} matching {@code libraryItemId} if found in repository, {@code null} otherwise.
	 */
	@Transactional
	public LibraryItem getLibraryItemById(Long libraryItemId) {
		if (libraryItemId == null) {
			throw new IllegalArgumentException("libraryItemId must not be null.");
		}
		LibraryItem li = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		return li;
	}
	
	/**
	 * Find all library items with specified title. 
	 * @param title
	 * @return list of library items with title matching input.
	 */
	@Transactional
	public List<LibraryItem> getLibraryItemsByTitle(String title) {
		List<LibraryItem> lis = libraryItemRepo.findLibraryItemByTitle(title);
		return lis;
	}
	
	/**
	 * Find all library items that have library item status 'Reserved'.
	 * @return list of reserved library items.
	 */
	@Transactional
	public List<LibraryItem> getAllReservedLibraryItems() {
		return libraryItemRepo.findLibraryItemByItemStatus(ItemStatus.Reserved);
	}
	
	/**
	 * Get all library items in library item repository.
	 * @return list of all library items in library management system.
	 */
	@Transactional
	public List<LibraryItem> getAllLibraryItems() {
		List<LibraryItem> lis = libraryItemRepo.findAll();
		return lis;
	}
	
	/**
	 * Delete the specified library item from the library item repository.
	 * @param libraryItemToDelete {@code LibraryItem} library item to delete from repository.
	 * @return {@code true} if library item was deleted from repository, {@code false} if {@code libraryItemToDelete} was not present in repository.
	 */
	@Transactional
	public boolean deleteLibraryItem(LibraryItem libraryItemToDelete) {
		if (libraryItemToDelete == null) {
			return false;
		} else {
			libraryItemRepo.delete(libraryItemToDelete);
			return true;
		}
	}
	
	
	/**
	 * Delete the specified library item from the library item repository using library item ID.
	 * @param libraryItemId {@code long} ID of library item to delete
	 * @return {@code true} if library item was deleted from repository, {@code false} if {@code libraryItemId} was not present in repository.
	 */
	@Transactional
	public boolean deleteLibraryItemById(Long libraryItemId) {
		if (libraryItemId == null) {
			throw new IllegalArgumentException("libraryItemId parameter cannot be null.");
		}
		LibraryItem libraryItem = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		return deleteLibraryItem(libraryItem);		
	}
	
	/**
	 * Business method.
	 * 
	 * This service method reserves a library item for a user,
	 * as long as that library item is able to be reserved.
	 * Library Item can only be reserved by one member at a time and
	 * some Library Items can not be reserved.
	 * 
	 * @param rm {@code Member} Member attemping to reserve a library item.
	 * @param libraryItem {@code LibraryItem} Library item member is attemping to reserve.
	 */
	@Transactional
	public void reserveLibraryItem(Member rm, LibraryItem libraryItem) {
		// Check inputs not null
		if (rm == null || libraryItem == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}		
		// Make book library item status is set
		if (libraryItem.getItemStatus() == null) {
			throw new IllegalStateException("libraryItem.getItemStatus() == null");
		}
		
		// Make sure library item is not reserved already
		if (libraryItem.getItemStatus().equals(LibraryItem.ItemStatus.Reserved)) {
			throw new IllegalStateException("Cannot reserve a library item that is already being reserved by another member.");
		}
		// Make sure library item is able to be reserved
		if (libraryItem.getItemStatus().equals(LibraryItem.ItemStatus.LibraryOnly)) {
			throw new IllegalStateException("Cannot reserve item that is for library use only (newspapers and archives).");
			// return false instead of throwing exception?
		}
		
		// Make sure member reserved list is intialized
		if (rm.getReserved() == null) {
			throw new IllegalStateException("Member reserve list is not initialized.");
		}
		
		// Add item to member's reserved list
		rm.getReserved().add(libraryItem);
		// Set book status to reserved
		libraryItem.setItemStatus(LibraryItem.ItemStatus.Reserved);
		
		// Save member and library item to update information
		libraryItemRepo.save(libraryItem);
		memberRepo.save(rm);
	}
	
	/**
	 * Allows member to checkout a library item. LibraryItem must not currently
	 * be checked out. Member must be verfied? or else they pay a fee.
	 * Members can only checkout a maximum of 10 library items at a time
	 * 
	 * {@link https://github.com/McGill-ECSE321-Fall2021/project-group-09/wiki/Use-Case-Diagram#checkout-library-item-abe-arafat}
	 * 
	 * @param l Librarian that is performing the library item checkout operation.
	 * @param m Member who is attempting to checkout an item
	 * @param li LibraryItem member is attemping to checkout
	 */
	@Transactional
	public void checkoutLibraryItem(Librarian l, Member m, LibraryItem li) {
		// make sure params not null
		if (l == null || m == null || li == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
		// Make sure member has less than 10 library items checked out
		if (m.getActiveLoans() == 10) {
			throw new IllegalStateException("Member has reached maximum number of library items allowed to be borrowed at a time (10).");
		}
		// make sure library item is avaiable or reserved by this member
		if (li.getItemStatus() == ItemStatus.CheckedOut) {
			throw new IllegalStateException("Can not checkout a library item that is already checked out.");
		} else if (li.getItemStatus() == ItemStatus.LibraryOnly) {
			throw new IllegalStateException("Can not checkout a library item that is has status 'LibraryOnly'.");
		} else if (li.getItemStatus() == ItemStatus.Reserved) {
			// if item is reserved, make sure that it is reserved by same member that is currently attempting to check it out
			if (!li.getMember().getLibCardNumber().equals(m.getLibCardNumber())) {
				// LibraryItem reserving member is not same as member currently attempting to checkout:
				throw new IllegalStateException("Can not checkout a library item that is currently reserved by another member.");
			}
		}
		
		// create new loan for library item
		Loan loan = new Loan();
		
		// set borrowed date to today
		Date today = Date.valueOf(LocalDate.now());
		loan.setBorrowedDate(today);
		
		// calculate return date based on library item loanable period
		int loanPeriod = li.getLoanablePeriod();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DAY_OF_MONTH, loanPeriod);
		java.util.Date utilReturnDate = c.getTime();
		java.sql.Date returnDate = new java.sql.Date(utilReturnDate.getTime());
		loan.setReturnDate(returnDate);
		
		// start with no late fees
		loan.setLateFees(0);
		
		// set loan status to active initially
		loan.setLoanStatus(LoanStatus.Active);
		
		// set associatations for loan (member, librarian, libraryItem)
		loan.setLibrarian(l);
		loan.setMember(m);
		loan.setLibraryItem(li);
		
		// update loan associations
		l.getLoans().add(loan);
		m.getLoans().add(loan);
		li.getLoans().add(loan);
		
		// update library item attributes
		li.setItemStatus(ItemStatus.CheckedOut);
		
		// update member attributes
		m.setActiveLoans(m.getActiveLoans() + 1);
		
		// save updated librarian, member, library item and loan
		librarianRepo.save(l);
		memberRepo.save(m);
		libraryItemRepo.save(li);
		loanRepo.save(loan);
	}
	
	/**
	 * Allows member to renew their current loan for a library item.
	 * Cannot renew overdue loan.
	 * Reset borrowed day and returned day for loan.
	 * @param m Member attempting to renew their loan.
	 * @param li LibraryItem that member wants to renew.
	 */
	@Transactional
	public void renewLibraryItem(Member m, LibraryItem li) {
		
	}
	
	/**
	 * TODO
	 * @param m
	 * @param li
	 */
	@Transactional
	public void returnLibraryItem(Member m, LibraryItem li) {
		
	}
}
