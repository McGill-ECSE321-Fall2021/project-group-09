package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import java.util.*;

//JPA tags added


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.sql.Time;
import java.util.List;

@Entity
public class Library
{
	// Default Library Hours, used in scheduleService.createDefaultLibraryScheduleList()
	public static final Time DEFAULT_WEEKDAY_OPENING_TIME = Time.valueOf("8:00:00");
	public static final Time DEFAULT_WEEKDAY_CLOSING_TIME = Time.valueOf("18:00:00");
	public static final Time DEFAULT_WEEKEND_OPENING_TIME = Time.valueOf("10:00:00");
	public static final Time DEFAULT_WEEKEND_CLOSING_TIME = Time.valueOf("14:00:00");

	//Library Attributes
	private String libraryName;
	private String libraryAddress;
	private String libraryPhone;
	private String libraryEmail;


	//Library Associations
	@ElementCollection
	private List<Account> accounts;

	//Composition
	@ElementCollection
	private List<LibraryItem> libraryItems; 

	
	//Composition
	@ElementCollection
	private List<Schedule> schedules;


	
	public Library() {
		this.schedules = new ArrayList<Schedule>();
		this.libraryItems = new ArrayList<LibraryItem>();
		this.accounts = new ArrayList<Account>();
	}
	
	//Methods
	public void setLibraryName(String aLibraryName)
	{
		this.libraryName = aLibraryName;
	}

	public void setLibraryAddress(String aLibraryAddress)
	{
		this.libraryAddress = aLibraryAddress;
	}

	public void setLibraryPhone(String aLibraryPhone)
	{
		this.libraryPhone = aLibraryPhone;
	}

	public void setLibraryEmail(String aLibraryEmail)
	{
		this.libraryEmail = aLibraryEmail; 
	}

	@Id
	public String getLibraryName()
	{
		return this.libraryName;
	}

	public String getLibraryAddress()
	{
		return this.libraryAddress;
	}

	public String getLibraryPhone()
	{
		return this.libraryPhone;
	}

	public String getLibraryEmail()
	{
		return this.libraryEmail;
	}

	
	//Association Methods
	@OneToMany(cascade = {CascadeType.ALL})
	public List<Schedule> getSchedules()
	{
		return this.schedules;
	}

	public void setSchedules(List<Schedule> schedules)
	{
		this.schedules = schedules;
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> anAccount){
		this.accounts = anAccount;
	}

	@OneToMany(cascade={CascadeType.ALL})
	public List<LibraryItem> getLibraryItems() {
		return this.libraryItems;
	}

	public void setLibraryItems(List<LibraryItem> aLibraryItem){
		this.libraryItems = aLibraryItem;
	}



}  





