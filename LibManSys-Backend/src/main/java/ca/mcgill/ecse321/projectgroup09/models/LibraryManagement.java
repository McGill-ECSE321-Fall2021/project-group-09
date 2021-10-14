package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.util.*;

public class LibraryManagement {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// LibraryManagement Associations
	private List<Library> libraries;
	private List<Account> accounts;
	private List<LibraryItem> libraryItems;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public LibraryManagement() {
		libraries = new ArrayList<Library>();
		accounts = new ArrayList<Account>();
		libraryItems = new ArrayList<LibraryItem>();
	}

	// ------------------------
	// INTERFACE
	// ------------------------
	/* Code from template association_GetMany */
	public Library getLibrary(int index) {
		Library aLibrary = libraries.get(index);
		return aLibrary;
	}

	public List<Library> getLibraries() {
		List<Library> newLibraries = Collections.unmodifiableList(libraries);
		return newLibraries;
	}

	public int numberOfLibraries() {
		int number = libraries.size();
		return number;
	}

	public boolean hasLibraries() {
		boolean has = libraries.size() > 0;
		return has;
	}

	public int indexOfLibrary(Library aLibrary) {
		int index = libraries.indexOf(aLibrary);
		return index;
	}

	/* Code from template association_GetMany */
	public Account getAccount(int index) {
		Account aAccount = accounts.get(index);
		return aAccount;
	}

	public List<Account> getAccounts() {
		List<Account> newAccounts = Collections.unmodifiableList(accounts);
		return newAccounts;
	}

	public int numberOfAccounts() {
		int number = accounts.size();
		return number;
	}

	public boolean hasAccounts() {
		boolean has = accounts.size() > 0;
		return has;
	}

	public int indexOfAccount(Account aAccount) {
		int index = accounts.indexOf(aAccount);
		return index;
	}

	/* Code from template association_GetMany */
	public LibraryItem getLibraryItem(int index) {
		LibraryItem aLibraryItem = libraryItems.get(index);
		return aLibraryItem;
	}

	public List<LibraryItem> getLibraryItems() {
		List<LibraryItem> newLibraryItems = Collections.unmodifiableList(libraryItems);
		return newLibraryItems;
	}

	public int numberOfLibraryItems() {
		int number = libraryItems.size();
		return number;
	}

	public boolean hasLibraryItems() {
		boolean has = libraryItems.size() > 0;
		return has;
	}

	public int indexOfLibraryItem(LibraryItem aLibraryItem) {
		int index = libraryItems.indexOf(aLibraryItem);
		return index;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfLibraries() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Library addLibrary(String aLibraryName, String aLibraryAddress, String aLibraryPhone, String aLibrarymail,
			HeadLibrarian aHeadLibrarian, Schedule... allSchedules) {
		return new Library(aLibraryName, aLibraryAddress, aLibraryPhone, aLibrarymail, this, aHeadLibrarian,
				allSchedules);
	}

	public boolean addLibrary(Library aLibrary) {
		boolean wasAdded = false;
		if (libraries.contains(aLibrary)) {
			return false;
		}
		LibraryManagement existingLibraryManagement = aLibrary.getLibraryManagement();
		boolean isNewLibraryManagement = existingLibraryManagement != null && !this.equals(existingLibraryManagement);
		if (isNewLibraryManagement) {
			aLibrary.setLibraryManagement(this);
		} else {
			libraries.add(aLibrary);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeLibrary(Library aLibrary) {
		boolean wasRemoved = false;
		// Unable to remove aLibrary, as it must always have a libraryManagement
		if (!this.equals(aLibrary.getLibraryManagement())) {
			libraries.remove(aLibrary);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addLibraryAt(Library aLibrary, int index) {
		boolean wasAdded = false;
		if (addLibrary(aLibrary)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfLibraries()) {
				index = numberOfLibraries() - 1;
			}
			libraries.remove(aLibrary);
			libraries.add(index, aLibrary);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveLibraryAt(Library aLibrary, int index) {
		boolean wasAdded = false;
		if (libraries.contains(aLibrary)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfLibraries()) {
				index = numberOfLibraries() - 1;
			}
			libraries.remove(aLibrary);
			libraries.add(index, aLibrary);
			wasAdded = true;
		} else {
			wasAdded = addLibraryAt(aLibrary, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfAccounts() {
		return 0;
	}
	/* Code from template association_AddManyToOne */

	public boolean addAccount(Account aAccount) {
		boolean wasAdded = false;
		if (accounts.contains(aAccount)) {
			return false;
		}
		LibraryManagement existingLibraryManagement = aAccount.getLibraryManagement();
		boolean isNewLibraryManagement = existingLibraryManagement != null && !this.equals(existingLibraryManagement);
		if (isNewLibraryManagement) {
			aAccount.setLibraryManagement(this);
		} else {
			accounts.add(aAccount);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeAccount(Account aAccount) {
		boolean wasRemoved = false;
		// Unable to remove aAccount, as it must always have a libraryManagement
		if (!this.equals(aAccount.getLibraryManagement())) {
			accounts.remove(aAccount);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addAccountAt(Account aAccount, int index) {
		boolean wasAdded = false;
		if (addAccount(aAccount)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfAccounts()) {
				index = numberOfAccounts() - 1;
			}
			accounts.remove(aAccount);
			accounts.add(index, aAccount);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveAccountAt(Account aAccount, int index) {
		boolean wasAdded = false;
		if (accounts.contains(aAccount)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfAccounts()) {
				index = numberOfAccounts() - 1;
			}
			accounts.remove(aAccount);
			accounts.add(index, aAccount);
			wasAdded = true;
		} else {
			wasAdded = addAccountAt(aAccount, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfLibraryItems() {
		return 0;
	}
	/* Code from template association_AddManyToOne */

	public boolean addLibraryItem(LibraryItem aLibraryItem) {
		boolean wasAdded = false;
		if (libraryItems.contains(aLibraryItem)) {
			return false;
		}
		LibraryManagement existingLibraryManagement = aLibraryItem.getLibraryManagement();
		boolean isNewLibraryManagement = existingLibraryManagement != null && !this.equals(existingLibraryManagement);
		if (isNewLibraryManagement) {
			aLibraryItem.setLibraryManagement(this);
		} else {
			libraryItems.add(aLibraryItem);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeLibraryItem(LibraryItem aLibraryItem) {
		boolean wasRemoved = false;
		// Unable to remove aLibraryItem, as it must always have a libraryManagement
		if (!this.equals(aLibraryItem.getLibraryManagement())) {
			libraryItems.remove(aLibraryItem);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addLibraryItemAt(LibraryItem aLibraryItem, int index) {
		boolean wasAdded = false;
		if (addLibraryItem(aLibraryItem)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfLibraryItems()) {
				index = numberOfLibraryItems() - 1;
			}
			libraryItems.remove(aLibraryItem);
			libraryItems.add(index, aLibraryItem);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveLibraryItemAt(LibraryItem aLibraryItem, int index) {
		boolean wasAdded = false;
		if (libraryItems.contains(aLibraryItem)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfLibraryItems()) {
				index = numberOfLibraryItems() - 1;
			}
			libraryItems.remove(aLibraryItem);
			libraryItems.add(index, aLibraryItem);
			wasAdded = true;
		} else {
			wasAdded = addLibraryItemAt(aLibraryItem, index);
		}
		return wasAdded;
	}

	public void delete() {
		while (libraries.size() > 0) {
			Library aLibrary = libraries.get(libraries.size() - 1);
			aLibrary.delete();
			libraries.remove(aLibrary);
		}

		while (accounts.size() > 0) {
			Account aAccount = accounts.get(accounts.size() - 1);
			aAccount.delete();
			accounts.remove(aAccount);
		}

		while (libraryItems.size() > 0) {
			LibraryItem aLibraryItem = libraryItems.get(libraryItems.size() - 1);
			aLibraryItem.delete();
			libraryItems.remove(aLibraryItem);
		}

	}

}
