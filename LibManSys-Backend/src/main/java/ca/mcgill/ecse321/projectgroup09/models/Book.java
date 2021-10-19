package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;

@Entity
public class Book extends LibraryItem{


	  //Book Attributes
	  private String author;
	  private String publisher;
	  private String ISBN;
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

}


