package ca.mcgill.ecse321.projectgroup09.models;

//Sneha 

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * @author Omar :) :)
 */

@Table(name = "library")
@Entity
public class Library
{

	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------

	  //Library Attributes
	  private String libraryName;
	  private String libraryAddress;
	  private String libraryPhone;
	  private String librarymail;

	  //Library Associations
	  private LibraryManagement libraryManagement;
	  private List<Booking> bookings;
	  private List<Schedule> schedules;
	  private HeadLibrarian headLibrarian;

	  //------------------------
	  // CONSTRUCTOR
	  //------------------------

	  public Library(String aLibraryName, String aLibraryAddress, String aLibraryPhone, String aLibrarymail, LibraryManagement aLibraryManagement, HeadLibrarian aHeadLibrarian, Schedule... allSchedules)
	  {
	    libraryName = aLibraryName;
	    libraryAddress = aLibraryAddress;
	    libraryPhone = aLibraryPhone;
	    librarymail = aLibrarymail;
	    boolean didAddLibraryManagement = setLibraryManagement(aLibraryManagement);
	    if (!didAddLibraryManagement)
	    {
	      throw new RuntimeException("Unable to create library due to libraryManagement. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
	    }
	    bookings = new ArrayList<Booking>();
	    schedules = new ArrayList<Schedule>();
	    boolean didAddSchedules = setSchedules(allSchedules);
	    if (!didAddSchedules)
	    {
	      throw new RuntimeException("Unable to create Library, must have 7 schedules. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
	    }
	    if (aHeadLibrarian == null || aHeadLibrarian.getLibrary() != null)
	    {
	      throw new RuntimeException("Unable to create Library due to aHeadLibrarian. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
	    }
	    headLibrarian = aHeadLibrarian;
	  }

	  public Library(String aLibraryName, String aLibraryAddress, String aLibraryPhone, String aLibrarymail, LibraryManagement aLibraryManagement, String aFullNameForHeadLibrarian, LibraryManagement aLibraryManagementForHeadLibrarian, String aLibrarianEmailForHeadLibrarian, String aLibrarianPasswordForHeadLibrarian, String aLibrarianUsernameForHeadLibrarian, int aEmployeeIdNumForHeadLibrarian, int aManagerIdNumForHeadLibrarian, Schedule... allSchedules)
	  {
	    libraryName = aLibraryName;
	    libraryAddress = aLibraryAddress;
	    libraryPhone = aLibraryPhone;
	    librarymail = aLibrarymail;
	    boolean didAddLibraryManagement = setLibraryManagement(aLibraryManagement);
	    if (!didAddLibraryManagement)
	    {
	      throw new RuntimeException("Unable to create library due to libraryManagement. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
	    }
	    bookings = new ArrayList<Booking>();
	    schedules = new ArrayList<Schedule>();
	    headLibrarian = new HeadLibrarian(aFullNameForHeadLibrarian, aLibraryManagementForHeadLibrarian, aLibrarianEmailForHeadLibrarian, aLibrarianPasswordForHeadLibrarian, aLibrarianUsernameForHeadLibrarian, aEmployeeIdNumForHeadLibrarian, aManagerIdNumForHeadLibrarian, this);
	  }

	  //------------------------
	  // INTERFACE
	  //------------------------

	  public boolean setLibraryName(String aLibraryName)
	  {
	    boolean wasSet = false;
	    libraryName = aLibraryName;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setLibraryAddress(String aLibraryAddress)
	  {
	    boolean wasSet = false;
	    libraryAddress = aLibraryAddress;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setLibraryPhone(String aLibraryPhone)
	  {
	    boolean wasSet = false;
	    libraryPhone = aLibraryPhone;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setLibrarymail(String aLibrarymail)
	  {
	    boolean wasSet = false;
	    librarymail = aLibrarymail;
	    wasSet = true;
	    return wasSet;
	  }

	  @Id
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

	  public String getLibrarymail()
	  {
	    return librarymail;
	  }
	 
	  @OneToOne
	  public LibraryManagement getLibraryManagement()
	  {
	    return libraryManagement;
	  }
	  /* Code from template association_GetMany */
	  public Booking getBooking(int index)
	  {
	    Booking aBooking = bookings.get(index);
	    return aBooking;
	  }

	  @OneToMany
	  public List<Booking> getBookings()
	  {
	    List<Booking> newBookings = Collections.unmodifiableList(bookings);
	    return newBookings;
	  }

	  public int numberOfBookings()
	  {
	    int number = bookings.size();
	    return number;
	  }

	  public boolean hasBookings()
	  {
	    boolean has = bookings.size() > 0;
	    return has;
	  }

	  public int indexOfBooking(Booking aBooking)
	  {
	    int index = bookings.indexOf(aBooking);
	    return index;
	  }
	  
	  @OneToOne
	  public Schedule getSchedule(int index)
	  {
	    Schedule aSchedule = schedules.get(index);
	    return aSchedule;
	  }


	  public List<Schedule> getSchedules()
	  {
	    List<Schedule> newSchedules = Collections.unmodifiableList(schedules);
	    return newSchedules;
	  }

	  public int numberOfSchedules()
	  {
	    int number = schedules.size();
	    return number;
	  }

	  public boolean hasSchedules()
	  {
	    boolean has = schedules.size() > 0;
	    return has;
	  }

	  public int indexOfSchedule(Schedule aSchedule)
	  {
	    int index = schedules.indexOf(aSchedule);
	    return index;
	  }
	  
	  @OneToOne
	  public HeadLibrarian getHeadLibrarian()
	  {
	    return headLibrarian;
	  }
	  /* Code from template association_SetOneToMany */
	  public boolean setLibraryManagement(LibraryManagement aLibraryManagement)
	  {
	    boolean wasSet = false;
	    if (aLibraryManagement == null)
	    {
	      return wasSet;
	    }

	    LibraryManagement existingLibraryManagement = libraryManagement;
	    libraryManagement = aLibraryManagement;
	    if (existingLibraryManagement != null && !existingLibraryManagement.equals(aLibraryManagement))
	    {
	      existingLibraryManagement.removeLibrary(this);
	    }
	    libraryManagement.addLibrary(this);
	    wasSet = true;
	    return wasSet;
	  }
	  /* Code from template association_MinimumNumberOfMethod */
	  public static int minimumNumberOfBookings()
	  {
	    return 0;
	  }
	  /* Code from template association_AddUnidirectionalMany */
	  public boolean addBooking(Booking aBooking)
	  {
	    boolean wasAdded = false;
	    if (bookings.contains(aBooking)) { return false; }
	    bookings.add(aBooking);
	    wasAdded = true;
	    return wasAdded;
	  }

	  public boolean removeBooking(Booking aBooking)
	  {
	    boolean wasRemoved = false;
	    if (bookings.contains(aBooking))
	    {
	      bookings.remove(aBooking);
	      wasRemoved = true;
	    }
	    return wasRemoved;
	  }
	  /* Code from template association_AddIndexControlFunctions */
	  public boolean addBookingAt(Booking aBooking, int index)
	  {  
	    boolean wasAdded = false;
	    if(addBooking(aBooking))
	    {
	      if(index < 0 ) { index = 0; }
	      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
	      bookings.remove(aBooking);
	      bookings.add(index, aBooking);
	      wasAdded = true;
	    }
	    return wasAdded;
	  }

	  public boolean addOrMoveBookingAt(Booking aBooking, int index)
	  {
	    boolean wasAdded = false;
	    if(bookings.contains(aBooking))
	    {
	      if(index < 0 ) { index = 0; }
	      if(index > numberOfBookings()) { index = numberOfBookings() - 1; }
	      bookings.remove(aBooking);
	      bookings.add(index, aBooking);
	      wasAdded = true;
	    } 
	    else 
	    {
	      wasAdded = addBookingAt(aBooking, index);
	    }
	    return wasAdded;
	  }
	  /* Code from template association_RequiredNumberOfMethod */
	  public static int requiredNumberOfSchedules()
	  {
	    return 7;
	  }
	  /* Code from template association_MinimumNumberOfMethod */
	  public static int minimumNumberOfSchedules()
	  {
	    return 7;
	  }
	  /* Code from template association_MaximumNumberOfMethod */
	  public static int maximumNumberOfSchedules()
	  {
	    return 7;
	  }
	  /* Code from template association_SetUnidirectionalN */
	  public boolean setSchedules(Schedule... newSchedules)
	  {
	    boolean wasSet = false;
	    ArrayList<Schedule> verifiedSchedules = new ArrayList<Schedule>();
	    for (Schedule aSchedule : newSchedules)
	    {
	      if (verifiedSchedules.contains(aSchedule))
	      {
	        continue;
	      }
	      verifiedSchedules.add(aSchedule);
	    }

	    if (verifiedSchedules.size() != newSchedules.length || verifiedSchedules.size() != requiredNumberOfSchedules())
	    {
	      return wasSet;
	    }

	    schedules.clear();
	    schedules.addAll(verifiedSchedules);
	    wasSet = true;
	    return wasSet;
	  }

	  public void delete()
	  {
	    LibraryManagement placeholderLibraryManagement = libraryManagement;
	    this.libraryManagement = null;
	    if(placeholderLibraryManagement != null)
	    {
	      placeholderLibraryManagement.removeLibrary(this);
	    }
	    bookings.clear();
	    schedules.clear();
	    HeadLibrarian existingHeadLibrarian = headLibrarian;
	    headLibrarian = null;
	    if (existingHeadLibrarian != null)
	    {
	      existingHeadLibrarian.delete();
	    }
	  }


	  public String toString()
	  {
	    return super.toString() + "["+
	            "libraryName" + ":" + getLibraryName()+ "," +
	            "libraryAddress" + ":" + getLibraryAddress()+ "," +
	            "libraryPhone" + ":" + getLibraryPhone()+ "," +
	            "librarymail" + ":" + getLibrarymail()+ "]" + System.getProperties().getProperty("line.separator") +
	            "  " + "libraryManagement = "+(getLibraryManagement()!=null?Integer.toHexString(System.identityHashCode(getLibraryManagement())):"null") + System.getProperties().getProperty("line.separator") +
	            "  " + "headLibrarian = "+(getHeadLibrarian()!=null?Integer.toHexString(System.identityHashCode(getHeadLibrarian())):"null");
	  }
	}