package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;


import ca.mcgill.ecse321.projectgroup09.models.Library;


public class LibraryDto {

	private String libraryName;
	private String libraryAddress;
	private String libraryPhone;
	private String libraryEmail;


	private List<AccountDto> accounts;
	private List<LibraryItemDto> libraryItems;
	private List<ScheduleDto> schedules;
	
	
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
	
	public static BookingDto convertToDto(Library library) {
		return new BookingDto();
	}
}

