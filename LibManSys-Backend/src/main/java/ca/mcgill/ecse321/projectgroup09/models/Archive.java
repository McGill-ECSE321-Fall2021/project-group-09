package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.*;


@Entity
public class Archive extends LibraryItem{
  

	private String institution;

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

	  
	public void setInstitution(String anInstitution){
	    this.institution = anInstitution;
	}
	  
	public String getInstitution()
	{
	   return this.institution;
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
//public class Archive extends LibraryItem{
//
//	  //------------------------
//	  // MEMBER VARIABLES
//	  //------------------------
//
//	  //Archive Attributes
//	  private String institution;
//
//	  //------------------------
//	  // CONSTRUCTOR
//	  //------------------------
//
//	  public Archive(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee, ItemStatus aItemStatus, LibraryManagement aLibraryManagement, String aInstitution)
//	  {
//	    super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod, aDailyOverdueFee, aItemStatus, aLibraryManagement);
//	    institution = aInstitution;
//	  }
//
//	  //------------------------
//	  // INTERFACE
//	  //------------------------
//
//	  public boolean setInstitution(String aInstitution)
//	  {
//	    boolean wasSet = false;
//	    institution = aInstitution;
//	    wasSet = true;
//	    return wasSet;
//	  }
//	  @Id
//	  public String getInstitution()
//	  {
//	    return institution;
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
//	            "institution" + ":" + getInstitution()+ "]";
//	  }
//	}
