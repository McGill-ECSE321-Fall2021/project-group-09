package ca.mcgill.ecse321.projectgroup09.dto;

import ca.mcgill.ecse321.projectgroup09.models.Account;
import ca.mcgill.ecse321.projectgroup09.models.Library;

public class HeadLibrarianDto {
	private Long managerIDNum;
	private LibraryDto library;
	
	public HeadLibrarianDto(){}
	
	public HeadLibrarianDto(Long managerIDNum){
		this.managerIDNum = managerIDNum;
	}
	
	public Long getManagerIDNum() {
		return this.managerIDNum;
	}
	
	public void setManagerIDNum(Long  library) {
		this.managerIDNum =  library;
	}
	
	public LibraryDto getLibrary() {
		return this.library;
	}
	
	public void setLibrary(LibraryDto library) {
		this.library = library;
	}

	public static HeadLibrarianDto convertToDto(Account account) {
		// TODO Auto-generated method stub
		return null;
	}
}
