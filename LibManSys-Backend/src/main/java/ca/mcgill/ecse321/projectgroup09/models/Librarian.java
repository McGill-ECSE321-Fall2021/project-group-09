package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import java.sql.Time;
import java.util.*;

import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Schedule.DayofWeek;

@Entity
public class Librarian extends Account {

	// ------------------------
	// ENUMERATIONS
	// ------------------------

	// public enum LoanStatus { Active, Renewed, Overdue, Closed }

	// ------------------------
	// MEMBER VARIABLES
	// ------------------------

	// Librarian Attributes
	private String librarianEmail;
	private String librarianPassword;
	private String librarianUsername;
	private Long employeeIDNum;

	// Librarian Associations
	private List<Schedule> schedules;
	private List<Loan> loans;
	private List<Booking> bookings;

	// ------------------------
	// CONSTRUCTOR
	// ------------------------

	public Librarian(String aFullName, LibraryManagement aLibraryManagement, String aLibrarianEmail,
			String aLibrarianPassword, String aLibrarianUsername, Long aemployeeIDNum) {
		super(aFullName, aLibraryManagement);
		librarianEmail = aLibrarianEmail;
		librarianPassword = aLibrarianPassword;
		librarianUsername = aLibrarianUsername;
		employeeIDNum = aemployeeIDNum;
		schedules = new ArrayList<Schedule>();
		loans = new ArrayList<Loan>();
		bookings = new ArrayList<Booking>();
	}

	// ------------------------
	// INTERFACE
	// ------------------------

	public boolean setLibrarianEmail(String aLibrarianEmail) {
		boolean wasSet = false;
		librarianEmail = aLibrarianEmail;
		wasSet = true;
		return wasSet;
	}

	public boolean setLibrarianPassword(String aLibrarianPassword) {
		boolean wasSet = false;
		librarianPassword = aLibrarianPassword;
		wasSet = true;
		return wasSet;
	}

	public boolean setLibrarianUsername(String aLibrarianUsername) {
		boolean wasSet = false;
		librarianUsername = aLibrarianUsername;
		wasSet = true;
		return wasSet;
	}

	public boolean setemployeeIDNum(Long aemployeeIDNum) {
		boolean wasSet = false;
		employeeIDNum = aemployeeIDNum;
		wasSet = true;
		return wasSet;
	}

	public String getLibrarianEmail() {
		return librarianEmail;
	}

	public String getLibrarianPassword() {
		return librarianPassword;
	}

	public String getLibrarianUsername() {
		return librarianUsername;
	}
	
	@Id
	public Long getemployeeIDNum() {
		return employeeIDNum;
	}

	/* Code from template association_GetMany */
	public Schedule getSchedule(int index) {
		Schedule aSchedule = schedules.get(index);
		return aSchedule;
	}

	public List<Schedule> getSchedules() {
		List<Schedule> newSchedules = Collections.unmodifiableList(schedules);
		return newSchedules;
	}

	public int numberOfSchedules() {
		int number = schedules.size();
		return number;
	}

	public boolean hasSchedules() {
		boolean has = schedules.size() > 0;
		return has;
	}

	public int indexOfSchedule(Schedule aSchedule) {
		int index = schedules.indexOf(aSchedule);
		return index;
	}

	/* Code from template association_GetMany */
	public Loan getLoan(int index) {
		Loan aLoan = loans.get(index);
		return aLoan;
	}

	public List<Loan> getLoans() {
		List<Loan> newLoans = Collections.unmodifiableList(loans);
		return newLoans;
	}

	public int numberOfLoans() {
		int number = loans.size();
		return number;
	}

	public boolean hasLoans() {
		boolean has = loans.size() > 0;
		return has;
	}

	public int indexOfLoan(Loan aLoan) {
		int index = loans.indexOf(aLoan);
		return index;
	}

	/* Code from template association_GetMany */
	public Booking getBooking(int index) {
		Booking aBooking = bookings.get(index);
		return aBooking;
	}

	public List<Booking> getBookings() {
		List<Booking> newBookings = Collections.unmodifiableList(bookings);
		return newBookings;
	}

	public int numberOfBookings() {
		int number = bookings.size();
		return number;
	}

	public boolean hasBookings() {
		boolean has = bookings.size() > 0;
		return has;
	}

	public int indexOfBooking(Booking aBooking) {
		int index = bookings.indexOf(aBooking);
		return index;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfSchedules() {
		return 0;
	}

	/* Code from template association_MaximumNumberOfMethod */
	public static int maximumNumberOfSchedules() {
		return 7;
	}

	/* Code from template association_AddOptionalNToOne */
	public Schedule addSchedule(Long aScheduledId, Time aOpeningTime, Time aClosingTime, DayofWeek aDayofWeek) {
		if (numberOfSchedules() >= maximumNumberOfSchedules()) {
			return null;
		} else {
			return new Schedule(aScheduledId, aOpeningTime, aClosingTime, aDayofWeek, this);
		}
	}

	public boolean addSchedule(Schedule aSchedule) {
		boolean wasAdded = false;
		if (schedules.contains(aSchedule)) {
			return false;
		}
		if (numberOfSchedules() >= maximumNumberOfSchedules()) {
			return wasAdded;
		}

		Librarian existingLibrarian = aSchedule.getLibrarian();
		boolean isNewLibrarian = existingLibrarian != null && !this.equals(existingLibrarian);
		if (isNewLibrarian) {
			aSchedule.setLibrarian(this);
		} else {
			schedules.add(aSchedule);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeSchedule(Schedule aSchedule) {
		boolean wasRemoved = false;
		// Unable to remove aSchedule, as it must always have a librarian
		if (!this.equals(aSchedule.getLibrarian())) {
			schedules.remove(aSchedule);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addScheduleAt(Schedule aSchedule, int index) {
		boolean wasAdded = false;
		if (addSchedule(aSchedule)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfSchedules()) {
				index = numberOfSchedules() - 1;
			}
			schedules.remove(aSchedule);
			schedules.add(index, aSchedule);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveScheduleAt(Schedule aSchedule, int index) {
		boolean wasAdded = false;
		if (schedules.contains(aSchedule)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfSchedules()) {
				index = numberOfSchedules() - 1;
			}
			schedules.remove(aSchedule);
			schedules.add(index, aSchedule);
			wasAdded = true;
		} else {
			wasAdded = addScheduleAt(aSchedule, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfLoans() {
		return 0;
	}

	/* Code from template association_AddManyToOne */
	public Loan addLoan(Date aBorrowedDate, Date aReturnDate, double aLateFees, LoanStatus aLoanStatus,
			LibraryItem aLibraryItem, Member aMember) {
		return new Loan(aBorrowedDate, aReturnDate, aLateFees, aLoanStatus, aLibraryItem, aMember, this);
	}

	public boolean addLoan(Loan aLoan) {
		boolean wasAdded = false;
		if (loans.contains(aLoan)) {
			return false;
		}
		Librarian existingLibrarian = aLoan.getLibrarian();
		boolean isNewLibrarian = existingLibrarian != null && !this.equals(existingLibrarian);
		if (isNewLibrarian) {
			aLoan.setLibrarian(this);
		} else {
			loans.add(aLoan);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeLoan(Loan aLoan) {
		boolean wasRemoved = false;
		// Unable to remove aLoan, as it must always have a librarian
		if (!this.equals(aLoan.getLibrarian())) {
			loans.remove(aLoan);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addLoanAt(Loan aLoan, int index) {
		boolean wasAdded = false;
		if (addLoan(aLoan)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfLoans()) {
				index = numberOfLoans() - 1;
			}
			loans.remove(aLoan);
			loans.add(index, aLoan);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveLoanAt(Loan aLoan, int index) {
		boolean wasAdded = false;
		if (loans.contains(aLoan)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfLoans()) {
				index = numberOfLoans() - 1;
			}
			loans.remove(aLoan);
			loans.add(index, aLoan);
			wasAdded = true;
		} else {
			wasAdded = addLoanAt(aLoan, index);
		}
		return wasAdded;
	}

	/* Code from template association_MinimumNumberOfMethod */
	public static int minimumNumberOfBookings() {
		return 0;
	}

	/* Code from template association_AddManyToOptionalOne */
	public boolean addBooking(Booking aBooking) {
		boolean wasAdded = false;
		if (bookings.contains(aBooking)) {
			return false;
		}
		Librarian existingLibrarian = aBooking.getLibrarian();
		if (existingLibrarian == null) {
			aBooking.setLibrarian(this);
		} else if (!this.equals(existingLibrarian)) {
			existingLibrarian.removeBooking(aBooking);
			addBooking(aBooking);
		} else {
			bookings.add(aBooking);
		}
		wasAdded = true;
		return wasAdded;
	}

	public boolean removeBooking(Booking aBooking) {
		boolean wasRemoved = false;
		if (bookings.contains(aBooking)) {
			bookings.remove(aBooking);
			aBooking.setLibrarian(null);
			wasRemoved = true;
		}
		return wasRemoved;
	}

	/* Code from template association_AddIndexControlFunctions */
	public boolean addBookingAt(Booking aBooking, int index) {
		boolean wasAdded = false;
		if (addBooking(aBooking)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfBookings()) {
				index = numberOfBookings() - 1;
			}
			bookings.remove(aBooking);
			bookings.add(index, aBooking);
			wasAdded = true;
		}
		return wasAdded;
	}

	public boolean addOrMoveBookingAt(Booking aBooking, int index) {
		boolean wasAdded = false;
		if (bookings.contains(aBooking)) {
			if (index < 0) {
				index = 0;
			}
			if (index > numberOfBookings()) {
				index = numberOfBookings() - 1;
			}
			bookings.remove(aBooking);
			bookings.add(index, aBooking);
			wasAdded = true;
		} else {
			wasAdded = addBookingAt(aBooking, index);
		}
		return wasAdded;
	}

	public void delete() {
		for (int i = schedules.size(); i > 0; i--) {
			Schedule aSchedule = schedules.get(i - 1);
			aSchedule.delete();
		}
		for (int i = loans.size(); i > 0; i--) {
			Loan aLoan = loans.get(i - 1);
			aLoan.delete();
		}
		while (!bookings.isEmpty()) {
			bookings.get(0).setLibrarian(null);
		}
		super.delete();
	}

	public String toString() {
		return super.toString() + "[" + "librarianEmail" + ":" + getLibrarianEmail() + "," + "librarianPassword" + ":"
				+ getLibrarianPassword() + "," + "librarianUsername" + ":" + getLibrarianUsername() + ","
				+ "employeeIDNum" + ":" + getemployeeIDNum() + "]";
	}
}
