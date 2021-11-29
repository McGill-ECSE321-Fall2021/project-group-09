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
	public void setemployeeIDNumber(Long anEmployeeIDNum) {
		super.setemployeeIDNumber(anEmployeeIDNum);
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
	public Long getemployeeIDNumber() {
		return super.getemployeeIDNumber();
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

