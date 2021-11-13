package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.*;
import ca.mcgill.ecse321.projectgroup09.models.Archive;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;



public class ArchiveDto {
	

		private Long libraryItemID;
		private String title;
		private int publishedYear;
		private int loanablePeriod;
		private double dailyOverdueFee;
		private ItemStatus itemStatus;
		

		private MemberDto member;
		private List<LoanDto> loans;
		

		private String institution;
		

		public ArchiveDto() {
		
		}
		
		public ArchiveDto(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee,
				ItemStatus aItemStatus, Member aMember, List<Loan> aLoans, String aInstitution) {
			
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
			this.institution = aInstitution;
		}
		
	
		public static ArchiveDto convertToDto(Archive archive) {
			if (archive == null) {
				throw new IllegalArgumentException("archive parameter cannot be null.");
			}
			
			ArchiveDto archiveDto = new ArchiveDto(
					archive.getlibraryItemID(),
					archive.getTitle(),
					archive.getPublishedYear(),
					archive.getLoanablePeriod(),
					archive.getDailyOverdueFee(),
					archive.getItemStatus(),
					archive.getMember(),
					archive.getLoans(),
					archive.getInstitution()
					
			);
			return archiveDto;
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
		
		public String getInstitution() {
			return institution;
		}
		
		public void setInstitution(String institution) {
			this.institution=institution;
		}

}
