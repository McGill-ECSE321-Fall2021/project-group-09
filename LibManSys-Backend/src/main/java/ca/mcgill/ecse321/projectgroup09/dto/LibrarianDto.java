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
	private Long employeeIDNum;

	private List<ScheduleDto> schedules;
	private List<LoanDto> loans;
	private List<BookingDto> bookings;


	public LibrarianDto(long accountID, String fullName, String librarianEmail, String librarianPassword, String librarianUsername,
			Long employeeIDNum, List<Schedule> schedules, List<Loan> loans, List<Booking> bookings){
		super(accountID, fullName);
		this.librarianEmail = librarianEmail;
		this.librarianPassword = librarianPassword;
		this.librarianUsername = librarianUsername;
		this.employeeIDNum = employeeIDNum;

		List<ScheduleDto> scheduleDto = schedules.stream().map(schedule -> ScheduleDto.convertToDto(schedule)).collect(Collectors.toList());
		this.schedules = scheduleDto;
		List<LoanDto> loanDto = loans.stream().map(loan -> LoanDto.convertToDto(loan)).collect(Collectors.toList());
		this.loans = loanDto;
		List <BookingDto> bookingDto = bookings.stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
		this.bookings = bookingDto;
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
	public Long getEmployeeIDNum() {
		return employeeIDNum;
	}
	public void setEmployeeIDNum(Long employeeIDNum) {
		this.employeeIDNum = employeeIDNum;
	}
	public List<ScheduleDto> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<ScheduleDto> schedules) {
		this.schedules = schedules;
	}
	public List<LoanDto> getLoans() {
		return loans;
	}
	public void setLoans(List<LoanDto> loans) {
		this.loans = loans;
	}
	public List<BookingDto> getBookings() {
		return bookings;
	}
	public void setBookings(List<BookingDto> bookings) {
		this.bookings = bookings;
	}

	public static LibrarianDto convertToDto(Librarian librarian) {
		LibrarianDto ldto = new LibrarianDto(
				librarian.getAccountId(),
				librarian.getFullName(),
				librarian.getLibrarianEmail(),
				librarian.getLibrarianPassword(),
				librarian.getLibrarianUsername(), 
				librarian.getemployeeIDNum(), 
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
