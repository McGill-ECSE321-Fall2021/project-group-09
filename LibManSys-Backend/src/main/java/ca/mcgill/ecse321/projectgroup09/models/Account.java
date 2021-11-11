package ca.mcgill.ecse321.projectgroup09.models;



import javax.persistence.Entity;
import javax.persistence.Id;

//@MappedSuperclass
@Entity
public abstract class Account {
	private Long accountId;
	private String fullName;
	
	/**
	 * Default constructor. Automatically sets account id.
	 */
	public Account() {
		this.accountId = getNextAccountId();
	}
	
	// Generate account id automatically
	private static Long nextAccountId = 1L;
	
	/**
	 * Gets the next unique Id to assign to an account.
	 * @return {@code long} a unique account ID.
	 */
	private static Long getNextAccountId() {
		Long nextId = nextAccountId;
		nextAccountId++;
		return nextId;
	}
	
	// Already defined in Member, Librarian (both subclasses of Account)
	//@ElementCollection
	//private List<Booking> bookings;

	public void setFullName(String aFullName) {
		this.fullName = aFullName; 
	}
	
	
	//@Id
	public String getFullName() {
		return fullName;
	}


	/**
	 * @return the accountId
	 */
	@Id
	public Long getAccountId() {
		return accountId;
	}


	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	
	/* Already defined in Member, Librarian (both subclasses of Account)
	
	@OneToMany(cascade = {CascadeType.ALL})
	public List<Booking> getBookings() {
		return this.bookings;
	}
	
	@OneToMany(cascade = {CascadeType.ALL})
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	*/


}

