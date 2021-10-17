package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Movie extends LibraryItem{

	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------

	  //Movie Attributes
	  private String director;
	  private int runtime;
	  private String genre;

	  //------------------------
	  // CONSTRUCTOR
	  //------------------------

	  public Movie(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement, String aDirector, int aRuntime, String aGenre)
	  {
	    super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod, aDailyOverdueFee, aItemStatus, aLibraryManagement);
	    director = aDirector;
	    runtime = aRuntime;
	    genre = aGenre;
	  }

	  //------------------------
	  // INTERFACE
	  //------------------------

	  public boolean setDirector(String aDirector)
	  {
	    boolean wasSet = false;
	    director = aDirector;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setRuntime(int aRuntime)
	  {
	    boolean wasSet = false;
	    runtime = aRuntime;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setGenre(String aGenre)
	  {
	    boolean wasSet = false;
	    genre = aGenre;
	    wasSet = true;
	    return wasSet;
	  }

	  @Id
	  public String getDirector()
	  {
	    return director;
	  }

	  public int getRuntime()
	  {
	    return runtime;
	  }

	  public String getGenre()
	  {
	    return genre;
	  }

	  public void delete()
	  {
	    super.delete();
	  }


	  public String toString()
	  {
	    return super.toString() + "["+
	            "director" + ":" + getDirector()+ "," +
	            "runtime" + ":" + getRuntime()+ "," +
	            "genre" + ":" + getGenre()+ "]";
	  }
}
