package ca.mcgill.ecse321.projectgroup09.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class HeadLibrarian extends Librarian {

	//HeadLibrarian Attributes
	private Long managerIDNum;

	//HeadLibrarian Associations
	private Library library;
	
	public HeadLibrarian() {
		
	}
	
	public HeadLibrarian (String aFullName, String aLibrarianEmail, String aLibrarianPassword,
			String aLibrarianUsername, List<Schedule> aSchedules, List<Loan> aLoans, List<Booking> aBookings) {
		super(aFullName, aLibrarianEmail, aLibrarianPassword, aLibrarianUsername, aSchedules, aLoans, aBookings);
		if (this.getSchedules() == null) {
			this.setSchedules(new ArrayList<Schedule>());
		}
		if (this.getLoans() == null) {
			this.setLoans(new ArrayList<Loan>());
		}
		if (this.getBookings() == null) {
			this.setBookings(new ArrayList<Booking>());
		}
		// generate employee id number
		int max = 999999;
	    int min = 1;
	    int range = max - min + 1;
	    this.managerIDNum = (long) ((Math.random() * range) + min);
	}
	
	public void setmanagerIDNum(Long aManagerIDNum) {
		this.managerIDNum = aManagerIDNum;
	}
	
	@Column(unique = true)
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

