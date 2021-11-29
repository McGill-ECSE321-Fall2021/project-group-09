package ca.mcgill.ecse321.projectgroup09.controller;

import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailure;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpFailureMessage;
import static ca.mcgill.ecse321.projectgroup09.utils.HttpUtil.httpSuccess;

import java.sql.Date;
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
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
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
	public ResponseEntity<?> createLoan(@RequestParam("borrowedDate") String borrowedDateString, 
			@RequestParam("loanStatus") String loanStatusString, 
			@RequestParam("memberLibCardNumber") Long memberId,
			@RequestParam("librarianEmployeeId") Long librarianId, 
			@RequestParam("libraryItemId") Long libraryItemId)  {	
		// get params
		Date borrowedDate = null;
		try {
			borrowedDate = Date.valueOf(borrowedDateString);
		} catch (Exception e) {
			httpFailureMessage(e.getMessage());
		}
		LoanStatus loanStatus = null;
		try {
			loanStatus = LoanStatus.valueOf(loanStatusString);
		} catch (Exception e) {
			httpFailureMessage(e.getMessage());
		}
		// Create loan object
		try {
			Loan loan = loanService.createLoan(borrowedDate, loanStatus, memberId, librarianId, libraryItemId);
			return httpSuccess(LoanDto.convertToDto(loan, true));
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
			return httpSuccess(LoanDto.convertToDto(loan, true));
		} catch (Exception e) {
			return httpFailure("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Get loans for a member using the member libCardNumber.
	 * @param libCardNumber
	 * @return
	 */
	@GetMapping(value = { BASE_URL + "/get-by-member/{libCardNumber}", BASE_URL + "/get-by-member/{libCardNumber}/" })
	public ResponseEntity<?> getAllLoansByMember(@PathVariable("libCardNumber") Long memberLibCardNumber)  {
		try {
			List<Loan> loans = loanService.getLoansbyMember(memberLibCardNumber);
			return httpSuccess(LoanDto.convertToDtos(loans, true));
		} catch (Exception e) {
			return httpFailure("Error: " + e.getMessage());
		}
	}
	
	/**
	 * Get total late fees for a member by library card number.
	 * TODO implement
	 * @param libCardNumber
	 * @return
	 */
	@PostMapping(value = { BASE_URL + "/get-member-late-fees/{libCardNumber}", BASE_URL + "/get-member-late-fees/{libCardNumber}/" })
	public ResponseEntity<?> getLoanFeesbyMember(@PathVariable("libCardNumber") Long memberLibCardNumber)  {
		//Loan loan = loanService.getLoanbyId(loanId);
		//return LoanDto.convertToDto(loan);
		return null;
	}
	
	/**
	 * Delete the specified loan from the loan repository.
	 * @param loanId
	 * @return message indicating if loan was deleted or not.
	 */
	@PostMapping(value = { BASE_URL + "/delete/{id}", BASE_URL + "/delete/{id}/" })
	public ResponseEntity<?> deleteLoan(@PathVariable("id") Long loanId)  {
		// try to delete loan
		try {
			loanService.deleteLoan(loanId);
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		return httpSuccess("Deleted loan successfully.");
	}
	
	
	/**
	 * Get all loans. This is the endpoint for the base url for loan controller (www.site.com/loan)
	 * @return
	 */
	@GetMapping(value = { BASE_URL, BASE_URL + "/", BASE_URL + "/get-all", BASE_URL + "/get-all/" })
	public ResponseEntity<?> getAllLoans() {
		// Create list of all books, converted to Dto's
		List<LoanDto> loans = null;
		try {
			loans = loanService.getAllLoans().stream().map(loan -> LoanDto.convertToDto(loan, true)).collect(Collectors.toList());
		} catch (Exception e) {
			return httpFailureMessage(e.getMessage());
		}
		return httpSuccess(loans);
	}
	
	

}
