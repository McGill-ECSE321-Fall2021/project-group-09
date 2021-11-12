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


	private List<AccountDto> accounts;
	private List<LibraryItemDto> libraryItems;
	private List<ScheduleDto> schedules;
	
	public LibraryDto(String libName, String libEmail, String phoneNo, String libAddress, List<Schedule> schedules, List<Account> accounts, List<LibraryItem> libraryItems) {
		this.libraryAddress = libAddress;
		this.libraryEmail = libEmail;
		this.libraryName = libName; 
		this.libraryPhone = phoneNo;
		

		List<ScheduleDto> scheduleDto = schedules.stream().map(schedule -> ScheduleDto.convertToDto(schedule)).collect(Collectors.toList());
		this.schedules = scheduleDto;
		
//		List<AccountDto> accountDto = accounts.stream().map(account -> AccountDto.convertToDto(account)).collect(Collectors.toList());
//		this.accounts = accountDto;
		
		List<LibraryItemDto> libraryItemDto = libraryItems.stream().map(libraryItem -> LibraryItemDto.convertToDto(libraryItem)).collect(Collectors.toList());
		this.libraryItems = libraryItemDto;
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

	public List<ScheduleDto> getSchedules()
	{
		return schedules;
	}

	public void setSchedules(List<ScheduleDto> schedules)
	{
		this.schedules = schedules;
	}

	public List<AccountDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountDto> accounts){
		this.accounts = accounts;
	}

	public List<LibraryItemDto> getLibraryItems() {
		return libraryItems;
	}

	public void setLibraryItems(List<LibraryItemDto> aLibraryItem){
		this.libraryItems = aLibraryItem;
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

