package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailure;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.LoanDto;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.service.LoanService;

@CrossOrigin(origins = "*")
@RestController
public class LoanController {
 
	private static final String BASE_URL = "/loan";
	
	@Autowired
	private LoanService loanService;
	
	/**
	 * Create a new loan object.
	 * @param borrowedDate
	 * @param loanId
	 * @param memberId
	 * @param librarianId
	 * @param libraryItemId
	 * @return
	 */
	@PostMapping(value = { BASE_URL + "/create", BASE_URL + "/create/" })
	public ResponseEntity<?> createLoan(@RequestParam("borrowedDate") Date borrowedDate, 
			@RequestParam("loanId") Long loanId, @RequestParam("memberId") Long memberId,
			@RequestParam("librarianId") Long librarianId, @RequestParam("libraryItemId") Long libraryItemId)  {	
		try {
			Loan loan = loanService.createLoan(borrowedDate, loanId, librarianId, memberId, libraryItemId);
			return httpSuccess(LoanDto.convertToDto(loan));
		} catch (Exception e) {
			return httpFailure("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Get loan by id.
	 * @param loanId
	 * @return
	 */
	@GetMapping(value = {BASE_URL + "/{id}", BASE_URL + "/{id}/"})
	public ResponseEntity<?> getLoanbyId(@PathVariable("id") Long loanId)  {
		try {
			Loan loan = loanService.getLoanbyId(loanId);
			return httpSuccess(LoanDto.convertToDto(loan));
		} catch (Exception e) {
			return httpFailure("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Get loans for a member using the member id.
	 * @param memberId
	 * @return
	 */
	@GetMapping(value = { BASE_URL + "/get-by-member/{id}", BASE_URL + "/get-by-member/{id}/" })
	public ResponseEntity<?> getAllLoansbyMember(@PathVariable("id") Long memberId)  {
		try {
			List<Loan> loans = loanService.getLoansbyMember(memberId);
			return httpSuccess(LoanDto.convertToDtos(loans));
		} catch (Exception e) {
			return httpFailure("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Get total late fees for a member.
	 * @param memberId
	 * @return
	 */
	@PostMapping(value = { BASE_URL + "/get-member-late-fees/{id}", BASE_URL + "/get-member-late-fees/{id}/" })
	public ResponseEntity<?> getLoanFeesbyMember(@PathVariable("id") Long memberId)  {
		//Loan loan = loanService.getLoanbyId(loanId);
		//return LoanDto.convertToDto(loan);
		return null;
	}
	
	@PostMapping(value = { "/Loan/Delete/Id", "/Loan/Delete/Id/" })
	public void deleteLoan(@PathVariable("loanId") Long loanId)  {
		//Loan loan = loanService.getLoanbyId(loanId);
		loanService.deleteLoan(loanId);
		//return true;
	}
	
	
	@GetMapping(value = { "/Loan/All", "/Loan/All/" })
	public List<LoanDto> getAllLoans() {
		// Create list of all books, converted to Dto's
		return loanService.getAllLoans().stream().map(loan -> LoanDto.convertToDto(loan)).collect(Collectors.toList());
	}
	
	

}
