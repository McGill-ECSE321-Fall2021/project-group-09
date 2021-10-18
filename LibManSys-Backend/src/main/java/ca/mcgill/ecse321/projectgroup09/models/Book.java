package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.*;

//import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;


@Entity
public class Book extends LibraryItem{

	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------

	  //Book Attributes
	  private String author;
	  private String publisher;
	  private String isbn;
	  private int numPages;

	  @Override
		public void setlibraryItemID(Long alibraryItemID) {
			super.setlibraryItemID(alibraryItemID);
		}

		@Override
		public void setTitle(String aTitle) {
			super.setTitle(aTitle);
		}

		@Override
		public void setPublishedYear(int aPublishedYear) {
			super.setPublishedYear(aPublishedYear);
		}

		
		@Override
		public void setLoanablePeriod(int aLoanablePeriod) {
			super.setLoanablePeriod(aLoanablePeriod);
		}

		
		@Override
		public void setDailyOverdueFee(double aDailyOverdueFee) {
			super.setDailyOverdueFee(aDailyOverdueFee);
		}

		
		@Override
		public void setItemStatus(ItemStatus anItemStatus) {
			super.setItemStatus(anItemStatus);
		}
		
		@Override
		public Long getlibraryItemID() {
			return super.getlibraryItemID();
		}

		
		@Override
		public String getTitle() {
			return super.getTitle();
		}

		@Override
		public int getPublishedYear() {
			return super.getPublishedYear();
		}

		
		@Override
		public int getLoanablePeriod() {
			return super.getLoanablePeriod();
		}

		@Override
		public double getDailyOverdueFee() {
			return super.getDailyOverdueFee();
		}

		@Override
		public ItemStatus getItemStatus() {
			return super.getItemStatus();
		}
		
	  public void setAuthor(String aAuthor)
	  {
	    this.author = aAuthor;
	  }

	  public void setPublisher(String aPublisher)
	  {
	    this.publisher = aPublisher;
	  }

	  public void setISBN(String aISBN)
	  {
	   this.isbn = aISBN;
	  }

	  public void setNumPages(int aNumPages)
	  {
	   this.numPages = aNumPages;
	  }

	  public String getAuthor()
	  {
	    return this.author;
	  }

	  public String getPublisher()
	  {
	    return this.publisher;
	  }


	  public String getISBN()
	  {
	    return this.isbn;
	  }

	  public int getNumPages()
	  {
	    return this.numPages;
	  }

}


//package ca.mcgill.ecse321.projectgroup09.models;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import java.util.Set;
//import javax.persistence.CascadeType;
//import javax.persistence.OneToMany;
//import javax.persistence.Id;
//
//@Entity
//public class Book extends LibraryItem{
//
//	  //------------------------
//	  // MEMBER VARIABLES
//	  //------------------------
//
//	  //Book Attributes
//	  private String author;
//	  private String publisher;
//	  private String isbn;
//	  private int numPages;
//
//	  //------------------------
//	  // CONSTRUCTOR
//	  //------------------------
//
//	  public Book(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement, String aAuthor, String aPublisher, String aIsbn, int aNumPages)
//	  {
//	    super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod, aDailyOverdueFee, aItemStatus, aLibraryManagement);
//	    author = aAuthor;
//	    publisher = aPublisher;
//	    isbn = aIsbn;
//	    numPages = aNumPages;
//	  }
//
//	  //------------------------
//	  // INTERFACE
//	  //------------------------
//
//	  public boolean setAuthor(String aAuthor)
//	  {
//	    boolean wasSet = false;
//	    author = aAuthor;
//	    wasSet = true;
//	    return wasSet;
//	  }
//
//	  public boolean setPublisher(String aPublisher)
//	  {
//	    boolean wasSet = false;
//	    publisher = aPublisher;
//	    wasSet = true;
//	    return wasSet;
//	  }
//
//	  public boolean setIsbn(String aIsbn)
//	  {
//	    boolean wasSet = false;
//	    isbn = aIsbn;
//	    wasSet = true;
//	    return wasSet;
//	  }
//
//	  public boolean setNumPages(int aNumPages)
//	  {
//	    boolean wasSet = false;
//	    numPages = aNumPages;
//	    wasSet = true;
//	    return wasSet;
//	  }
//
//	  public String getAuthor()
//	  {
//	    return author;
//	  }
//
//	  public String getPublisher()
//	  {
//	    return publisher;
//	  }
//
//
//	  public String getIsbn()
//	  {
//	    return isbn;
//	  }
//
//	  public int getNumPages()
//	  {
//	    return numPages;
//	  }
//
//	  public void delete()
//	  {
//	    super.delete();
//	  }
//
//
//	  public String toString()
//	  {
//	    return super.toString() + "["+
//	            "author" + ":" + getAuthor()+ "," +
//	            "publisher" + ":" + getPublisher()+ "," +
//	            "isbn" + ":" + getIsbn()+ "," +
//	            "numPages" + ":" + getNumPages()+ "]";
//	  }
//}
