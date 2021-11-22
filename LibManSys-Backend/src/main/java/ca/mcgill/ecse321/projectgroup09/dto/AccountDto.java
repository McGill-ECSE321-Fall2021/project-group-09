package ca.mcgill.ecse321.projectgroup09.dto;

import javax.persistence.Id;

import ca.mcgill.ecse321.projectgroup09.models.*;
import java.util.*;

public class AccountDto {

	private Long accountId;
	private String fullName;
	

	public AccountDto(long accountID, String fullName) {
		this.accountId = accountID; 
		this.fullName = fullName;
	}
	
	public static AccountDto convertToDto(Account account) {
		AccountDto accountDto = null; 
		
		if ((account instanceof Member) && !(account instanceof OnlineMember)) {
			accountDto = MemberDto.convertToDto(account);
		}
		else if ((account instanceof Librarian) && (account instanceof HeadLibrarian)) {
			//accountDto = HeadLibrarianDto.convertToDto(account);
		}
		else if ((account instanceof Librarian) && !(account instanceof HeadLibrarian)) {
			accountDto = LibrarianDto.convertToDto(account);
		}
		else if ((account instanceof Member) && !(account instanceof OnlineMember)) {
			accountDto = OnlineMemberDto.convertToDto(account);
		}
		
		return accountDto;
	}
	
	public void setFullName(String aFullName) {
		this.fullName = aFullName; 
	}

	public String getFullName() {
		return fullName;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	@Override
	public boolean equals(Object o) {
		// if o is not an Account, return false
		if (! (o instanceof Account)) {
			return false;
		}
		Account a = (Account) o;
		// if Ids not equal, return false
		if (!this.getAccountId().equals(a.getAccountId())) {
			return false;
		}
		return true;
	}

	
}
