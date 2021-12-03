package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.Account;
import ca.mcgill.ecse321.projectgroup09.models.Library;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Schedule;


public class LibraryDto {

	private String libraryName;
	private String libraryAddress;
	private String libraryPhone;
	private String libraryEmail;

	// associations
	/** Contains account ID's, NOT lib card numbers of employee IDs. */
	private List<Long> accounts;
	private List<Long> libraryItems;
	private List<Long> schedules;
	
	public LibraryDto(String libName, String libEmail, String phoneNo, String libAddress, List<Schedule> schedules, List<Account> accounts, List<LibraryItem> libraryItems) {
		this.libraryAddress = libAddress;
		this.libraryEmail = libEmail;
		this.libraryName = libName; 
		this.libraryPhone = phoneNo;
		

		List<Long> scheduleIds = schedules.stream().map(schedule -> schedule.getscheduleID()).collect(Collectors.toList());
		this.schedules = scheduleIds;
		
		List<Long> accountIds = accounts.stream().map(account -> account.getAccountID()).collect(Collectors.toList());
		this.accounts = accountIds;
		
		List<Long> libraryItemIds = libraryItems.stream().map(libraryItem -> libraryItem.getlibraryItemID()).collect(Collectors.toList());
		this.libraryItems = libraryItemIds;
	} 
	
	public String getLibraryName()
	{
		return libraryName;
	}

	public String getLibraryAddress()
	{
		return libraryAddress;
	}

	public String getLibraryPhone()
	{
		return libraryPhone;
	}

	public String getLibraryEmail()
	{
		return libraryEmail;
	}

	/**
	 * @return the accounts
	 */
	public List<Long> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Long> accounts) {
		this.accounts = accounts;
	}

	/**
	 * @return the libraryItems
	 */
	public List<Long> getLibraryItems() {
		return libraryItems;
	}

	/**
	 * @param libraryItems the libraryItems to set
	 */
	public void setLibraryItems(List<Long> libraryItems) {
		this.libraryItems = libraryItems;
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

	
	public static LibraryDto convertToDto(Library library) {
		LibraryDto libraryDto = new LibraryDto(
				library.getLibraryName(),
				library.getLibraryEmail(),
				library.getLibraryPhone(),
				library.getLibraryAddress(), library.getSchedules(), library.getAccounts(), library.getLibraryItems()
				);
		
		return libraryDto; 
	}
}

