package ca.mcgill.ecse321.projectgroup09.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;
import ca.mcgill.ecse321.projectgroup09.models.Loan.LoanStatus;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestPersistenceMember {


	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private MemberRepository memberRepository;
	

	@AfterEach
	public void clearAccountsDatabase() {
		loanRepository.deleteAll();
		memberRepository.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadMember() {

		// Account Attributes 
		String fullname = "Testing Name";

		// Member Attributes
		Long LibCardNumber =  2L;
		Member member = new Member();
		String address = "12 B";
		Boolean isResident = true;
		String phoneNumber = "514";
		Double amountOwed = 0.10;
		Integer activeLoans = 0;
		Boolean isVerified = true;


		member.setFullName(fullname);
		member.setActiveLoans(activeLoans);
		member.setAddress(address);
		member.setAmountOwed(amountOwed);
		member.setIsResident(isResident);
		member.setIsVerified(isVerified);
		member.setPhoneNumber(phoneNumber);
		member.setLibCardNumber(LibCardNumber);

		memberRepository.save(member);

		//Loan Attributes
		Long loanID = 1L;
		Date borrowdDate = java.sql.Date.valueOf("2021-02-01");
		Date returnDate = java.sql.Date.valueOf("2021-02-08");
		Double lateFees= 0.10;
		LoanStatus loanStatus =  LoanStatus.Active;

		Loan loan = new Loan();
		loan.setLateFees(lateFees);
		loan.setloanID(loanID);
		loan.setBorrowedDate(borrowdDate);
		loan.setReturnDate(returnDate);
		loan.setLoanStatus(loanStatus);

		loan.setMember(member);

		loanRepository.save(loan);

		member = null;
		member = memberRepository.findMemberByLibCardNumber(LibCardNumber);

		loan = null; 
		loan = loanRepository.findLoanByLoanID(loanID);

		assertNotNull(member);
		assertEquals(fullname, member.getFullName());
		assertEquals(fullname, loan.getMember().getFullName());
	}
}
