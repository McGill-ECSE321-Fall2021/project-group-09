package ca.mcgill.ecse321.projectgroup09.models;

import java.util.List;

//JPA tags added

import javax.persistence.*;

@Entity

public abstract class Account {
	
	
	private String fullName;
	
	// Already defined in Member, Librarian (both subclasses of Account)
	//@ElementCollection
	//private List<Booking> bookings;

	public void setFullName(String aFullName) {
		this.fullName = aFullName; 
	}
	
	
	@Id
	public String getFullName() {
		return fullName;
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

