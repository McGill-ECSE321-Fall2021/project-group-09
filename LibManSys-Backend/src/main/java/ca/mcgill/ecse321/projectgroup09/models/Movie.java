package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;

@Entity
public class Movie extends LibraryItem{

	  //Movie Attributes
	  private String director;
	  private int runtime;
	  private String genre;

	
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
		
	  public void setDirector(String aDirector)
	  {
	    this.director = aDirector;
	  }

	  public void setRuntime(int aRuntime)
	  {
		 this.runtime = aRuntime;
	  }

	  public void setGenre(String aGenre)
	  {
	    this.genre = aGenre;
	  }

	  public String getDirector()
	  {
	    return this.director;
	  }

	  public int getRuntime()
	  {
	    return this.runtime;
	  }

	  public String getGenre()
	  {
	    return this.genre;
	  }

	 
}
