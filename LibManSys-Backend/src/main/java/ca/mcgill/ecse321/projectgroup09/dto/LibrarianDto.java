package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.Librarian;

public class LibrarianDto extends AccountDto {

	// attributes
	
	// associations
	
	/**
	 * No-arg constructor.
	 */
	LibrarianDto() {
		
	}
	
	/**
	 * Create a new librarian Dto object with all fields filled.
	 * @param params
	 */
	public LibrarianDto(Long params) {
		
	}
	
	/**
	 * Convert a librarian object into a librarian dto.
	 * @param l
	 * @return
	 */
	public static LibrarianDto convertToDto(Librarian librarian) {
		LibrarianDto ldto = new LibrarianDto(/*put fields from 'l' here*/);
		return ldto;
	}
	
	public static List<LibrarianDto> convertToDtos(List<LibrarianDto> librarians) {
		List<LibrarianDto> librarianDtos = new ArrayList<LibrarianDto>();
		
		return librarianDtos;
	}
	
	// getters & setters
}
