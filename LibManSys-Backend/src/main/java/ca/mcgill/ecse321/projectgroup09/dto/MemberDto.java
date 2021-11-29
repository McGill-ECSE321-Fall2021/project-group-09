package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;


public class MemberDto extends AccountDto {
	// Account attributes
	private String fullName;	
	// Member Attributes
	private Long libCardNumber;
	private String address;
	private String phoneNumber;
	private double amountOwed;
	private int activeLoans;
	private boolean isVerifiedResident;
	// Member Associations
	private List<LoanDto> loans;
	private List<BookingDto> bookings;
	private List<LibraryItemDto> reserved;
	
//	/**
//	 * Default No-Arg Constructor
//	 */
//	public MemberDto() {
//		
//	}
	
	/**
	 * Initialize a new MemberDto object with the specified attributes. This constructor creates a MemberDto object with all fields filled.
	 * @param aFullName
	 * @param aLibCardNumber
	 * @param aAddress
	 * @param aIsResident
	 * @param aPhoneNumber
	 * @param aAmountOwed
	 * @param aActiveLoans
	 * @param aIsVerified
	 * @param aLoans
	 * @param aBookings
	 * @param aReserved
	 */
	public MemberDto(long accountID, String aFullName, Long aLibCardNumber, String aAddress,
			String aPhoneNumber, double aAmountOwed, int aActiveLoans, boolean aIsVerifiedResident,
			List<Loan> aLoans, List<Booking> aBookings, List<LibraryItem> aReserved) {
		super(accountID, aFullName);
		
		this.libCardNumber = aLibCardNumber;
		this.address = aAddress;
		this.phoneNumber = aPhoneNumber;
		this.amountOwed = aAmountOwed;
		this.activeLoans = aActiveLoans;
		this.isVerifiedResident = aIsVerifiedResident;
		// convert collections to Dto's
		List<LoanDto> aLoansDto = aLoans.stream().map(loan -> LoanDto.convertToDto(loan, false)).collect(Collectors.toList());
		this.loans = aLoansDto;
		List<BookingDto> aBookingsDto = aBookings.stream().map(booking -> BookingDto.convertToDto(booking)).collect(Collectors.toList());
		this.bookings = aBookingsDto;
		List<LibraryItemDto> aReservedDto = aReserved.stream().map(libraryItem -> LibraryItemDto.convertToDto(libraryItem)).collect(Collectors.toList());
		this.reserved = aReservedDto;
	}
	
	/**
	 * Creates MemberDto object to store information of member.
	 * @param member
	 * @return MemberDto object representing member.
	 */
	public static MemberDto convertToDto(Member member) {
		MemberDto memberDto = new MemberDto(
				member.getAccountId(),
				member.getFullName(),
				member.getLibCardNumber(),
				member.getAddress(),
				member.getPhoneNumber(),
				member.getAmountOwed(),
				member.getActiveLoans(),
				member.getIsVerifiedResident(),
				member.getLoans(),
				member.getBookings(),
				member.getReserved()
		);
		return memberDto;
	}

	/**
	 * @return the fullName
	 */
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
	
	
}
