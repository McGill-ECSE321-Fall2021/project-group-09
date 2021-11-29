package ca.mcgill.ecse321.projectgroup09.dto;

import java.util.List;
import java.util.stream.Collectors;

import ca.mcgill.ecse321.projectgroup09.models.Booking;
import ca.mcgill.ecse321.projectgroup09.models.LibraryItem;
import ca.mcgill.ecse321.projectgroup09.models.Loan;
import ca.mcgill.ecse321.projectgroup09.models.Member;


public class MemberDto extends AccountDto {
	
	// Member Attributes
	private Long libCardNumber;
	private String address;
	private String phoneNumber;
	private double amountOwed;
	private int activeLoans;
	private boolean isVerifiedResident;
	
	// Member Associations
	private List<Long> loans;
	private List<Long> bookings;
	/** ID's of library items reserved by this member. */
	private List<Long> reservedLibraryItems;
	
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
		List<Long> aLoansDto = aLoans.stream().map(loan -> loan.getLoanID()).collect(Collectors.toList());
		this.loans = aLoansDto;
		List<Long> aBookingsDto = aBookings.stream().map(booking -> booking.getBookingID()).collect(Collectors.toList());
		this.bookings = aBookingsDto;
		List<Long> aReservedDto = aReserved.stream().map(libraryItem -> libraryItem.getlibraryItemID()).collect(Collectors.toList());
		this.reservedLibraryItems = aReservedDto;
	}
	
	/**
	 * Creates MemberDto object to store information of member.
	 * @param member
	 * @return MemberDto object representing member.
	 */
	public static MemberDto convertToDto(Member member) {
		MemberDto memberDto = new MemberDto(
				member.getAccountID(),
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
	public List<Long> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(List<Long> loans) {
		this.loans = loans;
	}

	/**
	 * @return the bookings
	 */
	public List<Long> getBookings() {
		return bookings;
	}

	/**
	 * @param bookings the bookings to set
	 */
	public void setBookings(List<Long> bookings) {
		this.bookings = bookings;
	}

	/**
	 * @return the reserved
	 */
	public List<Long> getReservedLibraryItems() {
		return reservedLibraryItems;
	}

	/**
	 * @param reserved the reserved to set
	 */
	public void setReservedLibraryItems(List<Long> reserved) {
		this.reservedLibraryItems = reserved;
	}

	
}
