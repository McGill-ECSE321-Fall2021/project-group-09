package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.Archive;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem.ItemStatus;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;



public class ArchiveDto extends LibraryItemDto {
	
	private String institution;
	
	
	public ArchiveDto() {
	
	}
	
	public ArchiveDto(Long aLibraryItemId, String aTitle, int aPublishedYear, int aLoanablePeriod, double aDailyOverdueFee,
			ItemStatus aItemStatus, Member aMember, List<Loan> aLoans, String aInstitution) {
		super(aLibraryItemId, aTitle, aPublishedYear, aLoanablePeriod,aDailyOverdueFee, aItemStatus, aMember, aLoans, LibraryItemType.ARCHIVE);
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
	
	public String getInstitution() {
		return institution;
	}
	
	public void setInstitution(String institution) {
		this.institution=institution;
	}

}
