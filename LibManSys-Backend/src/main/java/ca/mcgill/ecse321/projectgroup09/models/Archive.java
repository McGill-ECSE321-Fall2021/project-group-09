package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.*;


@Entity
public class Archive extends LibraryItem{
	private static final double DAILY_OVERDUE_FEE = -1;
	private static final int LOANABLE_PERIOD = -1;
	private static final LibraryItem.ItemStatus LIBRARY_ITEM_STATUS = LibraryItem.ItemStatus.LibraryOnly;

	private String institution;
	
	public Archive() {
		this.setDailyOverdueFee(DAILY_OVERDUE_FEE);
		this.setLoanablePeriod(LOANABLE_PERIOD);
		this.setItemStatus(LIBRARY_ITEM_STATUS);
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

	  
	public void setInstitution(String anInstitution){
	    this.institution = anInstitution;
	}
	  
	public String getInstitution()
	{
	   return this.institution;
    }

	 
}

