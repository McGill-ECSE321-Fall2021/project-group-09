package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.Newspaper;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;

public class NewspaperDto{
	
	private Long libraryItemID;
	private String title;
	private int publishedYear;
	private int loanablePeriod;
	private double dailyOverdueFee;
	private ItemStatus itemStatus;
	

	private MemberDto member;
	private List<LoanDto> loans;
	

	private String journalName;
	private String edition;
	private String chiefEditor;
	
	public NewspaperDto() {
		
	}
	
	public NewspaperDto(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee,
			ItemStatus aItemStatus, Member aMember, List<Loan> aLoans, String journalName, String edition, String chiefEditor) {
		
		this.libraryItemID = aLibraryItemId;
		this.title = aTitle;
		this.publishedYear = aPublishedYear;
		this.loanablePeriod = aLoanablePeriod;
		this.dailyOverdueFee = aDailyOverdueFee;
		this.itemStatus = aItemStatus;

		if (member != null) {
			MemberDto aMemberDto = MemberDto.convertToDto(aMember);
			this.member = aMemberDto;
		} else {

			this.member = null;
		}

		List<LoanDto> aLoansDto = aLoans.stream().map(loan -> LoanDto.convertToDto(loan)).collect(Collectors.toList());
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
	
	public Long getLibraryItemID() {
		return libraryItemID;
	}
	
	public void setLibraryItemID(Long libraryItemID) {
		this.libraryItemID = libraryItemID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPublishedYear() {
		return publishedYear;
	}
	
	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}
	
	public int getLoanablePeriod() {
		return loanablePeriod;
	}
	
	public void setLoanablePeriod(int loanablePeriod) {
		this.loanablePeriod = loanablePeriod;
	}
	
	public double getDailyOverdueFee() {
		return dailyOverdueFee;
	}
	
	public void setDailyOverdueFee(double dailyOverdueFee) {
		this.dailyOverdueFee = dailyOverdueFee;
	}
	
	public ItemStatus getItemStatus() {
		return itemStatus;
	}
	
	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	public MemberDto getMember() {
		return member;
	}
	
	public void setMember(MemberDto member) {
		this.member = member;
	}
	
	public List<LoanDto> getLoans() {
		return loans;
	}
	
	public void setLoans(List<LoanDto> loans) {
		this.loans = loans;
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
