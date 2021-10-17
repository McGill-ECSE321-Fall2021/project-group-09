package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class HeadLibrarian extends Librarian {

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// HeadLibrarian Attributes

	private Long managerIdNum;

	// HeadLibrarian Associations
	private Library library;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public HeadLibrarian(String aFullName, LibraryManagement aLibraryManagement, String aLibrarianEmail,
			String aLibrarianPassword, String aLibrarianUsername, Long aEmployeeIdNum, Long aManagerIdNum,
			Library aLibrary) {
		super(aFullName, aLibraryManagement, aLibrarianEmail, aLibrarianPassword, aLibrarianUsername, aEmployeeIdNum);
		managerIdNum = aManagerIdNum;
		if (aLibrary == null || aLibrary.getHeadLibrarian() != null) {
			throw new RuntimeException(
					"Unable to create HeadLibrarian due to aLibrary. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
		library = aLibrary;
	}

	public HeadLibrarian(String aFullName, LibraryManagement aLibraryManagement, String aLibrarianEmail,
			String aLibrarianPassword, String aLibrarianUsername, Long aEmployeeIdNum, Long aManagerIdNum,
			String aLibraryNameForLibrary, String aLibraryAddressForLibrary, String aLibraryPhoneForLibrary,
			String aLibrarymailForLibrary, LibraryManagement aLibraryManagementForLibrary,
			Schedule... allSchedulesForLibrary) {
		super(aFullName, aLibraryManagement, aLibrarianEmail, aLibrarianPassword, aLibrarianUsername, aEmployeeIdNum);
		managerIdNum = aManagerIdNum;
		library = new Library(aLibraryNameForLibrary, aLibraryAddressForLibrary, aLibraryPhoneForLibrary,
				aLibrarymailForLibrary, aLibraryManagementForLibrary, this, allSchedulesForLibrary);
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setManagerIdNum(Long aManagerIdNum) {
		boolean wasSet = false;
		managerIdNum = aManagerIdNum;
		wasSet = true;
		return wasSet;
	}
	
	@Id
	public Long getManagerIdNum() {
		return managerIdNum;
	}

	/* Code from template association_GetOne */
	@OneToOne(optional=false) 
	public Library getLibrary() {
		return library;
	}

	public void delete() {
		Library existingLibrary = library;
		library = null;
		if (existingLibrary != null) {
			existingLibrary.delete();
		}
		super.delete();
	}

	public String toString() {
		return super.toString() + "[" + "managerIdNum" + ":" + getManagerIdNum() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "library = "
				+ (getLibrary() != null ? Integer.toHexString(System.identityHashCode(getLibrary())) : "null");
	}
}
