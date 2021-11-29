package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;

public class HeadLibrarianDto extends LibrarianDto{
	private Long managerIDNum;
	
	
	public HeadLibrarianDto(long accountID, String fullName, String librarianEmail, String librarianPassword, String librarianUsername,
			Long employeeIDNum, List<Schedule> schedules, List<Loan> loans, List<Booking> bookings, Long managerIDNum){
		super(accountID, fullName, librarianEmail, librarianPassword, librarianUsername, employeeIDNum, schedules, loans, bookings);
		this.managerIDNum = managerIDNum;

	}
	
	public Long getManagerIDNum() {
		return this.managerIDNum;
	}
	
	public void setManagerIDNum(Long  library) {
		this.managerIDNum =  library;
	}
	

	
	public static HeadLibrarianDto convertToDto(HeadLibrarian headLibrarian) {
		HeadLibrarianDto headLibrarianDto = new HeadLibrarianDto(
				headLibrarian.getAccountID(),
				headLibrarian.getFullName(),
				headLibrarian.getLibrarianEmail(),
				headLibrarian.getLibrarianPassword(),
				headLibrarian.getLibrarianUsername(), 
				headLibrarian.getemployeeIDNumber(), 
				headLibrarian.getSchedules(),
				headLibrarian.getLoans(),
				headLibrarian.getBookings(),
				headLibrarian.getmanagerIDNum()
				);
		
		return headLibrarianDto; 

	}
}
