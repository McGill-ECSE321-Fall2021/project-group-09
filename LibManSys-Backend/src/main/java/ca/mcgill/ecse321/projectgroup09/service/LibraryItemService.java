package ca.mcgill.ecse321.projectgroup09.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
	 * @return {@code LibraryItem} matching {@code libraryItemId} if found in repository.
	 */
	@Transactional
	public LibraryItem getLibraryItemById(Long libraryItemId) {
		if (libraryItemId == null) {
			throw new IllegalArgumentException("libraryItemId must not be null.");
		}
		LibraryItem li = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		if (li == null) {
			throw new IllegalArgumentException("Library item with id " + libraryItemId + " does not exist.");
		}
		return li;
	}
	
	/**
	 * Find all library items with specified title. 
	 * @param title
	 * @return list of library items with title matching input.
	 */
	@Transactional
	public List<LibraryItem> getLibraryItemsByTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Argument must not be null.");
		}
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
	 * Get library items by status.
	 * @param itemStatus
	 * @return
	 */
	@Transactional
	public List<LibraryItem> getLibraryItemsByStatus(ItemStatus itemStatus) {
		if (itemStatus == null) {
			throw new IllegalArgumentException("Argument must not be null.");
		}
		return libraryItemRepo.findLibraryItemByItemStatus(itemStatus);
	}
	
	/**
	 * 
	 * @param libCardNumber
	 * @return
	 */
	@Transactional
	public List<LibraryItem> getLibraryItemsByReservingMember(Long libCardNumber) {
		if (libCardNumber == null) {
			throw new IllegalArgumentException("Argument must not be null.");
		}
		Member m = memberRepo.findMemberByLibCardNumber(libCardNumber);
		if (m == null) {
			throw new IllegalArgumentException("Could not find member with libCardNumber = " + libCardNumber + ".");
		}
		List<LibraryItem> lis = libraryItemRepo.findLibraryItemByMember(m);
		return lis;
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
	 * @param memberId Member attemping to reserve a library item.
	 * @param libraryItemId Library item member is attemping to reserve.
	 * @return updated library item.
	 */
	@Transactional
	public LibraryItem reserveLibraryItem(Long memberId, Long libraryItemId) {
		// Check inputs not null
		if (memberId == null || libraryItemId == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
		// get member and library item from repo
		Member rm = memberRepo.findMemberByLibCardNumber(memberId);
		if (rm == null) {
			throw new IllegalStateException("Could not find member with id: " + memberId + " in repository.");
		}
		LibraryItem libraryItem = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		if (libraryItem == null) {
			throw new IllegalStateException("Could not find library item with id: " + libraryItemId + " in repository.");
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
		// add member to items association
		libraryItem.setMember(rm);
		// Set book status to reserved
		libraryItem.setItemStatus(LibraryItem.ItemStatus.Reserved);
		
		// Save member and library item to update information
		libraryItemRepo.save(libraryItem);
		memberRepo.save(rm);
		
		return libraryItem;
	}
	
	/**
	 * Cancels the reservation a library member has placed on a library item.
	 * @param memberId
	 * @param libraryItemId
	 * @return Updated library item
	 */
	@Transactional
	public LibraryItem cancelReservation(long memberId, long libraryItemId) {
		Member m = memberRepo.findMemberByLibCardNumber(memberId);
		if (m == null) {
			throw new IllegalArgumentException("There does not exist a member with that id.");
		}
		LibraryItem li = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		if (li == null) {
			throw new IllegalArgumentException("There does not exist a library item with that id.");
		}
		// Make sure library item is not reserved already
		if (!li.getItemStatus().equals(LibraryItem.ItemStatus.Reserved)) {
			throw new IllegalStateException("Cannot cancel reservation for library item that is not reserved.");
		}
		// Make sure library item is reserved by this member
		if (li.getMember() == null || !li.getMember().equals(m)) {
			throw new IllegalStateException("Cannot cancel reservation for another member.");
		}
		if (!m.getReserved().contains(li)) {
			throw new IllegalStateException("member resrved list does not contain library item");
		}
		// Add item to member's reserved list
		m.getReserved().remove(li);
		// add member to items association
		li.setMember(null);
		// Set book status to reserved
		li.setItemStatus(LibraryItem.ItemStatus.Available);
		
		// Save member and library item to update information
		libraryItemRepo.save(li);
		memberRepo.save(m);
		
		return li;

	}
	
	/**
	 * Allows member to checkout a library item. LibraryItem must not currently
	 * be checked out. Member must be verfied? or else they pay a fee.
	 * Members can only checkout a maximum of 10 library items at a time
	 * 
	 * {@link https://github.com/McGill-ECSE321-Fall2021/project-group-09/wiki/Use-Case-Diagram#checkout-library-item-abe-arafat}
	 * 
	 * @param librarianId Librarian that is performing the library item checkout operation.
	 * @param memberId Member who is attempting to checkout an item
	 * @param libraryItemId LibraryItem member is attemping to checkout
	 */
	@Transactional
	public Loan checkoutLibraryItem(Long librarianId, Long memberId, Long libraryItemId) {
		// make sure params not null
		if (librarianId == null || memberId == null || libraryItemId == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
		// find in repo
		Librarian l = librarianRepo.findLibrarianByEmployeeIDNumber(librarianId);
		if (l == null) {
			throw new IllegalStateException("Could not find librarian in repository.");
		}
		Member m = memberRepo.findMemberByLibCardNumber(memberId);
		if (m == null) {
			throw new IllegalStateException("Could not find member in repository");
		}
		LibraryItem li = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		if (li == null) {
			throw new IllegalStateException("Could not find library item in repository.");
		}
		// Make sure member has less than 10 library items checked out
		if (m.getActiveLoans() == 10) {
			throw new IllegalStateException("Cannot checkout library item, member has reached maximum number of library items allowed to be borrowed at a time (10).");
		}
		// make sure library item is avaiable or reserved by this member
		if (li.getItemStatus() == ItemStatus.CheckedOut) {
			throw new IllegalStateException("Can not checkout a library item that is already checked out.");
		} else if (li.getItemStatus() == ItemStatus.LibraryOnly) {
			throw new IllegalStateException("Cannot checkout library item because it is for library use only.");
		} else if (li.getItemStatus() == ItemStatus.Reserved) {
			// if item is reserved, make sure that it is reserved by same member that is currently attempting to check it out
			if (!li.getMember().getLibCardNumber().equals(m.getLibCardNumber())) {
				// LibraryItem reserving member is not same as member currently attempting to checkout:
				throw new IllegalStateException("Can not checkout a library item that is currently reserved by another member.");
			}
		}
		// Make sure member does not have any outstanding fees
		if (m.getAmountOwed() > 0) {
			throw new IllegalStateException("Cannot checkout library item because member owes $" + m.getAmountOwed() + ".");
		}
		
		// create new loan for library item
		Loan loan = new Loan();
		
		// set borrowed date to today
		Date today = Date.valueOf(LocalDate.now());
		loan.setBorrowedDate(today);
		loan.setReturnDate(null);
		
		// calculate return date based on library item loanable period
		// wait don't do this, not what return date is for...
		/*
		int loanPeriod = li.getLoanablePeriod();
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DAY_OF_MONTH, loanPeriod);
		java.util.Date utilReturnDate = c.getTime();
		java.sql.Date returnDate = new java.sql.Date(utilReturnDate.getTime());
		loan.setReturnDate(returnDate);
		*/
		
		// start with no late fees
		loan.setLateFees(0);
		loan.setAreFeesPaid(false);
		
		// set loan status to active initially
		loan.setLoanStatus(LoanStatus.Active);
		
		// set associatations for loan (member, librarian, libraryItem)
		loan.setLibrarian(l);
		loan.setMember(m);
		loan.setLibraryItem(li);
		loanRepo.save(loan);

		// update loan associations
		l.getLoans().add(loan);
		m.getLoans().add(loan);
		li.getLoans().add(loan);
		
		// update library item attributes
		li.setItemStatus(ItemStatus.CheckedOut);
		 
		// update member attributes
		m.setActiveLoans(m.getActiveLoans() + 1);
		
		// save loan first before updating others with the loan
		loan = loanRepo.save(loan);
		
		// save updated librarian, member, library item
		l = librarianRepo.save(l);
		m = memberRepo.save(m);
		li = libraryItemRepo.save(li);
		
		// testing
		li = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		
		return loan;
	}
	
	/**
	 * Allows member to renew their current loan for a library item.
	 * Cannot renew overdue loan.
	 * Reset borrowed day and returned day for loan.
	 * @param memberId library card number of member
	 * @param libraryItemId ID of library item that member wants to renew.
	 * @return loan Updated loan object.
	 */
	@Transactional
	public Loan renewLibraryItem(Long memberId, Long libraryItemId) {
		if (memberId == null || libraryItemId == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
		// find in repo
		Member m = memberRepo.findMemberByLibCardNumber(memberId);
		if (m == null) {
			throw new IllegalStateException("Could not find member in repository.");
		}
		LibraryItem li = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		if (li == null) {
			throw new IllegalStateException("Could not find library item in repository.");
		}
		// Make sure library item is checked out
		if (!li.getItemStatus().equals(ItemStatus.CheckedOut)) {
			// if not checked out, throw error
			throw new IllegalStateException("Cannot renew library item that is not currently checked out.");
		}
		
		// get all present and past loans for library item
		List<Loan> loans = li.getLoans();
		
		// look for non-Closed loan (should only be one)
		Loan loan = null;
		for (Loan l : loans) {
			if (!l.getLoanStatus().equals(LoanStatus.Closed)) {
				// make sure we have no already found a non-Closed Loan for this Library Item
				if (loan == null) {
					loan = l;
				} else {
					throw new IllegalStateException("LibraryItem should only have one non-Closed loan at the same time.");
				}
			}
		}
		// if no library item has active or renewed loan, or if loan belongs to other member, throw error
		if (loan == null || !loan.getMember().equals(m)) {
			throw new IllegalStateException("Could not renew library item, not currently checked out by this member.");
		}
		// make sure loan is not overdue
		// calculate due date
		Calendar c = Calendar.getInstance();
		c.setTime(loan.getBorrowedDate());
		c.add(Calendar.DAY_OF_MONTH, loan.getLibraryItem().getLoanablePeriod());
		Date dueDate = new Date(c.getTime().getTime());
		// Check if loan is overdue
		// calculate and set late fees if over due
		Date today = Date.valueOf(LocalDate.now());
		if (loan.getLoanStatus().equals(LoanStatus.Overdue) || today.after(dueDate)) {
			throw new IllegalStateException("Cannot renew library item that is already overdue.");
		}
		// make sure loan is present in member.loans?
		//if (!m.getLoans().contains(loan)) {
		//	throw new IllegalStateException("Loan not present in member loan list.");
		//}
		
		// renew item
		loan.setLoanStatus(LoanStatus.Renewed);
		loan.setBorrowedDate(today);
		
		// save loan to repo to update
		// member and library item not changing so don't need to save them to repo again
		loanRepo.save(loan);
		return loan;
	}
	
	/**
	 * Allows a librarian to return a book for a member. If todays date is after the due date for the library item
	 * then late fees will be applied to the loan. If the library item is successfully returned, the loan status
	 * will be set to Closed.
	 * @param memberId Id of member returning an item
	 * @param libraryItemId Id of library item member is returning
	 * @return updated loan object
	 */
	@Transactional
	public Loan returnLibraryItem(/*Long librarianId,*/ Long memberId, Long libraryItemId) {
		if (/*librarianId == null ||*/ memberId == null || libraryItemId == null) {
			throw new IllegalArgumentException("Arguments must not be null.");
		}
			// find in repo
			//Librarian l = librarianRepo.findLibrarianByEmployeeIDNum(librarianId);
			//if (l == null) {
			//	throw new IllegalStateException("Could not find librarian in repository.");
			//}
		Member m = memberRepo.findMemberByLibCardNumber(memberId);
		if (m == null) {
			throw new IllegalStateException("Could not find member in repository.");
		}
		LibraryItem li = libraryItemRepo.findLibraryItemByLibraryItemID(libraryItemId);
		if (li == null) {
			throw new IllegalStateException("Could not find library item in repository.");
		}
		// Make sure library item is currently checked out
		if (!li.getItemStatus().equals(ItemStatus.CheckedOut)) {
			// if not checked out, throw error
			throw new IllegalStateException("Cannot return library item that is not currently checked out.");
		}
		if (m.getActiveLoans() == 0) {
			throw new IllegalStateException("Member active loans is 0 but member is attemping to return library item.");
		}
		
		// get all present and past loans for library item
		List<Loan> loans = li.getLoans();
		
		// look for non-Closed loan (should only be one)
		Loan loan = null;
		for (Loan ll : loans) {
			if (!ll.getLoanStatus().equals(LoanStatus.Closed)) {
				// make sure we have no already found a non-Closed Loan for this Library Item
				if (loan == null) {
					loan = ll;
				} else {
					throw new IllegalStateException("LibraryItem should only have one non-Closed loan at the same time.");
				}
			}
		}
		
		// if no library item has active or renewed loan, or if loan belongs to other member, throw error
		if (loan == null || !loan.getMember().equals(m)) {
			throw new IllegalStateException("Cannot return library item, not currently checked out by this member.");
		}
		
		// return item

		// set loan return date to today
		Date today = Date.valueOf(LocalDate.now());
		loan.setReturnDate(today);
		
		
		// calculate due date
		Calendar c = Calendar.getInstance();
		c.setTime(loan.getBorrowedDate());
		c.add(Calendar.DAY_OF_MONTH, loan.getLibraryItem().getLoanablePeriod());
		Date dueDate = new Date(c.getTime().getTime());
		
		// Check if loan is overdue
		// calculate and set late fees if over due
		double lateFee = 0;
		if (loan.getReturnDate().after(dueDate)) {
			// OVERDUE - calculate late fee, returnDate = today
			long daysLateInMillies = today.getTime() - dueDate.getTime();
			long daysLate = TimeUnit.DAYS.convert(daysLateInMillies, TimeUnit.MILLISECONDS);			
			lateFee = ((double) daysLate) * li.getDailyOverdueFee();
		}
		loan.setLateFees(lateFee);
		// Add fee to member account here // or have them call LoanService.getLoanFeesByMember
		m.setAmountOwed(m.getAmountOwed() + lateFee);
		
		// set loan status to closed when item is returned
		loan.setLoanStatus(LoanStatus.Closed);
		
		// update library item attributes
		li.setItemStatus(ItemStatus.Available);
		
		// update member attributes
		m.setActiveLoans(m.getActiveLoans() - 1);
		
		// save updated member, library item and loan
		memberRepo.save(m);
		libraryItemRepo.save(li);
		loanRepo.save(loan);
		
		return loan;
	}
}
