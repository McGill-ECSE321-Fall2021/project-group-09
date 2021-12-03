package ca.mcgill.ecse321.projectgroup09.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Librarian extends Account {
	
	// Librarian Attributes
	private String librarianEmail;
	private String librarianPassword;
	private String librarianUsername;
	private Long employeeIDNumber;

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
	
	/**
	 * All argument constructor.
	 * This inializes a Librarian object with fields equal to input parameters, as well
	 * as a randomly generated employee ID number.
	 * @param aFullName
	 * @param aLibrarianEmail
	 * @param aLibrarianPassword
	 * @param aLibrarianUsername
	 * @param aSchedules if null is passed as input, then a empty list is created for schedules
	 * @param aLoans if null is passed as input, then a empty list is created for loan
	 * @param aBookings if null is passed as input, then a empty list is created for bookings
	 */
	public Librarian(String aFullName, String aLibrarianEmail, String aLibrarianPassword,
			String aLibrarianUsername, List<Schedule> aSchedules, List<Loan> aLoans, List<Booking> aBookings) {
		// generate employee id number
		  int max = 999999;
	        int min = 1;
	        int range = max - min + 1;
	        final Long employeeIdNumber = (long) ((Math.random() * range) + min);
	//	final Long employeeIdNumber = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
		// set fields
		this.setFullName(aFullName);
		this.setLibrarianEmail(aLibrarianEmail);
		this.setLibrarianPassword(aLibrarianPassword);
		this.setLibrarianUsername(aLibrarianUsername);
		this.setemployeeIDNumber(employeeIdNumber);
		this.setSchedules(aSchedules == null ? new ArrayList<Schedule>() : aSchedules);
		this.setLoans(aLoans == null ? new ArrayList<Loan>() : aLoans);
		this.setBookings(aBookings == null ? new ArrayList<Booking>() : aBookings);
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

	public void setemployeeIDNumber(Long anEmployeeIDNum) {
		this.employeeIDNumber = anEmployeeIDNum;
	}

	public String getLibrarianEmail() {
		return this.librarianEmail;
	}

	public String getLibrarianPassword() {
		return this.librarianPassword;
	}

	@Column(unique = true)
	public String getLibrarianUsername() {
		return this.librarianUsername;
	}
	
	@Column(unique = true)
	public Long getemployeeIDNumber() {
		return this.employeeIDNumber;
	}

//	@OneToMany
//	public Schedule getSchedule(int index) {
//		return this.schedule;
//	}

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "librarian")
	public List<Schedule> getSchedules() {
		return this.schedules;
	}

	public void setSchedules (List<Schedule> aSchedule) {
		this.schedules = aSchedule;
	}

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "librarian")
	public List<Loan> getLoans() {
		return this.loans;
	}
	
	public void setLoans(List<Loan> aLoan) {
		this.loans = aLoan;
	}


	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "librarian")
	public List<Booking> getBookings() {
		return this.bookings;
	}
	
	public void setBookings(List<Booking> aBooking) {
		this.bookings = aBooking;
	}


	
}
