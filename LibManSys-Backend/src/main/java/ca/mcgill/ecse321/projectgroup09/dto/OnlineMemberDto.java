/**
 * @author Zarif Ashraf
 */
package ca.mcgill.ecse321.projectgroup09.dto;


import java.util.List;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;

public class OnlineMemberDto extends MemberDto {
	
	//Online Member Attributes
	private String memberEmail;
	private String memberPassword;
	private String memberUsername;

	public OnlineMemberDto(long accountID, String aFullName, 																				// account attributes
			Long aLibCardNumber, String anAddress, String aPhoneNumber, double anAmountOwed, int activeLoans, boolean isVerifiedResident,   // member attributes
			List<Loan> loans, List<Booking> bookings, List<LibraryItem> reserved, 															// member associations
			String aMemberEmail, String aMemberPassword, String aMemberUsername) {															// online member attributes (only ones managed by this class)
		super(accountID, aFullName, aLibCardNumber, anAddress, aPhoneNumber, anAmountOwed, activeLoans, isVerifiedResident, loans, bookings, reserved);
	
		this.memberEmail = aMemberEmail;
		this.memberPassword = aMemberPassword;
		this.memberUsername = aMemberUsername;
	}

	public static OnlineMemberDto convertToDto(OnlineMember onlineMember) {
		OnlineMemberDto onlineMemberDto = new OnlineMemberDto(
				onlineMember.getAccountID(),
				onlineMember.getFullName(),
				onlineMember.getLibCardNumber(),
				onlineMember.getAddress(),
				onlineMember.getPhoneNumber(),
				onlineMember.getAmountOwed(),
				onlineMember.getActiveLoans(),
				onlineMember.getIsVerifiedResident(),
				onlineMember.getLoans(),
				onlineMember.getBookings(),
				onlineMember.getReserved(),
				onlineMember.getMemberEmail(),
				onlineMember.getMemberPassword(),
				onlineMember.getMemberUsername()
		);
		return onlineMemberDto;
	}
	
	
	public String getMemberEmail() {
		return memberEmail;
	}
	
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	public String getMemberPassword() {
		return memberPassword;
	}
	
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	
	public String getMemberUsername() {
		return memberUsername;
	}
	
	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}
}

