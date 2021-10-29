package ca.mcgill.ecse321.projectgroup09.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

//import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;

@Entity
public class Member extends Account {


	public enum LoanStatus { Active, Renewed, Overdue, Closed }


	// Member Attributes
	
	private Long libCardNumber;
	private String address;
	private boolean isResident;
	private String phoneNumber;
	private double amountOwed;
	private int activeLoans;
	private boolean isVerified;
	
	// Member Associations
	@ElementCollection
	private List<Loan> loans;
	
	@ElementCollection
	private List<Booking> bookings;

	@ElementCollection
	private List<LibraryItem> reserved;

	// Member Default Constructor, Entities must have a no-arg constructor
	public Member() {
		
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

	public void setIsResident(boolean aIsResident) {
		this.isResident = aIsResident;
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

	public void setIsVerified(boolean aIsVerified) {
		this.isVerified = aIsVerified; 
	}
	
	
	//@Transient
	public Long getLibCardNumber() {
		return this.libCardNumber;
	}

	public String getAddress() {
		return this.address;
	}

	public boolean getIsResident() {
		return this.isResident;
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

	public boolean getIsVerified() {
		return this.isVerified;
	}

	@OneToMany(cascade = {CascadeType.ALL})
	public List<Loan> getLoans() {
		return this.loans;
	}
	
	public void setLoans (List<Loan> aLoan) {
		this.loans = aLoan;
	}

	@OneToMany(cascade = {CascadeType.ALL})
	public List<Booking> getBookings() {
		return this.bookings;
	}
	
	public void setBookings(List<Booking> aBooking) {
		this.bookings = aBooking;
	}

	@OneToMany(cascade = {CascadeType.ALL}, fetch=FetchType.EAGER)
	public List<LibraryItem> getReserved() {
		return this.reserved;
	}
	
	public void setReserved(List<LibraryItem> aReserved) {
		this.reserved = aReserved;
	}
}

//package ca.mcgill.ecse321.projectgroup09.models;
//
//import javax.persistence.*;
//
//import java.sql.Time;
//import java.util.*;
//
//import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
//
//@Entity
//public class Member extends Account {
//
//	// Christos code for testing removed and changed for Umple
//	/*
//	 * private String name;
//	 * 
//	 * public void setName(String value) { this.name = value; }
//	 * 
//	 * @Id public String getName() { return this.name; }
//	 */
//
//	// ------------------------
//	// ENUMERATIONS
//	// ------------------------
//
//	// public enum LoanStatus { Active, Renewed, Overdue, Closed }
//
//	// ------------------------
//	// MEMBER VARIABLES
//	// ------------------------
//
//	// Member Attributes
//	
//	private Long libCardNumber;
//	private String address;
//	private boolean isResident;
//	private String phoneNumber;
//	private double amountOwed;
//	private int activeLoans;
//	private boolean isVerified;
//
//	// Member Associations
//	private List<Loan> loans;
//	private List<Booking> bookings;
//
//	// ------------------------
//	// CONSTRUCTOR
//	// ------------------------
//
//	public Member(String aFullName, LibraryManagement aLibraryManagement, Long aLibCardNumber, String aAddress,
//			boolean aIsResident, String aPhoneNumber, double aAmountOwed, int aActiveLoans, boolean aIsVerified) {
//		super(aFullName, aLibraryManagement);
//		libCardNumber = aLibCardNumber;
//		address = aAddress;
//		isResident = aIsResident;
//		phoneNumber = aPhoneNumber;
//		amountOwed = aAmountOwed;
//		activeLoans = aActiveLoans;
//		isVerified = aIsVerified;
//		loans = new ArrayList<Loan>();
//		bookings = new ArrayList<Booking>();
//	}
//
//	// ------------------------
//	// INTERFACE
//	// ------------------------
//
//	public boolean setLibCardNumber(Long aLibCardNumber) {
//		boolean wasSet = false;
//		libCardNumber = aLibCardNumber;
//		wasSet = true;
//		return wasSet;
//	}
//
//	public boolean setAddress(String aAddress) {
//		boolean wasSet = false;
//		address = aAddress;
//		wasSet = true;
//		return wasSet;
//	}
//
//	public boolean setIsResident(boolean aIsResident) {
//		boolean wasSet = false;
//		isResident = aIsResident;
//		wasSet = true;
//		return wasSet;
//	}
//
//	public boolean setPhoneNumber(String aPhoneNumber) {
//		boolean wasSet = false;
//		phoneNumber = aPhoneNumber;
//		wasSet = true;
//		return wasSet;
//	}
//
//	public boolean setAmountOwed(double aAmountOwed) {
//		boolean wasSet = false;
//		amountOwed = aAmountOwed;
//		wasSet = true;
//		return wasSet;
//	}
//
//	public boolean setActiveLoans(int aActiveLoans) {
//		boolean wasSet = false;
//		activeLoans = aActiveLoans;
//		wasSet = true;
//		return wasSet;
//	}
//
//	public boolean setIsVerified(boolean aIsVerified) {
//		boolean wasSet = false;
//		isVerified = aIsVerified;
//		wasSet = true;
//		return wasSet;
//	}
//	
//	@Id
//	//@Transient
//	public Long getLibCardNumber() {
//		return libCardNumber;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public boolean getIsResident() {
//		return isResident;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public double getAmountOwed() {
//		return amountOwed;
//	}
//
//	public int getActiveLoans() {
//		return activeLoans;
//	}
//
//	public boolean getIsVerified() {
//		return isVerified;
//	}
//
//	/* Code from template attribute_IsBoolean */
//	@Transient
//	public boolean isIsResident() {
//		return isResident;
//	}
//
//	/* Code from template attribute_IsBoolean */
//	@Transient
//	public boolean isIsVerified() {
//		return isVerified;
//	}
//
//	/* Code from template association_GetMany */
//	public Loan getLoan(int index) {
//		Loan aLoan = loans.get(index);
//		return aLoan;
//	}
//
//	public List<Loan> getLoans() {
//		List<Loan> newLoans = Collections.unmodifiableList(loans);
//		return newLoans;
//	}
//
//	public int numberOfLoans() {
//		int number = loans.size();
//		return number;
//	}
//
//	public boolean hasLoans() {
//		boolean has = loans.size() > 0;
//		return has;
//	}
//
//	public int indexOfLoan(Loan aLoan) {
//		int index = loans.indexOf(aLoan);
//		return index;
//	}
//
//	/* Code from template association_GetMany */
//	public Booking getBooking(int index) {
//		Booking aBooking = bookings.get(index);
//		return aBooking;
//	}
//
//	public List<Booking> getBookings() {
//		List<Booking> newBookings = Collections.unmodifiableList(bookings);
//		return newBookings;
//	}
//
//	public int numberOfBookings() {
//		int number = bookings.size();
//		return number;
//	}
//
//	public boolean hasBookings() {
//		boolean has = bookings.size() > 0;
//		return has;
//	}
//
//	public int indexOfBooking(Booking aBooking) {
//		int index = bookings.indexOf(aBooking);
//		return index;
//	}
//
//	/* Code from template association_MinimumNumberOfMethod */
//	public static int minimumNumberOfLoans() {
//		return 0;
//	}
//
//	/* Code from template association_MaximumNumberOfMethod */
//	public static int maximumNumberOfLoans() {
//		return 10;
//	}
//
//	/* Code from template association_AddOptionalNToOne */
//	public Loan addLoan(Date aBorrowedDate, Date aReturnDate, double aLateFees, LoanStatus aLoanStatus,
//			LibraryItem aLibraryItem, Librarian aLibrarian) {
//		if (numberOfLoans() >= maximumNumberOfLoans()) {
//			return null;
//		} else {
//			return new Loan(aBorrowedDate, aReturnDate, aLateFees, aLoanStatus, aLibraryItem, this, aLibrarian);
//		}
//	}
//
//	public boolean addLoan(Loan aLoan) {
//		boolean wasAdded = false;
//		if (loans.contains(aLoan)) {
//			return false;
//		}
//		if (numberOfLoans() >= maximumNumberOfLoans()) {
//			return wasAdded;
//		}
//
//		Member existingMember = aLoan.getMember();
//		boolean isNewMember = existingMember != null && !this.equals(existingMember);
//		if (isNewMember) {
//			aLoan.setMember(this);
//		} else {
//			loans.add(aLoan);
//		}
//		wasAdded = true;
//		return wasAdded;
//	}
//
//	public boolean removeLoan(Loan aLoan) {
//		boolean wasRemoved = false;
//		// Unable to remove aLoan, as it must always have a member
//		if (!this.equals(aLoan.getMember())) {
//			loans.remove(aLoan);
//			wasRemoved = true;
//		}
//		return wasRemoved;
//	}
//
//	/* Code from template association_AddIndexControlFunctions */
//	public boolean addLoanAt(Loan aLoan, int index) {
//		boolean wasAdded = false;
//		if (addLoan(aLoan)) {
//			if (index < 0) {
//				index = 0;
//			}
//			if (index > numberOfLoans()) {
//				index = numberOfLoans() - 1;
//			}
//			loans.remove(aLoan);
//			loans.add(index, aLoan);
//			wasAdded = true;
//		}
//		return wasAdded;
//	}
//
//	public boolean addOrMoveLoanAt(Loan aLoan, int index) {
//		boolean wasAdded = false;
//		if (loans.contains(aLoan)) {
//			if (index < 0) {
//				index = 0;
//			}
//			if (index > numberOfLoans()) {
//				index = numberOfLoans() - 1;
//			}
//			loans.remove(aLoan);
//			loans.add(index, aLoan);
//			wasAdded = true;
//		} else {
//			wasAdded = addLoanAt(aLoan, index);
//		}
//		return wasAdded;
//	}
//
//	/* Code from template association_MinimumNumberOfMethod */
//	public static int minimumNumberOfBookings() {
//		return 0;
//	}
//
//	/* Code from template association_AddManyToOne */
//	public Booking addBooking(Time aBookingStartTime, Time aBookingEndTime, Long aBookingID, Date aBookingDate) {
//		return new Booking(aBookingStartTime, aBookingEndTime, aBookingID, aBookingDate, this);
//	}
//
//	public boolean addBooking(Booking aBooking) {
//		boolean wasAdded = false;
//		if (bookings.contains(aBooking)) {
//			return false;
//		}
//		Member existingMember = aBooking.getMember();
//		boolean isNewMember = existingMember != null && !this.equals(existingMember);
//		if (isNewMember) {
//			aBooking.setMember(this);
//		} else {
//			bookings.add(aBooking);
//		}
//		wasAdded = true;
//		return wasAdded;
//	}
//
//	public boolean removeBooking(Booking aBooking) {
//		boolean wasRemoved = false;
//		// Unable to remove aBooking, as it must always have a member
//		if (!this.equals(aBooking.getMember())) {
//			bookings.remove(aBooking);
//			wasRemoved = true;
//		}
//		return wasRemoved;
//	}
//
//	/* Code from template association_AddIndexControlFunctions */
//	public boolean addBookingAt(Booking aBooking, int index) {
//		boolean wasAdded = false;
//		if (addBooking(aBooking)) {
//			if (index < 0) {
//				index = 0;
//			}
//			if (index > numberOfBookings()) {
//				index = numberOfBookings() - 1;
//			}
//			bookings.remove(aBooking);
//			bookings.add(index, aBooking);
//			wasAdded = true;
//		}
//		return wasAdded;
//	}
//
//	public boolean addOrMoveBookingAt(Booking aBooking, int index) {
//		boolean wasAdded = false;
//		if (bookings.contains(aBooking)) {
//			if (index < 0) {
//				index = 0;
//			}
//			if (index > numberOfBookings()) {
//				index = numberOfBookings() - 1;
//			}
//			bookings.remove(aBooking);
//			bookings.add(index, aBooking);
//			wasAdded = true;
//		} else {
//			wasAdded = addBookingAt(aBooking, index);
//		}
//		return wasAdded;
//	}
//
//	public void delete() {
//		for (int i = loans.size(); i > 0; i--) {
//			Loan aLoan = loans.get(i - 1);
//			aLoan.delete();
//		}
//		for (int i = bookings.size(); i > 0; i--) {
//			Booking aBooking = bookings.get(i - 1);
//			aBooking.delete();
//		}
//		super.delete();
//	}
//
//	public String toString() {
//		return super.toString() + "[" + "libCardNumber" + ":" + getLibCardNumber() + "," + "address" + ":"
//				+ getAddress() + "," + "isResident" + ":" + getIsResident() + "," + "phoneNumber" + ":"
//				+ getPhoneNumber() + "," + "amountOwed" + ":" + getAmountOwed() + "," + "activeLoans" + ":"
//				+ getActiveLoans() + "," + "isVerified" + ":" + getIsVerified() + "]";
//	}
//}
