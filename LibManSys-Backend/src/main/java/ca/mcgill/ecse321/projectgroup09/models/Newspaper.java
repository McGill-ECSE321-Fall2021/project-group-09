package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;

@Entity
public class Newspaper extends LibraryItem{


	  //Newspaper Attributes
	  private String journalName;
	  private String edition;
	  private String chiefEditor;

	
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
	 
		public void setJournalName(String aJournalName)
	  {
	    this.journalName = aJournalName;
	  }

	  public void setEdition(String anEdition)
	  {
	    this.edition = anEdition;
	  }

	  public void setChiefEditor(String aChiefEditor)
	  {
	    this.chiefEditor = aChiefEditor; 
	  }

	
	  public String getJournalName()
	  {
	    return this.journalName;
	  }

	  public String getEdition()
	  {
	    return this.edition;
	  }

	  public String getChiefEditor()
	  {
	    return this.chiefEditor;
	  }

	 
	}
