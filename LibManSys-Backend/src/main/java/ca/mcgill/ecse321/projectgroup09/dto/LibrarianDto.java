package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.Librarian;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;


public class LibrarianDto extends AccountDto {

	private String librarianEmail;
	private String librarianPassword;
	private String librarianUsername;
	private Long employeeIDNumber;

	// associations
	private List<Long> schedules;
	private List<Long> loans;
	private List<Long> bookings;


	public LibrarianDto(long accountID, String fullName, String librarianEmail, String librarianPassword, String librarianUsername,
			Long employeeIDNum, List<Schedule> schedules, List<Loan> loans, List<Booking> bookings){
		super(accountID, fullName);
		this.librarianEmail = librarianEmail;
		this.librarianPassword = librarianPassword;
		this.librarianUsername = librarianUsername;
		this.employeeIDNumber = employeeIDNum;

		List<Long> scheduleIds = schedules.stream().map(schedule -> schedule.getscheduleID()).collect(Collectors.toList());
		this.schedules = scheduleIds;
		List<Long> loanIds = loans.stream().map(loan -> loan.getLoanID()).collect(Collectors.toList());
		this.loans = loanIds;
		List <Long> bookingIds = bookings.stream().map(booking -> booking.getBookingID()).collect(Collectors.toList());
		this.bookings = bookingIds;
	}

	public String getLibrarianEmail() {
		return librarianEmail;
	}
	public void setLibrarianEmail(String librarianEmail) {
		this.librarianEmail = librarianEmail;
	}
	public String getLibrarianPassword() {
		return librarianPassword;
	}
	public void setLibrarianPassword(String librarianPassword) {
		this.librarianPassword = librarianPassword;
	}
	public String getLibrarianUsername() {
		return librarianUsername;
	}
	public void setLibrarianUsername(String librarianUsername) {
		this.librarianUsername = librarianUsername;
	}
	public Long getEmployeeIDNumber() {
		return employeeIDNumber;
	}
	public void setEmployeeIDNumber(Long employeeIDNumber) {
		this.employeeIDNumber = employeeIDNumber;
	}

	/**
	 * @return the schedules
	 */
	public List<Long> getSchedules() {
		return schedules;
	}

	/**
	 * @param schedules the schedules to set
	 */
	public void setSchedules(List<Long> schedules) {
		this.schedules = schedules;
	}

	/**
	 * @return the loans
	 */
	public List<Long> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<Long> loans) {
		this.loans = loans;
	}

	/**
	 * @return the bookings
	 */
	public List<Long> getBookings() {
		return bookings;
	}

	/**
	 * @param bookings the bookings to set
	 */
	public void setBookings(List<Long> bookings) {
		this.bookings = bookings;
	}	
	
	
	public static LibrarianDto convertToDto(Librarian librarian) {
		LibrarianDto ldto = new LibrarianDto(
				librarian.getAccountID(),
				librarian.getFullName(),
				librarian.getLibrarianEmail(),
				librarian.getLibrarianPassword(),
				librarian.getLibrarianUsername(), 
				librarian.getemployeeIDNumber(), 
				librarian.getSchedules(),
				librarian.getLoans(),
				librarian.getBookings()
				);
		return ldto;
	}

	public static List<LibrarianDto> convertToDtos(List<Librarian> librarians) {
		List<LibrarianDto> librarianDtos = librarians.stream().map(l -> LibrarianDto.convertToDto(l)).collect(Collectors.toList());
		return librarianDtos;
	}

}
