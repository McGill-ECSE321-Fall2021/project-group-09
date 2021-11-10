package ca.mcgill.ecse321.projectgroup09.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Librarian extends Account {

	
	public enum LoanStatus { Active, Renewed, Overdue, Closed }

	
	// Librarian Attributes
	private String librarianEmail;
	private String librarianPassword;
	private String librarianUsername;
	private Long employeeIDNum;

	// Librarian Associations
	@ElementCollection
	private List<Schedule> schedules;
	
	@ElementCollection
	private List<Loan> loans;
	
	@ElementCollection
	private List<Booking> bookings;

	/**
	 * Default no-arg constructor.
	 * Initializes collections that represent associations of Librarian.
	 */
	public Librarian() {
		this.schedules = new ArrayList<Schedule>();
		this.loans = new ArrayList<Loan>();
		this.bookings = new ArrayList<Booking>();
	}
	
	@Override
	public void setFullName(String aFullName) {
		super.setFullName(aFullName); 
	}
	
	@Override
	public String getFullName() {
		return super.getFullName();
	}

	public void setLibrarianEmail(String aLibrarianEmail) {
		this.librarianEmail = aLibrarianEmail;
	}

	public void setLibrarianPassword(String aLibrarianPassword) {
		this.librarianPassword = aLibrarianPassword;
	}

	public void setLibrarianUsername(String aLibrarianUsername) {
		this.librarianUsername = aLibrarianUsername;
	}

	public void setemployeeIDNum(Long anEmployeeIDNum) {
		this.employeeIDNum = anEmployeeIDNum;
	}

	public String getLibrarianEmail() {
		return this.librarianEmail;
	}

	public String getLibrarianPassword() {
		return this.librarianPassword;
	}

	public String getLibrarianUsername() {
		return this.librarianUsername;
	}
	
//	@Id
	public Long getemployeeIDNum() {
		return this.employeeIDNum;
	}

//	@OneToMany
//	public Schedule getSchedule(int index) {
//		return this.schedule;
//	}

	@OneToMany
	public List<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules (List<Schedule> aSchedule) {
		this.schedules = aSchedule;
	}

	@OneToMany
	public List<Loan> getLoans() {
		return this.loans;
	}
	
	public void setLoans(List<Loan> aLoan) {
		this.loans = aLoan;
	}


	@OneToMany
	public List<Booking> getBookings() {
		return this.bookings;
	}
	
	public void setBookings(List<Booking> aBooking) {
		this.bookings = aBooking;
	}


	
}
