package ca.mcgill.ecse321.projectgroup09.dto;


import ca.mcgill.ecse321.projectgroup09.models.*;
import java.util.*;
import java.util.stream.Collectors;

public class AccountDto {

	private Long accountID;
	private String fullName;
	

	public AccountDto(long accountID, String fullName) {
		this.accountID = accountID; 
		this.fullName = fullName;
	}
	
	/**
	 * Returns an account dto object representing the account inputted. The return value
	 * is also an account, so you must cast the result back to the desired account type
	 * in order to use the subclass methods.
	 * @param account
	 * @return
	 */
	public static AccountDto convertToDto(Account account) {
		AccountDto accountDto = null; 
		// need to cast account to proper type before calling convertToDto, or else
		// it will call convertToDto(Account account), which is this method -> endless loop
		
		// if account is Member and online member
		if (account instanceof OnlineMember) {
			OnlineMember om = (OnlineMember) account;
			accountDto = OnlineMemberDto.convertToDto(om);
		}
		// else if account is Member and not online member
		// (already know it is not an online member because of above)
		else if (account instanceof Member) {
			Member m = (Member) account;
			accountDto = MemberDto.convertToDto(m);
		}
		// else if account is a librarian AND head librarian
		else if (account instanceof HeadLibrarian) {
			HeadLibrarian hl = (HeadLibrarian) account;
			accountDto = HeadLibrarianDto.convertToDto(hl);
		}
		// else if account is just librarian and not head librarian
		else if (account instanceof Librarian) {
			Librarian l = (Librarian) account;
			accountDto = LibrarianDto.convertToDto(l);
		}
		
		return accountDto;
	}
	
	public static List<AccountDto> convertToDto(List<? extends Account> accounts) {
		return accounts.stream().map(account -> AccountDto.convertToDto(account)).collect(Collectors.toList());
		
	}
	
	public void setFullName(String aFullName) {
		this.fullName = aFullName; 
	}

	public String getFullName() {
		return fullName;
	}

	public Long getAccountID() {
		return accountID;
	}

	public void setAccountID(Long accountId) {
		this.accountID = accountId;
	}

	@Override
	public boolean equals(Object o) {
		// if o is not an Account, return false
		if (! (o instanceof AccountDto)) {
			return false;
		}
		AccountDto a = (AccountDto) o;
		// if Ids not equal, return false
		if (!this.getAccountID().equals(a.getAccountID())) {
			return false;
		}
		return true;
	}

	
}
