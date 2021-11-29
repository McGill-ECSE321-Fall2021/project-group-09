package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@MappedSuperclass
@Entity
public abstract class Account {
	private Long accountId;
	private String fullName;
	
	/**
	 * Default constructor.
	 */
	public Account() {
		//this.accountId = getNextAccountId();
	}
	
	// Generate account id automatically
	//private static Long nextAccountId = 1L;
	
	/**
	 * Gets the next unique Id to assign to an account.
	 * @return {@code long} a unique account ID.
	 */
	/*
	 * private static Long getNextAccountId() { Long nextId = nextAccountId;
	 * nextAccountId++; return nextId; }
	 */
	
	// Already defined in Member, Librarian (both subclasses of Account)
	//@ElementCollection
	//private List<Booking> bookings;

	public void setFullName(String aFullName) {
		this.fullName = aFullName; 
	}
	
	
	//@Id
	@Column(name = "fullName", unique = false)
	public String getFullName() {
		return fullName;
	}


	/**
	 * Account ID's are generated automatically when a new Account object is added to the account repository.
	 * @return the accountId
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "accountId", unique = true, nullable = false)
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

	/**
	 * Returns true if this Account object is equal to specified object.
	 * Two accounts are equal if they have the same account Id.
	 */
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

