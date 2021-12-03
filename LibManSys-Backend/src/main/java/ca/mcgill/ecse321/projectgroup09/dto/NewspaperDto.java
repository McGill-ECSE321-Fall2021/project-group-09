package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Newspaper;

public class NewspaperDto extends LibraryItemDto {

	private String journalName;
	private String edition;
	private String chiefEditor;
	
	public NewspaperDto() {
		
	}
	
	public NewspaperDto(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee,
			ItemStatus aItemStatus, Member aMember, List<Loan> aLoans, String journalName, String edition, String chiefEditor) {
		super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod,aDailyOverdueFee, aItemStatus, aMember, aLoans, LibraryItemType.NEWSPAPER);
		this.journalName=journalName;
		this.edition=edition;
		this.chiefEditor=chiefEditor;
	}
	
	
	
	
	public static NewspaperDto convertToDto(Newspaper newspaper) {
		if (newspaper == null) {
			throw new IllegalArgumentException("Newspaper parameter cannot be null.");
		}
		
		NewspaperDto newspaperDto = new NewspaperDto(
				newspaper.getlibraryItemID(),
				newspaper.getTitle(),
				newspaper.getPublishedYear(),
				newspaper.getLoanablePeriod(),
				newspaper.getDailyOverdueFee(),
				newspaper.getItemStatus(),
				newspaper.getMember(),
				newspaper.getLoans(),
				newspaper.getJournalName(),
				newspaper.getEdition(),
				newspaper.getChiefEditor()
				
		);
		return newspaperDto;
	}
	
	public String getjournalName() {
		return journalName;
	}
	
	public void setjournalName(String journalName) {
		this.journalName=journalName;
	}
	
	public String getEdition() {
		return edition;
	}
	
	public void setEdition(String edition) {
		this.edition=edition;
	}
	
	public String getChiefEditior() {
		return chiefEditor;
	}
	
	public void setChiefEditor(String chiefEditor) {
		this.chiefEditor=chiefEditor;
	}
		
}
