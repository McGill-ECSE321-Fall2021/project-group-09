package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.ElementCollection;

//JPA tags added
 

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.util.List;

@Entity
public class Library
{

	  //Library Attributes
	  private String libraryName;
	  private String libraryAddress;
	  private String libraryPhone;
	  private String libraryEmail;

	  //Library Associations
	  private LibraryManagement libraryManagement;
	  
	  @ElementCollection
	  private List<Booking> bookings;
	  
	  @ElementCollection
	  private List<Schedule> schedules;
	  
	  @ElementCollection
	  private HeadLibrarian headLibrarian;
	  
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
	 
	  @OneToOne
	  public LibraryManagement getLibraryManagement()
	  {
	    return this.libraryManagement;
	  }
	 
	  @OneToMany
	  public List<Booking> getBookings()
	  {
	    return this.bookings;
	  }


	  @OneToMany
	  public List<Schedule> getSchedules()
	  {
	    return this.schedules;
	  }

	  @OneToMany
	  public void setSchedules(List<Schedule> schedules)
	  {
	    this.schedules = schedules;
	  }

	  public void setHeadLibrarian(HeadLibrarian aHeadLibrarian)
	  {
	    this.headLibrarian = aHeadLibrarian;
	  }
	  

	  
	  @OneToOne
	  public HeadLibrarian getHeadLibrarian()
	  {
	    return this.headLibrarian;
	  }
	 

	  public void setBookings(List<Booking> aNewBooking) {
			this.bookings = aNewBooking;
		}
	  
	  public void setLibraryManagement(LibraryManagement aLibraryManagement)
	  {
	    this.libraryManagement = aLibraryManagement;
	    }
}  
		

		
	
   
