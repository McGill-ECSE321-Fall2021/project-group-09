package ca.mcgill.ecse321.projectgroup09.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

//import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;

@Entity
public class Member extends Account {

	// Member Attributes
	private Long libCardNumber;
	private String address;
	private String phoneNumber;
	private double amountOwed;
	private int activeLoans;
	private boolean isVerifiedResident;
	
	// Member Associations
	@ElementCollection
	private List<Loan> loans;
	
	@ElementCollection
	private List<Booking> bookings;

	@ElementCollection
	private List<LibraryItem> reserved;

	/**
	 * Member Default Constructor, Entities must have a no-arg constructor
	 */
	public Member() {
		loans = new ArrayList<Loan>();
		bookings = new ArrayList<Booking>();
		reserved = new ArrayList<LibraryItem>();
		this.amountOwed = 0;
		this.activeLoans = 0;
		this.isVerifiedResident = false;
	}
	
	public Member (Long aLibCardNumber) {
		this.loans = new ArrayList<Loan>();
		this.bookings = new ArrayList<Booking>();
		this.reserved = new ArrayList<LibraryItem>();
		this.libCardNumber = aLibCardNumber;
	}

	@Override
	public void setFullName(String aFullName) {
		super.setFullName(aFullName); 
	}
	
	@Override
	public String getFullName() {
		return super.getFullName();
	}

	public void setLibCardNumber(Long aLibCardNumber) {
		this.libCardNumber = aLibCardNumber;
	}

	public void setAddress(String anAddress) {
		this.address = anAddress;
	}

	public void setPhoneNumber(String aPhoneNumber) {
		this.phoneNumber = aPhoneNumber;
	}

	public void setAmountOwed(double anAmountOwed) {
		this.amountOwed = anAmountOwed;
	}

	public void setActiveLoans(int anActiveLoans) {
		this.activeLoans = anActiveLoans;
	}

	public void setIsVerifiedResident(boolean aisVerifiedResident) {
		this.isVerifiedResident = aisVerifiedResident; 
	}
	
	// see accountId
	//@Id
	public Long getLibCardNumber() {
		return this.libCardNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public double getAmountOwed() {
		return this.amountOwed;
	}

	public int getActiveLoans() {
		return this.activeLoans;
	}

	public boolean getIsVerifiedResident() {
		return this.isVerifiedResident;
	}

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "member")
	public List<Loan> getLoans() {
		return this.loans;
	}
	
	public void setLoans (List<Loan> aLoan) {
		this.loans = aLoan;
	}

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "member")
	public List<Booking> getBookings() {
		return this.bookings;
	}
	
	public void setBookings(List<Booking> aBooking) {
		this.bookings = aBooking;
	}

	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "member")
	public List<LibraryItem> getReserved() {
		return this.reserved;
	}
	
	public void setReserved(List<LibraryItem> aReserved) {
		this.reserved = aReserved;
	}
}

