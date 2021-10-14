package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public abstract class Account {
	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Account Attributes
	private String fullName;

	// Account Associations
	private LibraryManagement libraryManagement;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Account(String aFullName, LibraryManagement aLibraryManagement) {
		fullName = aFullName;
		boolean didAddLibraryManagement = setLibraryManagement(aLibraryManagement);
		if (!didAddLibraryManagement) {
			throw new RuntimeException(
					"Unable to create account due to libraryManagement. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
		}
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setFullName(String aFullName) {
		boolean wasSet = false;
		fullName = aFullName;
		wasSet = true;
		return wasSet;
	}

	public String getFullName() {
		return fullName;
	}

	/* Code from template association_GetOne */
	public LibraryManagement getLibraryManagement() {
		return libraryManagement;
	}

	/* Code from template association_SetOneToMany */
	public boolean setLibraryManagement(LibraryManagement aLibraryManagement) {
		boolean wasSet = false;
		if (aLibraryManagement == null) {
			return wasSet;
		}

		LibraryManagement existingLibraryManagement = libraryManagement;
		libraryManagement = aLibraryManagement;
		if (existingLibraryManagement != null && !existingLibraryManagement.equals(aLibraryManagement)) {
			existingLibraryManagement.removeAccount(this);
		}
		libraryManagement.addAccount(this);
		wasSet = true;
		return wasSet;
	}

	public void delete() {
		LibraryManagement placeholderLibraryManagement = libraryManagement;
		this.libraryManagement = null;
		if (placeholderLibraryManagement != null) {
			placeholderLibraryManagement.removeAccount(this);
		}
	}

	public String toString() {
		return super.toString() + "[" + "fullName" + ":" + getFullName() + "]"
				+ System.getProperties().getProperty("line.separator") + "  " + "libraryManagement = "
				+ (getLibraryManagement() != null ? Integer.toHexString(System.identityHashCode(getLibraryManagement()))
						: "null");
	}

}
