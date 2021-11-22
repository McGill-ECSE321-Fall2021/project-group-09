package ca.mcgill.ecse321.projectgroup09.dto;

import ca.mcgill.ecse321.projectgroup09.models.Account;

import ca.mcgill.ecse321.projectgroup09.models.HeadLibrarian;
import ca.mcgill.ecse321.projectgroup09.models.Library;

public class HeadLibrarianDto extends LibrarianDto{
	private Long managerIDNum;
	private LibraryDto library;
	
	public HeadLibrarianDto(){}
	
	public HeadLibrarianDto(Long managerIDNum, Library library){
		this.managerIDNum = managerIDNum;
		this.library = LibraryDto.convertToDto(library);
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

	
	public static HeadLibrarianDto convertToDto(HeadLibrarian headLibrarian) {
		HeadLibrarianDto headLibrarianDto = new HeadLibrarianDto(
				headLibrarian.getmanagerIDNum(),
				headLibrarian.getLibrary()
				);
		
		return headLibrarianDto; 

	}
}
