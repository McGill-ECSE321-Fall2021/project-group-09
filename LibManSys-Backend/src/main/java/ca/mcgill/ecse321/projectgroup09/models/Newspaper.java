package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.*;

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
//public class Newspaper extends LibraryItem{
//
//
//
//	  //------------------------
//	  // MEMBER VARIABLES
//	  //------------------------
//
//	  //Newspaper Attributes
//	  private String journalName;
//	  private String edition;
//	  private String chiefEditor;
//
//	  //------------------------
//	  // CONSTRUCTOR
//	  //------------------------
//
//	  public Newspaper(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement, String aJournalName, String aEdition, String aChiefEditor)
//	  {
//	    super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod, aDailyOverdueFee, aItemStatus, aLibraryManagement);
//	    journalName = aJournalName;
//	    edition = aEdition;
//	    chiefEditor = aChiefEditor;
//	  }
//
//	  //------------------------
//	  // INTERFACE
//	  //------------------------
//
//	  public boolean setJournalName(String aJournalName)
//	  {
//	    boolean wasSet = false;
//	    journalName = aJournalName;
//	    wasSet = true;
//	    return wasSet;
//	  }
//
//	  public boolean setEdition(String aEdition)
//	  {
//	    boolean wasSet = false;
//	    edition = aEdition;
//	    wasSet = true;
//	    return wasSet;
//	  }
//
//	  public boolean setChiefEditor(String aChiefEditor)
//	  {
//	    boolean wasSet = false;
//	    chiefEditor = aChiefEditor;
//	    wasSet = true;
//	    return wasSet;
//	  }
//
//	  @Id
//	  public String getJournalName()
//	  {
//	    return journalName;
//	  }
//
//	  public String getEdition()
//	  {
//	    return edition;
//	  }
//
//	  public String getChiefEditor()
//	  {
//	    return chiefEditor;
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
//	            "journalName" + ":" + getJournalName()+ "," +
//	            "edition" + ":" + getEdition()+ "," +
//	            "chiefEditor" + ":" + getChiefEditor()+ "]";
//	  }
//	}