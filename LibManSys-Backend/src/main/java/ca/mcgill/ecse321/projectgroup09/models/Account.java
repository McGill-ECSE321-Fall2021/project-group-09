package ca.mcgill.ecse321.projectgroup09.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@MappedSuperclass
@Entity
public abstract class Account {
	private Long accountID;
	private String fullName;
	
	/**
	 * Default constructor.
	 */
	public Account() {}

	public void setFullName(String aFullName) {
		this.fullName = aFullName; 
	}
	
	public String getFullName() {
		return fullName;
	}


	/**
	 * Account ID's are generated automatically when a new Account object is added to the account repository.
	 * @return the accountId
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "accountID", unique = true, nullable = false)
	public Long getAccountID() {
		return accountID;
	}


	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountID(Long accountId) {
		this.accountID = accountId;
	}

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
		if (!this.getAccountID().equals(a.getAccountID())) {
			return false;
		}
		return true;
	}

}

