package ca.mcgill.ecse321.projectgroup09.models;

public class Newspaper extends LibraryItem{


	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------

	  //Newspaper Attributes
	  private String journalName;
	  private String edition;
	  private String chiefEditor;

	  //------------------------
	  // CONSTRUCTOR
	  //------------------------

	  public Newspaper(int aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement, String aJournalName, String aEdition, String aChiefEditor)
	  {
	    super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod, aDailyOverdueFee, aItemStatus, aLibraryManagement);
	    journalName = aJournalName;
	    edition = aEdition;
	    chiefEditor = aChiefEditor;
	  }

	  //------------------------
	  // INTERFACE
	  //------------------------

	  public boolean setJournalName(String aJournalName)
	  {
	    boolean wasSet = false;
	    journalName = aJournalName;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setEdition(String aEdition)
	  {
	    boolean wasSet = false;
	    edition = aEdition;
	    wasSet = true;
	    return wasSet;
	  }

	  public boolean setChiefEditor(String aChiefEditor)
	  {
	    boolean wasSet = false;
	    chiefEditor = aChiefEditor;
	    wasSet = true;
	    return wasSet;
	  }

	  public String getJournalName()
	  {
	    return journalName;
	  }

	  public String getEdition()
	  {
	    return edition;
	  }

	  public String getChiefEditor()
	  {
	    return chiefEditor;
	  }

	  public void delete()
	  {
	    super.delete();
	  }


	  public String toString()
	  {
	    return super.toString() + "["+
	            "journalName" + ":" + getJournalName()+ "," +
	            "edition" + ":" + getEdition()+ "," +
	            "chiefEditor" + ":" + getChiefEditor()+ "]";
	  }
	}