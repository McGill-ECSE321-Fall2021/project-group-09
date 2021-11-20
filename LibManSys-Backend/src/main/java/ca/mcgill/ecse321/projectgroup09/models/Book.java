package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;

/**
 * Book Library Item
 */
@Entity
public class Book extends LibraryItem{
	/** Daily overdue fee for books (in dollars). */
	private static final double DAILY_OVERDUE_FEE = 0.1;
	/** Period allowed for loans of Book type library items (in days). */
	private static final int LOANABLE_PERIOD = 14;
	/** Initial value for book item status. */
	private static final LibraryItem.ItemStatus LIBRARY_ITEM_STATUS = LibraryItem.ItemStatus.Available;
	
	  //Book Attributes
	private String author;
	private String publisher;
	private String ISBN;
	private int numPages;
	
	/**
	 * Default No Arg Constructor.
	 */
	public Book() {
		// DailyOverdueFee is a constant value
		this.setDailyOverdueFee(DAILY_OVERDUE_FEE);
		// Always init ItemStatus to Avaiable for books
		this.setItemStatus(LIBRARY_ITEM_STATUS);
		// Loanable period is always same for books
		this.setLoanablePeriod(LOANABLE_PERIOD);
	}
	
	/**
	 * DailyOverdueFee, ItemStatus, and LoanablePeriod are all set to default values by constructor.
	 * LibraryItemId is set by LibraryItem (superclass) constructor.
	 * @param title
	 * @param aPublishedYear
	 * @param author
	 * @param publisher
	 * @param ISBN
	 * @param numPages
	 */
	public Book(String title, int aPublishedYear, String author, String publisher,
			String ISBN, int numPages) {
		super(title, aPublishedYear);
		this.author = author;
		this.publisher = publisher;
		this.ISBN = ISBN;
		this.numPages = numPages;
		// DailyOverdueFee is a constant value
		this.setDailyOverdueFee(DAILY_OVERDUE_FEE);
		// Always init ItemStatus to Avaiable for books
		this.setItemStatus(LIBRARY_ITEM_STATUS);
		// Loanable period is always same for books
		this.setLoanablePeriod(LOANABLE_PERIOD);
	}
	  
	  public Book (Long alibraryItemId) {
		  super(alibraryItemId);
	  }
	
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
	   this.ISBN = aISBN;
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
	    return this.ISBN;
	  }
	
	  public int getNumPages()
	  {
	    return this.numPages;
	  }
	  
	  /**
	   * Determines if the input string is a valid 10 or 13 digit ISBN.
	   * ISBN string can have dashed in random places?
	   * TODO implement
	   * @param isbn
	   * @return {@code true} if isbn is valid, {@code false} otherwise.
	   */
	  public static boolean isValidISBN(String isbn) {
		  return true;
	  }

}


