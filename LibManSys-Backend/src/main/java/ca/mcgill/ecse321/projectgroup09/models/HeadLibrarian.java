package ca.mcgill.ecse321.projectgroup09.models;


import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class HeadLibrarian extends Librarian {

	//HeadLibrarian Attributes
	private Long managerIDNum;

	//HeadLibrarian Associations
	private Library library;

	@Override
	public void setLibrarianEmail(String aLibrarianEmail) {
		super.setLibrarianEmail(aLibrarianEmail);
	}

	@Override
	public void setLibrarianPassword(String aLibrarianPassword) {
		super.setLibrarianPassword(aLibrarianPassword);
	}

	@Override
	public void setLibrarianUsername(String aLibrarianUsername) {
		super.setLibrarianUsername(aLibrarianUsername);
	}

	@Override
	public void setemployeeIDNum(Long anEmployeeIDNum) {
		super.setemployeeIDNum(anEmployeeIDNum);
	}

	@Override
	public String getLibrarianEmail() {
		return super.getLibrarianEmail();
	}

	@Override
	public String getLibrarianPassword() {
		return super.getLibrarianPassword();
	}

	@Override
	public String getLibrarianUsername() {
		return super.getLibrarianUsername();
	}
	
	@Override
	public Long getemployeeIDNum() {
		return super.getemployeeIDNum();
	}
	
	public void setmanagerIDNum(Long aManagerIDNum) {
		this.managerIDNum = aManagerIDNum;
	}
	
	//@Id
	public Long getmanagerIDNum() {
		return this.managerIDNum;
	}

	@OneToOne 
	public Library getLibrary() {
		return this.library;
	}
	
	public void setLibrary (Library aLibrary) {
		this.library = aLibrary;
	}
	
}

//package ca.mcgill.ecse321.projectgroup09.models;
//
//import javax.persistence.*;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//import java.util.Set;
//import javax.persistence.CascadeType;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Id;
//
//@Entity
//public class HeadLibrarian extends Librarian {
//
//	// ------------------------
//	// MEMBER VARIABLES
//	// ------------------------
//
//	// HeadLibrarian Attributes
//
//	private Long managerIDNum;
//
//	// HeadLibrarian Associations
//	private Library library;
//
//	// ------------------------
//	// CONSTRUCTOR
//	// ------------------------
//
//	public HeadLibrarian(String aFullName, LibraryManagement aLibraryManagement, String aLibrarianEmail,
//			String aLibrarianPassword, String aLibrarianUsername, Long aEmployeeIdNum, Long amanagerIDNum,
//			Library aLibrary) {
//		super(aFullName, aLibraryManagement, aLibrarianEmail, aLibrarianPassword, aLibrarianUsername, aEmployeeIdNum);
//		managerIDNum = amanagerIDNum;
//		if (aLibrary == null || aLibrary.getHeadLibrarian() != null) {
//			throw new RuntimeException(
//					"Unable to create HeadLibrarian due to aLibrary. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
//		}
//		library = aLibrary;
//	}
//
//	public HeadLibrarian(String aFullName, LibraryManagement aLibraryManagement, String aLibrarianEmail,
//			String aLibrarianPassword, String aLibrarianUsername, Long aEmployeeIdNum, Long amanagerIDNum,
//			String aLibraryNameForLibrary, String aLibraryAddressForLibrary, String aLibraryPhoneForLibrary,
//			String aLibrarymailForLibrary, LibraryManagement aLibraryManagementForLibrary,
//			Schedule... allSchedulesForLibrary) {
//		super(aFullName, aLibraryManagement, aLibrarianEmail, aLibrarianPassword, aLibrarianUsername, aEmployeeIdNum);
//		managerIDNum = amanagerIDNum;
//		library = new Library(aLibraryNameForLibrary, aLibraryAddressForLibrary, aLibraryPhoneForLibrary,
//				aLibrarymailForLibrary, aLibraryManagementForLibrary, this, allSchedulesForLibrary);
//	}
//
//	// ------------------------
//	// INTERFACE
//	// ------------------------
//
//	public boolean setmanagerIDNum(Long amanagerIDNum) {
//		boolean wasSet = false;
//		managerIDNum = amanagerIDNum;
//		wasSet = true;
//		return wasSet;
//	}
//	
//	//@Id
//	public Long getmanagerIDNum() {
//		return managerIDNum;
//	}
//
//	/* Code from template association_GetOne */
//	@OneToOne(optional=false) 
//	public Library getLibrary() {
//		return library;
//	}
//
//	public void delete() {
//		Library existingLibrary = library;
//		library = null;
//		if (existingLibrary != null) {
//			existingLibrary.delete();
//		}
//		super.delete();
//	}
//
//	public String toString() {
//		return super.toString() + "[" + "managerIDNum" + ":" + getmanagerIDNum() + "]"
//				+ System.getProperties().getProperty("line.separator") + "  " + "library = "
//				+ (getLibrary() != null ? Integer.toHexString(System.identityHashCode(getLibrary())) : "null");
//	}
//}
