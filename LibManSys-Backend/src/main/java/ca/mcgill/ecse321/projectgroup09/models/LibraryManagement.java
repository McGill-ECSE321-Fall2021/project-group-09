package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class LibraryManagement {


	// LibraryManagement Associations
	@ElementCollection
	private List<Library> libraries;
	
	@ElementCollection
	private List<Account> accounts;
	
	@ElementCollection
	private List<LibraryItem> libraryItems; 



	@OneToMany(cascade={CascadeType.ALL})
	public List<Library> getLibraries() {
		return this.libraries;
	}

	public void setLibraries(List<Library> aLibrary){
		this.libraries = aLibrary;
	}
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> anAccount){
		this.accounts = anAccount;
	}
	
	@OneToMany(cascade={CascadeType.ALL})
	public List<LibraryItem> getLibraryItems() {
		return this.libraryItems;
	}
	
	public void setLibraryItems(List<LibraryItem> aLibraryItem){
		this.libraryItems = aLibraryItem;
	}
	
	private long id; 
	
	public void setID(Long anID) {
		this.id = anID;
	}
	
	@Id
	public long getID() {
		return this.id;
	}

	

}
