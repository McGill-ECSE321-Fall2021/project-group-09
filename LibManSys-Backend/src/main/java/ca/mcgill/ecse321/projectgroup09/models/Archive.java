package ca.mcgill.ecse321.projectgroup09.models;

public class Archive extends LibraryItem{

	  //------------------------
	  // MEMBER VARIABLES
	  //------------------------

	  //Archive Attributes
	  private String institution;

	  //------------------------
	  // CONSTRUCTOR
	  //------------------------

	  public Archive(int aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement, String aInstitution)
	  {
	    super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod, aDailyOverdueFee, aItemStatus, aLibraryManagement);
	    institution = aInstitution;
	  }

	  //------------------------
	  // INTERFACE
	  //------------------------

	  public boolean setInstitution(String aInstitution)
	  {
	    boolean wasSet = false;
	    institution = aInstitution;
	    wasSet = true;
	    return wasSet;
	  }

	  public String getInstitution()
	  {
	    return institution;
	  }

	  public void delete()
	  {
	    super.delete();
	  }


	  public String toString()
	  {
	    return super.toString() + "["+
	            "institution" + ":" + getInstitution()+ "]";
	  }
	}