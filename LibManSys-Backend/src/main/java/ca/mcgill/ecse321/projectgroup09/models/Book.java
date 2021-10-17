package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Book extends LibraryItem{

	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------

	  //Book Attributes
	  private String author;
	  private String publisher;
	  private int isbn;
	  private int numPages;

	  //------------------------
	  // CONSTRUCTOR
	  //------------------------

	  public Book(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement, String aAuthor, String aPublisher, int aIsbn, int aNumPages)
	  {
	    super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod, aDailyOverdueFee, aItemStatus, aLibraryManagement);
	    author = aAuthor;
	    publisher = aPublisher;
	    isbn = aIsbn;
	    numPages = aNumPages;
	  }

	  //------------------------
	  // INTERFACE
	  //------------------------

	  public boolean setAuthor(String aAuthor)
	  {
	    boolean wasSet = false;
	    author = aAuthor;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setPublisher(String aPublisher)
	  {
	    boolean wasSet = false;
	    publisher = aPublisher;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setIsbn(int aIsbn)
	  {
	    boolean wasSet = false;
	    isbn = aIsbn;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setNumPages(int aNumPages)
	  {
	    boolean wasSet = false;
	    numPages = aNumPages;
	    wasSet = true;
	    return wasSet;
	  }

	  public String getAuthor()
	  {
	    return author;
	  }

	  public String getPublisher()
	  {
	    return publisher;
	  }

	  public int getIsbn()
	  {
	    return isbn;
	  }

	  public int getNumPages()
	  {
	    return numPages;
	  }

	  public void delete()
	  {
	    super.delete();
	  }


	  public String toString()
	  {
	    return super.toString() + "["+
	            "author" + ":" + getAuthor()+ "," +
	            "publisher" + ":" + getPublisher()+ "," +
	            "isbn" + ":" + getIsbn()+ "," +
	            "numPages" + ":" + getNumPages()+ "]";
	  }
}
