/**
 * @author Zarif Ashraf
 */
package ca.mcgill.ecse321.projectgroup09.dto;


import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.OnlineMember;

public class OnlineMemberDto extends MemberDto {
	// Account attributes
	private String fullName;	
	// Member Attributes
	private Long libCardNumber;
	private String address;
	private String phoneNumber;
	private double amountOwed;
	private int activeLoans;
	private boolean isVerifiedResident;
	//Online Member Attributes
	private String memberEmail;
	private String memberPassword;
	private String memberUsername;
	
	//
	private List<LoanDto> loans;
	private List<BookingDto> bookings;
	private List<LibraryItemDto> reserved;

public OnlineMemberDto(long accountID, String aFullName, Long aLibCardNumber, String anAddress,
		String aPhoneNumber, double anAmountOwed, int activeLoans, boolean isVerifiedResident,
		List<Loan> loans, List<Booking> bookings, List<LibraryItem> reserved, String aMemberEmail, String aMemberPassword, String aMemberUsername) {
	super(accountID, aFullName, aLibCardNumber, anAddress, aPhoneNumber, anAmountOwed, activeLoans, isVerifiedResident, loans, bookings, reserved);
	
	List<LoanDto> aLoansDto = loans.stream().map(loan -> LoanDto.convertToDto(loan, false)).collect(Collectors.toList());
	this.loans = aLoansDto;
	List<BookingDto> aBookingsDto = bookings.stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
	this.bookings = aBookingsDto;
	List<LibraryItemDto> aReservedDto = reserved.stream().map(libraryItem -> LibraryItemDto.convertToDto(libraryItem)).collect(Collectors.toList());
	this.reserved = aReservedDto;
	
	this.memberEmail = aMemberEmail;
	this.memberPassword = aMemberPassword;
	this.memberUsername = aMemberUsername;
}

public static OnlineMemberDto convertToDto(OnlineMember onlineMember) {
	OnlineMemberDto onlineMemberDto = new OnlineMemberDto(
			onlineMember.getAccountId(),
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

public String getFullName() {
	return fullName;
}

/**
 * @param fullName the fullName to set
 */
public void setFullName(String fullName) {
	this.fullName = fullName;
}

/**
 * @return the libCardNumber
 */
public Long getLibCardNumber() {
	return libCardNumber;
}

/**
 * @param libCardNumber the libCardNumber to set
 */
public void setLibCardNumber(Long libCardNumber) {
	this.libCardNumber = libCardNumber;
}

/**
 * @return the address
 */
public String getAddress() {
	return address;
}

/**
 * @param address the address to set
 */
public void setAddress(String address) {
	this.address = address;
}

/**
 * @return the phoneNumber
 */
public String getPhoneNumber() {
	return phoneNumber;
}

/**
 * @param phoneNumber the phoneNumber to set
 */
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}

/**
 * @return the amountOwed
 */
public double getAmountOwed() {
	return amountOwed;
}

/**
 * @param amountOwed the amountOwed to set
 */
public void setAmountOwed(double amountOwed) {
	this.amountOwed = amountOwed;
}

/**
 * @return the activeLoans
 */
public int getActiveLoans() {
	return activeLoans;
}

/**
 * @param activeLoans the activeLoans to set
 */
public void setActiveLoans(int activeLoans) {
	this.activeLoans = activeLoans;
}

/**
 * @return the isVerified
 */
public boolean isVerifiedResident() {
	return isVerifiedResident;
}

/**
 * @param isVerified the isVerified to set
 */
public void setVerifiedResident(boolean isVerified) {
	this.isVerifiedResident = isVerified;
}

/**
 * @return the loans
 */
public List<LoanDto> getLoans() {
	return loans;
}

/**
 * @param loans the loans to set
 */
public void setLoans(List<LoanDto> loans) {
	this.loans = loans;
}

/**
 * @return the bookings
 */
public List<BookingDto> getBookings() {
	return bookings;
}

/**
 * @param bookings the bookings to set
 */
public void setBookings(List<BookingDto> bookings) {
	this.bookings = bookings;
}

/**
 * @return the reserved
 */
public List<LibraryItemDto> getReserved() {
	return reserved;
}

/**
 * @param reserved the reserved to set
 */
public void setReserved(List<LibraryItemDto> reserved) {
	this.reserved = reserved;
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

