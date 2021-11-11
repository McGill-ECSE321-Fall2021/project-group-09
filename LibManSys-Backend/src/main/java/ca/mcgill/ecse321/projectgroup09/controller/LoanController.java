package ca.mcgill.ecse321.projectgroup09.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.projectgroup09.dto.LoanDto;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.service.LoanService;

@CrossOrigin(origins = "*")
@RestController
public class LoanController {
 /*
	@Autowired
	private LoanService loanService;
	
	
	@PostMapping(value = { "/Loan/createLoan", "/Loan/createLoan/" })
	public LoanDto createLoan(@PathVariable("borrowedDate") Date borrowedDate, @PathVariable("returnDate") Date returnDate,
			@PathVariable("lateFee") Double lateFee,@PathVariable("loanStatus") LoanStatus loanStatus,
			@PathVariable("loanId") Long loanId,@PathVariable("memberId") Long memberId,
			@PathVariable("librarianId") Long librarianId,@PathVariable("libraryItemId") Long libraryItemId)  {
		Loan loan = loanService.createLoan(borrowedDate, returnDate, lateFee, loanStatus, loanId, librarianId, memberId, libraryItemId);
		return LoanDto.convertToDto(loan);
	}
	
	//getloanbyid
	@PostMapping(value = { "/Loan/LoanId", "/Loan/LoanId/" })
	public LoanDto getLoanbyId(@PathVariable("loanId") Long loanId)  {
	//	LoanDto loanDto = new LoanDto();
		Loan loan = loanService.getLoanbyId(loanId);
		return LoanDto.convertToDto(loan);
	}
	//getloansbymember
	@PostMapping(value = { "/Loan/Member/MemberId", "/Loan/Member/MemberId/" })
	public List<LoanDto> getAllLoansbyMember(@PathVariable("member") Member member)  {
	
		List <LoanDto> loansDto = new ArrayList<LoanDto>();
		for(Loan loan : loanService.getLoansbyMember(member)) {
			loansDto.add(LoanDto.convertToDto(loan));
		}

		return loansDto;
	}
	
	//getloanfeesbymember
	/*@PostMapping(value = { "/Loan/Member/Fees", "/Loan/Member/Fees/" })
	public LoanDto getLoanFeesbyMember(@PathVariable("loanId") Long loanId)  {
	//	LoanDto loanDto = new LoanDto();
		Loan loan = loanService.getLoanbyId(loanId);
		return LoanDto.convertToDto(loan);
	}*/
	
	/*@PostMapping(value = { "/Loan/Delete/{id}", "/Loan/Delete/{id}" })
	public void deleteLoan(@PathVariable("loanId") Long loanId)  {
		//Loan loan = loanService.getLoanbyId(loanId);
		loanService.deleteLoan(loanId);
		//return true;
	}
	
	
	@GetMapping(value = { "/Loan/All", "/Loan/All/" })
	public List<LoanDto> getAllLoans() {
		// Create list of all books, converted to Dto's
		return loanService.getAllLoans().stream().map(loan -> LoanDto.convertToDto(loan)).collect(Collectors.toList());
	}*/
	
	

}
